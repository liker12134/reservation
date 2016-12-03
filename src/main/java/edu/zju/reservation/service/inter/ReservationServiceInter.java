package edu.zju.reservation.service.inter;

import java.io.Serializable;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

import edu.zju.reservation.domain.ResReservation;
import edu.zju.reservation.model.ReservationForm;
import edu.zju.reservation.model.ReservationModel;

public interface ReservationServiceInter {
	/**
	 * 主页面加载时的显示信息
	 */
	List<ReservationModel> getReservationInfo();

	/**
	 * 根据日期、时间段和教室查询是预约记录
	 */
	ResReservation getReservationByDateAndTimequantumAndClass(Date date,
			Serializable tid, Serializable cid);

	/**
	 * 根据用户获取预约信息
	 */
	List<ResReservation> getReservationsByStudentId(Serializable sid);

	/**
	 * 获取所有预约记录（根据日期desc排序）
	 */
	List<ResReservation> getAllReservations();

	/**
	 * 根据ID获取预约信息
	 */
	ResReservation getReservationById(Serializable rid);

	/**
	 * 修改预约信息
	 */
	void updateReservation(ResReservation reservation);

	/**
	 * 更新用户预约状态
	 */
	public void updateReservationStatus(Long rid, String status);

	/**
	 * 新增预约信息
	 * 
	 * @throws ParseException
	 */
	void addReservation(ReservationForm reservationForm) throws ParseException;

}
