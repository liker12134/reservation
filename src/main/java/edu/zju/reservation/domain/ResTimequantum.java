package edu.zju.reservation.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * ResTimequantum entity. @author MyEclipse Persistence Tools
 */

public class ResTimequantum implements java.io.Serializable {

    // Fields

    private Integer tid;
    private ResManager resManager;
    private Date tbegintime;
    private Date tendtime;
    private Date teffectivebegintime;
    private Date teffectiveendtime;
    private Date tmodifiedtime;
    private Set resReservations = new HashSet(0);

    // Constructors

    /**
     * default constructor
     */
    public ResTimequantum() {
    }

    /**
     * minimal constructor
     */
    public ResTimequantum(Date tbegintime, Date tendtime) {
        this.tbegintime = tbegintime;
        this.tendtime = tendtime;
    }

    /**
     * full constructor
     */
    public ResTimequantum(ResManager resManager, Date tbegintime,
                          Date tendtime, Date teffectivebegintime, Date teffectiveendtime,
                          Date tmodifiedtime, Set resReservations) {
        this.resManager = resManager;
        this.tbegintime = tbegintime;
        this.tendtime = tendtime;
        this.teffectivebegintime = teffectivebegintime;
        this.teffectiveendtime = teffectiveendtime;
        this.tmodifiedtime = tmodifiedtime;
        this.resReservations = resReservations;
    }

    // Property accessors

    public Integer getTid() {
        return this.tid;
    }

    public void setTid(Integer tid) {
        this.tid = tid;
    }

    public ResManager getResManager() {
        return this.resManager;
    }

    public void setResManager(ResManager resManager) {
        this.resManager = resManager;
    }

    public Date getTbegintime() {
        return this.tbegintime;
    }

    public void setTbegintime(Date tbegintime) {
        this.tbegintime = tbegintime;
    }

    public Date getTendtime() {
        return this.tendtime;
    }

    public void setTendtime(Date tendtime) {
        this.tendtime = tendtime;
    }

    public Date getTeffectivebegintime() {
        return this.teffectivebegintime;
    }

    public void setTeffectivebegintime(Date teffectivebegintime) {
        this.teffectivebegintime = teffectivebegintime;
    }

    public Date getTeffectiveendtime() {
        return this.teffectiveendtime;
    }

    public void setTeffectiveendtime(Date teffectiveendtime) {
        this.teffectiveendtime = teffectiveendtime;
    }

    public Date getTmodifiedtime() {
        return this.tmodifiedtime;
    }

    public void setTmodifiedtime(Date tmodifiedtime) {
        this.tmodifiedtime = tmodifiedtime;
    }

    public Set getResReservations() {
        return this.resReservations;
    }

    public void setResReservations(Set resReservations) {
        this.resReservations = resReservations;
    }

}