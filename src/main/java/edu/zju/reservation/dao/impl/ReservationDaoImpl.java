package edu.zju.reservation.dao.impl;

import edu.zju.reservation.dao.basic.BasicDaoImpl;
import edu.zju.reservation.dao.inter.ReservationDaoInter;
import edu.zju.reservation.domain.ResReservation;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Component("reservationDao")
public class ReservationDaoImpl extends BasicDaoImpl implements
        ReservationDaoInter {

    /**
     * 根据用户获取预约信息
     */
    @Override
    public List<ResReservation> getReservationsByStudentId(Serializable sid) {
        return this
                .executeQuery(
                        "from ResReservation where resStudent.sid = ? order by rrecordcreatetime desc",
                        new Object[]{sid});
    }

    /**
     * 获取所有预约记录（根据日期desc排序）
     */
    @Override
    public List<ResReservation> getAllReservations() {
        return this.executeQuery(
                "from ResReservation order by rrecordcreatetime desc", null);
    }

    /**
     * 根据ID获取预约信息
     */
    @Override
    public ResReservation getReservationById(Serializable rid) {
        return (ResReservation) this.findById(ResReservation.class, rid);
    }

    /**
     * 根据日期、时间段、教室信息获取预约信息
     */
    @Override
    public ResReservation getReservationByDateAndTimequantumAndClass(Date date,
                                                                     Serializable tid, Serializable cid) {
        Date todayBegin = this.getTodayBeginOrEnd(date, 1);
        Date todayEnd = this.getTodayBeginOrEnd(date, 2);

        return (ResReservation) this
                .uniqueQuery(
                        "from ResReservation where rtargetdate >= ? and rtargetdate < ? and resTimequantum.tid = ? and resClass.cid = ? and rstatus = ?",
                        new Object[]{todayBegin, todayEnd, tid, cid, "1"});
    }

    /**
     * 获取今日凌晨或23:59:59时间
     *
     * @param date
     * @return
     */
    private Date getTodayBeginOrEnd(Date date, int type) {
        Date today = new Date(date.getTime());
        if (type == 1) {
            today.setHours(0);
            today.setMinutes(0);
            today.setSeconds(0);
        } else if (type == 2) {
            today.setHours(23);
            today.setMinutes(59);
            today.setSeconds(59);
        }
        return today;
    }

    /**
     * 修改预约信息
     */
    @Override
    public void updateReservation(ResReservation reservation) {
        this.update(reservation);

    }

    /**
     * 更新用户预约状态
     */
    @Override
    public void updateReservationStatus(Long rid, String status) {
        this.executeUpdate(
                "update ResReservation set rstatus = ? where rid = ?",
                new Object[]{status, rid});

    }

    /**
     * 新增预约信息
     */
    @Override
    public void addReservation(ResReservation reservation) {
        this.add(reservation);
    }

}
