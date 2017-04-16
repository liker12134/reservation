package edu.zju.reservation.dao.inter;

import edu.zju.reservation.domain.ResReservation;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public interface ReservationDaoInter {
    /**
     * 根据用户获取预约信息
     */
    List<ResReservation> getReservationsByStudentId(Serializable sid);

    /**
     * 获取所有预约记录（根据日期desc排序）
     */
    public List<ResReservation> getAllReservations();

    /**
     * 根据ID获取预约信息
     */
    ResReservation getReservationById(Serializable rid);

    /**
     * 根据日期、时间段、教室信息获取预约信息
     */
    ResReservation getReservationByDateAndTimequantumAndClass(Date date,
                                                              Serializable tid, Serializable cid);

    /**
     * 修改预约信息
     */
    void updateReservation(ResReservation reservation);

    /**
     * 更新用户预约状态
     */
    void updateReservationStatus(Long rid, String status);

    /**
     * 新增预约信息
     */
    void addReservation(ResReservation reservation);

}
