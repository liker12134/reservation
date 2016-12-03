package edu.zju.reservation.model;

import java.util.Date;
import java.util.List;

/**
 * 用于在主页面显示的model数据
 * 
 * @author panye
 * 
 */
public class ReservationModel {
	private Date date; // 日期
	private String week; // 日期对应的星期
	private List<TimequantumModel> timequantum; // 时间段+各个教室是否可预约

	public ReservationModel() {
	}

	public ReservationModel(Date date, String week,
			List<TimequantumModel> timequantum) {
		super();
		this.date = date;
		this.week = week;
		this.timequantum = timequantum;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getWeek() {
		return week;
	}

	public void setWeek(String week) {
		this.week = week;
	}

	public List<TimequantumModel> getTimequantum() {
		return timequantum;
	}

	public void setTimequantum(List<TimequantumModel> timequantum) {
		this.timequantum = timequantum;
	}

}
