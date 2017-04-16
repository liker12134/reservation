package edu.zju.reservation.controller;

import com.google.gson.Gson;
import edu.zju.reservation.domain.*;
import edu.zju.reservation.exceptions.ApplicationException;
import edu.zju.reservation.model.ReservationForm;
import edu.zju.reservation.model.ReservationModel;
import edu.zju.reservation.model.ResponseCode;
import edu.zju.reservation.service.inter.*;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Controller
@RequestMapping("/user.do")
public class UserController {
    Logger log = Logger.getLogger(UserController.class);
    Gson gson = new Gson();
    static Lock lock = new ReentrantLock();

    // ==================业务逻辑==========================
    @Resource
    private ReservationServiceInter reservationService;

    public ReservationServiceInter getReservationService() {
        return reservationService;
    }

    public void setReservationService(ReservationServiceInter reservationService) {
        this.reservationService = reservationService;
    }

    @Resource
    private ClassServiceInter classService;

    public ClassServiceInter getClassService() {
        return classService;
    }

    public void setClassService(ClassServiceInter classService) {
        this.classService = classService;
    }

    @Resource
    private TimequantumServiceInter timeQuantumService;

    public TimequantumServiceInter getTimeQuantumService() {
        return timeQuantumService;
    }

    public void setTimeQuantumService(TimequantumServiceInter timeQuantumService) {
        this.timeQuantumService = timeQuantumService;
    }

    @Resource
    private StudentServiceInter studentService;

    public StudentServiceInter getStudentService() {
        return studentService;
    }

    public void setStudentService(StudentServiceInter studentService) {
        this.studentService = studentService;
    }

    @Resource
    private ConfigServiceInter configService;

    public ConfigServiceInter getConfigService() {
        return configService;
    }

    public void setConfigService(ConfigServiceInter configService) {
        this.configService = configService;
    }

    @RequestMapping(value = "test.do", method = RequestMethod.GET)
    public String test() {
        return "";
    }
    // ====================================================

    /**
     * 跳转到主页面
     *
     * @param map
     * @return
     */
    @RequestMapping(params = "flag=welcome")
    public String welcome(ModelMap map) {
        List<ResClass> classList = null;
        List<ReservationModel> reservationModelList = null;
        try {
            classList = this.classService.getClasses();
            reservationModelList = this.reservationService.getReservationInfo();
            map.put("title", "活动室自助服务系统");
            map.put("classList", classList);
            map.put("reservationModelList", reservationModelList);
            // String notice = ReadFromFile.readText(this.getClass()
            // .getResource("/").getPath().replace("%20", " ")
            // + "helptext.txt");
            ResConfig config = this.configService.getConfigByKey("notice");
            map.put("notice", config.getCvalue());
            return "user/welcome";
        } catch (Exception e) {
            map.put("err", e.getMessage());
            log.error(e.getMessage(), e);
            return "error";
        }

    }

    /**
     * 跳转申请页面
     *
     * @param map
     * @return
     */
    @RequestMapping(params = "flag=forAddReservation")
    public String forAddReservation(Integer cid, String date_, Integer tid,
                                    ModelMap map, HttpServletRequest req) {
        // 确认用户是否登录
        if (!isLogin(req)) { // 用户未登录
            map.put("err", "您未登入系统，不可进行预约操作！");
            return "error";
        }

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date;
        try {
            date = sdf.parse(date_);
        } catch (ParseException e1) {
            map.put("err", "数据加载失败！" + e1.getMessage());
            log.error(e1.getMessage(), e1);
            return "error";
        }

        try {
            // 再一次确认用户申请的活动室是否是空闲的
            ResReservation reservation = this.reservationService
                    .getReservationByDateAndTimequantumAndClass(date, tid, cid);

            if (reservation == null) { // 还没有预约记录
                ResTimequantum timequantum = this.timeQuantumService
                        .getTimequantumById(tid);
                ResClass rclass = this.classService.getClassById(cid);
                map.put("timequantum", timequantum);
                map.put("rclass", rclass);
                map.put("rtargetdate", date);
                map.put("title", "活动室申请");
                return "user/add_reservation";
            } else { // 已经有预约记录了
                map.put("err", "该教室该时间段已经有预约记录了！");
                return "error";
            }
        } catch (Exception e) {
            map.put("err", "数据加载失败！" + e.getMessage());
            log.error(e.getMessage(), e);
            return "error";
        }
    }

    /**
     * 添加申请记录
     *
     * @param map
     * @return
     */
    @RequestMapping(params = "flag=addReservation")
    public String addReservation(ReservationForm reservationForm, ModelMap map,
                                 HttpServletRequest req) {
        // 确认用户是否登录
        if (!isLogin(req)) {
            map.put("err", "您未登入系统，不可进行预约操作！");
            return "error";
        }

        try {
            lock.lock();
            log.debug(reservationForm);
            reservationForm.setSid(getUserFromSession(req).getSid());
            reservationService.addReservation(reservationForm);
            return "redirect:user.do?flag=myReservation";
        } catch (Exception e) {
            map.put("err", e.getMessage());
            log.error(e.getMessage(), e);
            return "error";
        } finally {
            lock.unlock();
        }
    }

