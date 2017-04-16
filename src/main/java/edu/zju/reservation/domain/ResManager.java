package edu.zju.reservation.domain;

import java.util.HashSet;
import java.util.Set;

/**
 * ResManager entity. @author MyEclipse Persistence Tools
 */

public class ResManager implements java.io.Serializable {

    // Fields

    private Integer mid;
    private String maccount;
    private String mpassword;
    private String mremark;
    private Set resClasses = new HashSet(0);
    private Set resTimequantums = new HashSet(0);

    // Constructors

    /**
     * default constructor
     */
    public ResManager() {
    }

    /**
     * minimal constructor
     */
    public ResManager(String maccount, String mpassword) {
        this.maccount = maccount;
        this.mpassword = mpassword;
    }

    /**
     * full constructor
     */
    public ResManager(String maccount, String mpassword, String mremark,
                      Set resClasses, Set resTimequantums) {
        this.maccount = maccount;
        this.mpassword = mpassword;
        this.mremark = mremark;
        this.resClasses = resClasses;
        this.resTimequantums = resTimequantums;
    }

    // Property accessors

    public Integer getMid() {
        return this.mid;
    }

    public void setMid(Integer mid) {
        this.mid = mid;
    }

    public String getMaccount() {
        return this.maccount;
    }

    public void setMaccount(String maccount) {
        this.maccount = maccount;
    }

    public String getMpassword() {
        return this.mpassword;
    }

    public void setMpassword(String mpassword) {
        this.mpassword = mpassword;
    }

    public String getMremark() {
        return this.mremark;
    }

    public void setMremark(String mremark) {
        this.mremark = mremark;
    }

    public Set getResClasses() {
        return this.resClasses;
    }

    public void setResClasses(Set resClasses) {
        this.resClasses = resClasses;
    }

    public Set getResTimequantums() {
        return this.resTimequantums;
    }

    public void setResTimequantums(Set resTimequantums) {
        this.resTimequantums = resTimequantums;
    }

    @Override
    public String toString() {
        return "ResManager [mid=" + mid + ", maccount=" + maccount
                + ", mpassword=" + mpassword + ", mremark=" + mremark
                + ", resClasses=" + resClasses + ", resTimequantums="
                + resTimequantums + "]";
    }

}