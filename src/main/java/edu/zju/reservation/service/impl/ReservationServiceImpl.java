package edu.zju.reservation.service.impl;

import edu.zju.reservation.dao.inter.*;
import edu.zju.reservation.domain.ResClass;
import edu.zju.reservation.domain.ResReservation;
import edu.zju.reservation.domain.ResTimequantum;
import edu.zju.reservation.exceptions.ApplicationException;
import edu.zju.reservation.model.ReservationForm;
import edu.zju.reservation.model.ReservationModel;
import edu.zju.reservation.model.TimequantumModel;
import edu.zju.reservation.service.inter.ReservationServiceInter;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service("reservationService")
public class ReservationServiceImpl implements ReservationServiceInter {

    // =====================数据访问层==============================
    @Resource
    private ClassDaoInter classDao;

    public ClassDaoInter getClassDao() {
        return classDao;
    }

    public void setClassDao(ClassDaoInter classDao) {
        this.classDao = classDao;
    }

    @Resource
    private TimequantumDaoInter timeQuantumDao;

    public TimequantumDaoInter getTimeQuantumDao() {
        return timeQuantumDao;
    }

    public void setTimeQuantumDao(TimequantumDaoInter timeQuantumDao) {
        this.timeQuantumDao = timeQuantumDao;
    }

    @Resource
    private ReservationDaoInter reservationDao;

    public ReservationDaoInter getReservationDao() {
        return reservationDao;
    }

    public void setReservationDao(ReservationDaoInter reservationDao) {
        this.reservationDao = reservationDao;
    }

    @Resource
    private StudentDaoInter studentDao;

    public StudentDaoInter getStudentDao() {
        return studentDao;
    }

    public void setStudentDao(StudentDaoInter studentDao) {
        this.studentDao = studentDao;
    }

    @Resource
    private ConfigDaoInter configDao;

    public ConfigDaoInter getConfigDao() {
        return configDao;
    }

    public void setConfigDao(ConfigDaoInter configDao) {
        this.configDao = configDao;
    }

    // =============================业务逻辑=========================

    /**
     * 主页面加载时的显示信息
     */
    @Override
    public List<ReservationModel> getReservationInfo() {
        // 获取教室列表
        List<ResClass> classesList = this.classDao.getClasses();
        // 获取时间段列表
        List<ResTimequantum> timeQuantumList = this.timeQuantumDao
                .getValidTimequantum();
        // 获取今日时间
        Date now = new Date();

        // 一共需要显示的天数
        int totalDays = Integer.parseInt(configDao.getConfigByKey("totalDays")
                .getCvalue());

        List<ReservationModel> rmlist = new ArrayList<ReservationModel>();
        int isFirst = 1; // 每一天的第一个数据要在页面显示信息数
        for (int i = 0; i < totalDays; i++) { // 循环获取从今天开始往后totalDays的数据
            ReservationModel rm = new ReservationModel(); // 每一天的数据存在一个ReservationModel钟
            // Date today = new Date(now.getTime() + i * 3600 * 24);
            Calendar cal = Calendar.getInstance();
            cal.setTime(now); // 设置当前日期
            cal.add(Calendar.DATE, i); // 日期加1天
            rm.setDate(cal.getTime());
            rm.setWeek(this.getWeek(cal.getTime()));
            List<TimequantumModel> tlist = new ArrayList<TimequantumModel>();
            isFirst = 1;
            for (ResTimequantum t : timeQuantumList) { // 循环每一个时间段
                TimequantumModel tm = new TimequantumModel(); // 每一个时间段的数据存在一个TimequantumModel中
                tm.setTimewuantum(t);
                tm.setIsFirst(isFirst);
                isFirst = 0;
                List<ResReservation> list = new ArrayList<ResReservation>();
                for (ResClass c : classesList) { // 循环每个教室
                    // 查询是否有预约记录
                    ResReservation reservation = this.reservationDao
                            .getReservationByDateAndTimequantumAndClass(
                                    cal.getTime(), t.getTid(), c.getCid());

                    list.add(reservation);
                }
                tm.setReservations(list);
                tlist.add(tm);
            }
            rm.setTimequantum(tlist);
            rmlist.add(rm);
        }

        return rmlist;
    }

    /**
     * 根据日期获取周数
     *
     * @param date
     * @return
     */
    private String getWeek(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int week = cal.get(Calendar.DAY_OF_WEEK);
        String strReturn;
        switch (week) {
            case 1:
                strReturn = "星期一";
                break;
            case 2:
                strReturn = "星期二";
                break;
            case 3:
                strReturn = "星期三";
                break;
            case 4:
                strReturn = "星期四";
                break;
            case 5:
                strReturn = "星期五";
                break;
            case 6:
                strReturn = "星期六";
                break;
            case 7:
                strReturn = "星期日";
                break;
            default:
                strReturn = "";
        }
        return strReturn;
    }

