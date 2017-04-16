package edu.zju.reservation.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * ResClass entity. @author MyEclipse Persistence Tools
 */

public class ResClass implements java.io.Serializable {

    // Fields

    private Integer cid;
    private ResManager resManager;
    private String cname;
    private Integer cmax;
    private String cremark;
    private String cstatus;
    private Date cmodifiedtime;
    private Set resReservations = new HashSet(0);

    // Constructors

    /**
     * default constructor
     */
    public ResClass() {
    }

    /**
     * minimal constructor
     */
    public ResClass(String cname, Integer cmax, String cremark, String cstatus) {
        this.cname = cname;
        this.cmax = cmax;
        this.cremark = cremark;
        this.cstatus = cstatus;
    }

    /**
     * full constructor
     */
    public ResClass(ResManager resManager, String cname, Integer cmax,
                    String cremark, String cstatus, Date cmodifiedtime,
                    Set resReservations) {
        this.resManager = resManager;
        this.cname = cname;
        this.cmax = cmax;
        this.cremark = cremark;
        this.cstatus = cstatus;
        this.cmodifiedtime = cmodifiedtime;
        this.resReservations = resReservations;
    }

    // Property accessors

    public Integer getCid() {
        return this.cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public ResManager getResManager() {
        return this.resManager;
    }

    public void setResManager(ResManager resManager) {
        this.resManager = resManager;
    }

    public String getCname() {
        return this.cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public Integer getCmax() {
        return this.cmax;
    }

    public void setCmax(Integer cmax) {
        this.cmax = cmax;
    }

    public String getCremark() {
        return this.cremark;
    }

    public void setCremark(String cremark) {
        this.cremark = cremark;
    }

    public String getCstatus() {
        return this.cstatus;
    }

    public void setCstatus(String cstatus) {
        this.cstatus = cstatus;
    }

    public Date getCmodifiedtime() {
        return this.cmodifiedtime;
    }

    public void setCmodifiedtime(Date cmodifiedtime) {
        this.cmodifiedtime = cmodifiedtime;
    }

    public Set getResReservations() {
        return this.resReservations;
    }

    public void setResReservations(Set resReservations) {
        this.resReservations = resReservations;
    }

    @Override
    public String toString() {
        return "ResClass [cid=" + cid + ", resManager=" + resManager
                + ", cname=" + cname + ", cmax=" + cmax + ", cremark="
                + cremark + ", cstatus=" + cstatus + ", cmodifiedtime="
                + cmodifiedtime + ", resReservations=" + resReservations + "]";
    }
}