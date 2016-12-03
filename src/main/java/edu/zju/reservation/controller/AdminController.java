package edu.zju.reservation.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.google.gson.Gson;

import edu.zju.reservation.domain.ResClass;
import edu.zju.reservation.domain.ResConfig;
import edu.zju.reservation.domain.ResManager;
import edu.zju.reservation.domain.ResReservation;
import edu.zju.reservation.domain.ResStudent;
import edu.zju.reservation.exceptions.ApplicationException;
import edu.zju.reservation.model.ResponseCode;
import edu.zju.reservation.service.inter.AdminServiceInter;
import edu.zju.reservation.service.inter.ClassServiceInter;
import edu.zju.reservation.service.inter.ConfigServiceInter;
import edu.zju.reservation.service.inter.ReservationServiceInter;
import edu.zju.reservation.service.inter.StudentServiceInter;

@Controller
@RequestMapping("/admin.do")
public class AdminController {
	Logger log = Logger.getLogger(AdminController.class);
	Gson gson = new Gson();
	// ==================业务逻辑==========================
	@Resource
	private AdminServiceInter adminService;

	public AdminServiceInter getAdminService() {
		return adminService;
	}

	public void setAdminService(AdminServiceInter adminService) {
		this.adminService = adminService;
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
	private ReservationServiceInter reservationService;

	public ReservationServiceInter getReservationService() {
		return reservationService;
	}

	public void setReservationService(ReservationServiceInter reservationService) {
		this.reservationService = reservationService;
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

	// ====================================================
	/**
	 * 跳转到管理员登录页面
	 * 
	 * @param map
	 * @return
	 */
	@RequestMapping(params = "flag=forlogin")
	public String forlogin(ModelMap map) {
		return "admin/login";
	}

	/**
	 * 管理员登录
	 * 
	 * @param username
	 * @param userpass
	 * @param map
	 * @return
	 */
	@RequestMapping(params = "flag=login")
	public String login(String account, String password, ModelMap map,
			HttpServletRequest req) {
		log.debug(account + "   " + password);
		try {
			// 账号密码校验
			ResManager manager = this.adminService
					.checkAdmin(account, password);
			// 存入session
			req.getSession().setAttribute("admin", manager);
			return "redirect:admin.do?flag=main";
		} catch (ApplicationException ae) { // 账号不存在或密码错误
			log.error(ae.getMessage(), ae);
			map.put("err", ae.getMessage());
			return "admin/login";
		} catch (Exception e) { // 无法预料的错误
			log.error(e.getMessage(), e);
			map.put("err", e.getMessage());
			map.put("type", "admin");
			return "error";
		}
	}

	/**
	 * 管理员登出
	 * 
	 * @param req
	 * @return
	 */
	@RequestMapping(params = "flag=logout")
	public String logout(HttpServletRequest req) {
		if (req.getSession().getAttribute("admin") != null) {
			req.getSession().removeAttribute("admin");
		}
		return "admin/login";
	}

	/**
	 * 跳转到管理员主页面（默认为教室维护）
	 * 
	 * @return
	 */
	@RequestMapping(params = "flag=main")
	public String main() {
		return "forward:/admin.do?flag=showClass";
	}

	/**
	 * 显示教室信息列表
	 * 
	 * @return
	 */
	@RequestMapping(params = "flag=showClass")
	public String showClass(ModelMap map) {
		// 获取教室列表
		List<ResClass> classList = null;
		try {
			classList = this.classService.getAllClasses();
			map.put("classList", classList);
			return "admin/show_class";
		} catch (Exception e) {
			log.error(e.getMessage());
			map.put("err", e.getMessage());
			return "error";
		}
	}

	/**
	 * 跳转到新增教室页面
	 * 
	 * @return
	 */
	@RequestMapping(params = "flag=forAddClass")
	public String forAddClass() {
		return "admin/add_class";
	}

	/**
	 * 根据教室名称查询教室是否存在(ajax请求)
	 * 
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(params = "flag=selectClassByName")
	public @ResponseBody
	String selectClassByName(String cname) throws UnsupportedEncodingException {
		ResponseCode code = new ResponseCode();
		try {
			boolean isExist = this.classService.isExistByName(cname);
			code.setCode(isExist ? "1" : "0");
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			code.setCode("-1");
			code.setMsg(e.getMessage());
		} finally {
			return gson.toJson(code);
		}
	}

	/**
	 * 添加教室
	 * 
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(params = "flag=addClass")
	public String addClass(ResClass resClass, ModelMap map,
			HttpServletRequest req) throws UnsupportedEncodingException {
		// 从session中获取管理员信息
		ResManager manager = (ResManager) req.getSession()
				.getAttribute("admin");
		// 重新查询数据库
		manager = this.adminService.getManagerByAccount(manager.getMaccount());
		// url decode
		resClass.setResManager(manager);
		// 添加到数据库
		try {
			this.classService.addClass(resClass);
			return "success";
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			map.put("err", e.getMessage());
			return "error";
		}
	}

	/**
	 * 跳转到修改教室页面
	 * 
	 * @return
	 */
	@RequestMapping(params = "flag=forUpdateClass")
	public String forUpdateClass(Integer cid, ModelMap map) {
		// 根据cid查询教室信息
		try {
			ResClass resClass = this.classService.getClassById(cid);
			// log.debug(resClass);
			map.put("classDetail", resClass);
			return "admin/modify_class";
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			map.put("err", e.getMessage());
			return "error";
		}
	}

	/**
	 * 修改教室
	 * 
	 * @return
	 */
	@RequestMapping(params = "flag=updateClass")
	public String updateClass(ResClass resClass, ModelMap map,
			HttpServletRequest req) {
		// 从session中获取管理员信息
		ResManager manager = (ResManager) req.getSession()
				.getAttribute("admin");
		// 重新查询数据库
		manager = this.adminService.getManagerByAccount(manager.getMaccount());
		resClass.setResManager(manager);

		try {
			this.classService.updateClass(resClass);
			return "success";
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			map.put("err", e.getMessage());
			return "error";
		}
	}

	/**
	 * 显示管理员信息列表
	 * 
	 * @return
	 */
	@RequestMapping(params = "flag=showManager")
	public String showManager(ModelMap map) {
		// 获取管理员列表
		List<ResManager> managerList = null;
		try {
			managerList = this.adminService.getAllManager();
			map.put("managerList", managerList);
			return "admin/show_manager";
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			map.put("err", e.getMessage());
			return "error";
		}
	}

	/**
	 * 跳转到新增管理员页面
	 * 
	 * @return
	 */
	@RequestMapping(params = "flag=forAddManager")
	public String forAddManager() {
		return "admin/add_manager";
	}

	/**
	 * 添加管理员
	 * 
	 * @return
	 */
	@RequestMapping(params = "flag=addManager")
	public String addManager(ResManager resManager, ModelMap map) {
		// 添加到数据库
		try {
			this.adminService.addManager(resManager);
			return "success";
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			map.put("err", e.getMessage());
			return "error";
		}
	}

	/**
	 * 跳转到修改管理员页面
	 * 
	 * @return
	 */
	@RequestMapping(params = "flag=forUpdateManager")
	public String forUpdateManager(String maccount, ModelMap map) {
		// 根据maccount查询管理员信息
		try {
			ResManager resManager = this.adminService
					.getManagerByAccount(maccount);
			// log.debug(resManager);
			map.put("manager", resManager);
			return "admin/modify_manager";
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			map.put("err", e.getMessage());
			return "error";
		}
	}

	/**
	 * 修改管理员
	 * 
	 * @return
	 */
	@RequestMapping(params = "flag=updateManager")
	public String updateManager(ResManager resManager, ModelMap map,
			HttpServletRequest req) {
		// 添加到数据库
		try {
			this.adminService.updateManager(resManager);
			return "success";
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			map.put("err", e.getMessage());
			return "error";
		}
	}

	/**
	 * 跳转到修改通知页面
	 * 
	 * @return
	 */
	@RequestMapping(params = "flag=showNotice")
	public String showNotice(ModelMap map) {
		// 获取通知信息
		try {
			// String notice = ReadFromFile.readText(this.getClass()
			// .getResource("/").getPath().replace("%20", " ")
			// + "helptext.txt");
			ResConfig config = this.configService.getConfigByKey("notice");
			map.put("notice", config.getCvalue());
			return "admin/show_notice";
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			map.put("err", e.getMessage());
			return "error";
		}
	}

	/**
	 * 修改通知(ajax请求)
	 * 
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(params = "flag=updateNotice")
	public @ResponseBody
	String updateNotice(String notice) throws UnsupportedEncodingException {
		notice = URLDecoder.decode(notice, "UTF-8").trim();
		ResponseCode code = new ResponseCode();
		try {
			// WriteToFile.writeText(this.getClass().getResource("/").getPath()
			// .replace("%20", " ")
			// + "helptext.txt", notice, false);
			this.configService
					.updateConfigByKey(new ResConfig("notice", notice));
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
	 * 显示预约列表
	 * 
	 * @param map
	 * @return
	 */
	@RequestMapping(params = "flag=showReservation")
	public String showReservation(ModelMap map) {
		try {
			List<ResReservation> resList = this.reservationService
					.getAllReservations();
			map.put("resList", resList);
			return "admin/show_reservation";
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			map.put("err", e.getMessage());
			return "error";
		}
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
	 * 显示班级组织用户列表
	 * 
	 * @param map
	 * @return
	 */

	@RequestMapping(params = "flag=showStudent")
	public String showStudent(ModelMap map) {
		try {
			List<ResStudent> stuList = this.studentService.getAllStudent();
			map.put("stuList", stuList);
			return "admin/show_student";
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			map.put("err", e.getMessage());
			return "error";
		}
	}

	/**
	 * 显示批量导入班级组织数据页面
	 * 
	 * @param map
	 * @return
	 */
	@RequestMapping(params = "flag=forBatchAddStudent")
	public String forBatchAddStudent() {
		return "admin/batch_add_student";
	}

	/**
	 * 上传班级组织数据（excel格式，且必须是xls文件）
	 * 
	 * @param map
	 * @return
	 */
	@RequestMapping(params = "flag=uploadStudent", method = RequestMethod.POST)
	public String uploadStudent(String name,
			@RequestParam("file") CommonsMultipartFile file,
			HttpServletRequest req, ModelMap map) {
		String fileName = file.getOriginalFilename();
		if (!fileName.substring(fileName.lastIndexOf(".") + 1).equals("xls")) {
			map.put("error", "文件必须为.xls文件！");
		} else if (file.isEmpty()) {
			map.put("error", "文件内容不能为空！");
		} else {
			List<ResStudent> studentList = null;
			try {
				// 解析excel
				studentList = this.studentService.parseExcel(file
						.getInputStream());
				// 存入session
				req.getSession().setAttribute("students", studentList);
				// 设置type，允许用户保存
				map.put("type", "commit");
			} catch (ApplicationException ae) {
				map.put("error", ae.getMessage());
			} catch (Exception e) {
				map.put("error", e.getMessage());
				log.error(e.getMessage(), e);
			}
		}
		return "admin/batch_add_student";

	}

	/**
	 * 批量保存班级组织用户数据
	 * 
	 * @param map
	 * @return
	 */
	@RequestMapping(params = "flag=batchAddStudent")
	public String batchAddStudent(HttpServletRequest req, ModelMap map) {
		List<ResStudent> studentList = (List<ResStudent>) req.getSession()
				.getAttribute("students");
		if (studentList != null) {
			try {
				// 批量保存
				this.studentService.batchAddStudent(studentList);
				map.put("msg", "保存成功！");
			} catch (Exception e) {
				map.put("error", e.getMessage());
			}
			// 删除session数据
			req.getSession().removeAttribute("students");
		} else {
			map.put("error", "没有数据可以保存！请先上传数据！");
		}
		return "admin/batch_add_student";
	}

	/**
	 * 跳转到新增班级组织页面
	 * 
	 * @return
	 */
	@RequestMapping(params = "flag=forAddStudent")
	public String forAddStudent() {
		return "admin/add_student";
	}

	/**
	 * 添加班级组织
	 * 
	 * @return
	 */
	@RequestMapping(params = "flag=addStudent")
	public String addStudent(ResStudent resStudent, ModelMap map) {
		// 添加到数据库
		try {
			this.studentService.addStudent(resStudent);
			return "success";
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			map.put("err", e.getMessage());
			return "error";
		}
	}

	/**
	 * 跳转到修改班级组织页面
	 * 
	 * @return
	 */
	@RequestMapping(params = "flag=forUpdateStudent")
	public String forUpdateStudent(Integer sid, ModelMap map) {
		// 根据sid查询班级组织信息
		try {
			ResStudent resStudent = this.studentService.getStudentById(sid);
			log.debug(resStudent);
			map.put("student", resStudent);
			return "admin/modify_student";
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			map.put("err", e.getMessage());
			return "error";
		}
	}

	/**
	 * 修改班级组织
	 * 
	 * @return
	 */
	@RequestMapping(params = "flag=updateStudent")
	public String updateStudent(ResStudent resStudent, ModelMap map) {
		// 添加到数据库
		try {
			this.studentService.updateStudent(resStudent);
			return "success";
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			map.put("err", e.getMessage());
			return "error";
		}
	}

	/**
	 * 根据班级组织sno判断班级组织是否存在
	 * 
	 * @param sno
	 * @param map
	 * @return
	 */
	@RequestMapping(params = "flag=checkBySno")
	public @ResponseBody
	String checkBySno(String sno) {
		ResponseCode code = new ResponseCode();
		try {
			boolean isExist = this.studentService.isExistBySno(sno);
			if (isExist) {
				code.setCode("-1");
			} else {
				code.setCode("0");
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			code.setCode("-2");
			code.setMsg(e.getMessage());
		} finally {
			return gson.toJson(code);
		}
	}

	/**
	 * 显示时间段维护界面
	 * 
	 * @param map
	 * @return
	 */
	@RequestMapping(params = "flag=showTimequantum")
	public String showTimequantum(ModelMap map) {
		return "coding";
	}

}