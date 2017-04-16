package edu.zju.reservation.model;

import edu.zju.reservation.domain.ResReservation;
import edu.zju.reservation.domain.ResTimequantum;

import java.util.List;

public class TimequantumModel {
    private ResTimequantum timewuantum; // 时间段
    private List<ResReservation> reservations; // 各个教室的预约情况
    private Integer isFirst; // 是否是每日的第一个数据，1为是 0为不是

    public TimequantumModel() {

    }

    public TimequantumModel(ResTimequantum timewuantum,
                            List<ResReservation> reservations, Integer isFirst) {
        super();
        this.timewuantum = timewuantum;
        this.reservations = reservations;
        this.isFirst = isFirst;
    }

    public Integer getIsFirst() {
        return isFirst;
    }

    public void setIsFirst(Integer isFirst) {
        this.isFirst = isFirst;
    }

    public ResTimequantum getTimewuantum() {
        return timewuantum;
    }

    public void setTimewuantum(ResTimequantum timewuantum) {
        this.timewuantum = timewuantum;
    }

    public List<ResReservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<ResReservation> reservations) {
        this.reservations = reservations;
    }

}