    /**
     * 根据日期和时间段查询是预约记录
     */
    @Override
    public ResReservation getReservationByDateAndTimequantumAndClass(Date date,
                                                                     Serializable tid, Serializable cid) {
        return this.reservationDao.getReservationByDateAndTimequantumAndClass(
                date, tid, cid);
    }

    /**
     * 获取所有预约记录（根据日期desc排序）
     */
    @Override
    public List<ResReservation> getAllReservations() {
        return this.reservationDao.getAllReservations();
    }

    /**
     * 根据用户获取预约信息
     */
    @Override
    public List<ResReservation> getReservationsByStudentId(Serializable sid) {
        return this.reservationDao.getReservationsByStudentId(sid);
    }

    /**
     * 根据ID获取预约信息
     */
    @Override
    public ResReservation getReservationById(Serializable rid) {
        return this.reservationDao.getReservationById(rid);
    }

    /**
     * 修改预约信息
     */
    @Override
    public void updateReservation(ResReservation reservation) {
        this.reservationDao.updateReservation(reservation);

    }

    /**
     * 更新用户预约状态
     */
    @Override
    public void updateReservationStatus(Long rid, String status) {
        this.reservationDao.updateReservationStatus(rid, status);
    }

    /**
     * 新增预约信息
     *
     * @throws ParseException
     */
    @Override
    public void addReservation(ReservationForm reservationForm)
            throws ParseException {
        ResReservation reservation = new ResReservation();
        // 数据封装
        // 设置学生
        reservation.setResStudent(studentDao.getStudentById(reservationForm
                .getSid()));
        // 设置教室
        reservation
                .setResClass(classDao.getClassById(reservationForm.getCid()));
        reservation.setCname(reservation.getResClass().getCname());
        // 设置预约时间段
        reservation.setResTimequantum(timeQuantumDao
                .getTimequantumById(reservationForm.getTimequantumid()));
        // 设置目标日期
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date targetdate = sdf.parse(reservationForm.getRtargetdate());
        targetdate.setSeconds(1); // 确保有时间
        reservation.setRtargetdate(targetdate);
        // 设置目标日期是周几
        Calendar cal = Calendar.getInstance();
        cal.setTime(targetdate);
        reservation.setRweeknum(cal.DAY_OF_WEEK);
        // 设置起始时间
        Date begintime = new Date();
        begintime.setTime(targetdate.getTime());
        begintime.setHours(reservation.getResTimequantum().getTbegintime()
                .getHours());
        begintime.setMinutes(reservation.getResTimequantum().getTbegintime()
                .getMinutes());
        begintime.setSeconds(0);
        reservation.setTbegintime(begintime);
        // 设置结束时间
        Date endtime = new Date();
        endtime.setTime(targetdate.getTime());
        endtime.setHours(reservation.getResTimequantum().getTendtime()
                .getHours());
        endtime.setMinutes(reservation.getResTimequantum().getTendtime()
                .getMinutes());
        endtime.setSeconds(0);
        reservation.setTendtime(endtime);
        // 设置具体开始时间(需要修改)
        reservation.setRspecificbegintime(begintime);
        // 设置具体结束时间(需要修改)
        reservation.setRspecificendtime(endtime);
        // 设置记录生成时间
        reservation.setRrecordcreatetime(new Date());
        // 设置申请单位
        reservation.setRapplyunit(reservationForm.getRapplyunit());
        // 设置负责人
        reservation.setRchargeperson(reservationForm.getRchargeperson());
        // 设置负责人电话
        reservation.setRchargetelephone(reservationForm.getRchargetelephone());
        // 设置联系人
        reservation.setRcontactsperson(reservationForm.getRcontactsperson());
        // 设置联系人联系电话
        reservation
                .setRcontacttelephone(reservationForm.getRcontacttelephone());
        // 设置详细内容
        reservation.setRspecificcontent(reservationForm.getRspecificcontent());
        // 设置状态
        reservation.setRstatus("1");

        // 判断数据库中是否已经有预约记录了，防止重复预约
        ResReservation reservationInDB = getReservationByDateAndTimequantumAndClass(
                reservation.getRtargetdate(),
                reservationForm.getTimequantumid(), reservationForm.getCid());
        if (reservationInDB == null) {
            this.reservationDao.addReservation(reservation);
        } else {
            throw new ApplicationException("该教室该时间段已经存在预约记录，请不要重复预约！");
        }
    }

}