    /**
     * 跳转申请查询页面
     *
     * @param map
     * @return
     */
    @RequestMapping(params = "flag=showReservation")
    public String showReservation(Long rid, ModelMap map, HttpServletRequest req) {
        ResReservation reservation = null;
        try {

            reservation = this.reservationService.getReservationById(rid);
            if (reservation != null) {
                map.put("title", "申请查询");
                map.put("res", reservation);
                return "user/show_reservation";
            } else {
                map.put("err", "无相应查询结果");
                return "error";
            }
        } catch (Exception e) {
            map.put("err", e.getMessage());
            log.error(e.getMessage());
            return "error";
        }
    }

    /**
     * 显示班级组织预约记录
     *
     * @param map
     * @return
     */
    @RequestMapping(params = "flag=myReservation")
    public String myReservation(ModelMap map, HttpServletRequest req) {
        // 确认用户是否登录
        if (!isLogin(req)) {
            map.put("err", "您未登入系统，不可查看个人预约记录！");
            return "error";
        }

        List<ResReservation> list = null;
        try {
            list = this.reservationService
                    .getReservationsByStudentId(getUserFromSession(req)
                            .getSid());
            map.put("title", "历史预约记录");
            map.put("list", list);
            return "user/show_myreservations";
        } catch (Exception e) {
            map.put("err", e.getMessage());
            log.error(e.getMessage());
            return "error";
        }
    }

    /**
     * 显示班级组织信息中心
     *
     * @param map
     * @return
     */
    @RequestMapping(params = "flag=self")
    public String self(ModelMap map, HttpServletRequest req) {
        // 确认用户是否登录
        if (!isLogin(req)) {
            map.put("err", "您未登入系统，不可查看班级组织信息！");
            return "error";
        }
        // 获取用户个人信息
        ResStudent student = getUserFromSession(req);
        map.put("title", "班级组织信息中心");
        map.put("student", student);
        return "user/self";
    }

    /**
     * 更新班级组织信息中心
     *
     * @param map
     * @return
     */
    @RequestMapping(params = "flag=updateSelf")
    public String updateSelf(ResStudent student, ModelMap map,
                             HttpServletRequest req) {
        // 确认用户是否登录
        if (!isLogin(req)) {
            map.put("err", "您未登入系统，不可更新班级组织信息！");
            return "error";
        }
        try {
            this.studentService.updateStudent(student);
            // 存入session
            req.getSession().setAttribute("userSession",
                    this.studentService.getStudentById(student.getSid()));
            return "redirect:user.do?flag=self";
        } catch (Exception e) {
            map.put("err", e.getMessage());
            log.error(e.getMessage(), e);
            return "error";
        }

    }

    /**
     * 登入请求(ajax)
     *
     * @param map
     * @return
     * @throws IOException
     */
    @RequestMapping(params = "flag=login")
    public @ResponseBody
    void login(String sno, String spassword, HttpServletRequest req,
               HttpServletResponse res) throws IOException {
        log.debug(sno + "  " + spassword);
        ResponseCode rc = new ResponseCode();
        try {
            ResStudent student = this.studentService.checkStudent(sno,
                    spassword);
            rc.setCode("0");
            rc.setMsg("success");
            // 存入session
            req.getSession().setAttribute("userSession", student);
            // 更新用户最后一次登入时间
            this.studentService.updateLastLogin(student.getSid());
        } catch (ApplicationException ae) {
            rc.setCode("1");
            rc.setMsg(ae.getMessage());
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            rc.setCode("-1");
            rc.setMsg(e.getMessage());
        }
        log.debug(rc.toString());
        res.setContentType("text/html;charset=utf-8");
        res.getWriter().print(gson.toJson(rc));
    }

    /**
     * 登出请求
     *
     * @param map
     * @return
     */
    @RequestMapping(params = "flag=logout")
    public String logout(HttpServletRequest req) {
        ResStudent student = (ResStudent) req.getSession().getAttribute(
                "userSession");
        if (student != null) {
            req.getSession().removeAttribute("userSession");
        }
        // 重定向到主页面
        return "redirect:user.do?flag=welcome";
    }

    /**
     * 修改用户预约状态（ajax调用）
     *
     * @param rid
     * @param status
     * @return
     */
    @RequestMapping(params = "flag=updateReservationStatus")
    public @ResponseBody
    String updateReservationStatus(Long rid, String status) {
        log.debug(rid + "   " + status);
        ResponseCode code = new ResponseCode();
        try {
            this.reservationService.updateReservationStatus(rid, status);
            code.setCode("0");
            code.setMsg(null);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            code.setCode("-1");
            code.setMsg(e.getMessage());
        } finally {
            return gson.toJson(code);
        }
    }

    /**
     * 获取session中的用户信息
     *
     * @param req
     * @return
     */
    private ResStudent getUserFromSession(HttpServletRequest req) {
        return (ResStudent) req.getSession().getAttribute("userSession");
    }

    /**
     * 判断用户是否已经登录了
     *
     * @param req
     * @return
     */
    private boolean isLogin(HttpServletRequest req) {
        ResStudent student = null;
        student = this.getUserFromSession(req);
        if (student == null) {
            return false;
        } else {
            return true;
        }
    }
}