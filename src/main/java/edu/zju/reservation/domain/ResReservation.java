package edu.zju.reservation.domain;

import java.util.Date;

/**
 * ResReservation entity. @author MyEclipse Persistence Tools
 */

public class ResReservation implements java.io.Serializable {

    // Fields

    private Long rid;
    private ResStudent resStudent;
    private ResTimequantum resTimequantum;
    private ResClass resClass;
    private String cname;
    private Date rtargetdate;
    private Integer rweeknum;
    private Date tbegintime;
    private Date tendtime;
    private Date rspecificbegintime;
    private Date rspecificendtime;
    private Date rrecordcreatetime;
    private String rapplyunit;
    private String rchargeperson;
    private String rchargetelephone;
    private String rcontactsperson;
    private String rcontacttelephone;
    private String rspecificcontent;
    private String rstatus;

    // Constructors

    /**
     * default constructor
     */
    public ResReservation() {
    }

    /**
     * minimal constructor
     */
    public ResReservation(ResTimequantum resTimequantum, String cname,
                          Date rtargetdate, Integer rweeknum, Date tbegintime, Date tendtime,
                          String rchargeperson, String rchargetelephone, String rstatus) {
        this.resTimequantum = resTimequantum;
        this.cname = cname;
        this.rtargetdate = rtargetdate;
        this.rweeknum = rweeknum;
        this.tbegintime = tbegintime;
        this.tendtime = tendtime;
        this.rchargeperson = rchargeperson;
        this.rchargetelephone = rchargetelephone;
        this.rstatus = rstatus;
    }

    /**
     * full constructor
     */
    public ResReservation(ResStudent resStudent, ResTimequantum resTimequantum,
                          ResClass resClass, String cname, Date rtargetdate,
                          Integer rweeknum, Date tbegintime, Date tendtime,
                          Date rspecificbegintime, Date rspecificendtime,
                          Date rrecordcreatetime, String rapplyunit, String rchargeperson,
                          String rchargetelephone, String rcontactsperson,
                          String rcontacttelephone, String rspecificcontent, String rstatus) {
        this.resStudent = resStudent;
        this.resTimequantum = resTimequantum;
        this.resClass = resClass;
        this.cname = cname;
        this.rtargetdate = rtargetdate;
        this.rweeknum = rweeknum;
        this.tbegintime = tbegintime;
        this.tendtime = tendtime;
        this.rspecificbegintime = rspecificbegintime;
        this.rspecificendtime = rspecificendtime;
        this.rrecordcreatetime = rrecordcreatetime;
        this.rapplyunit = rapplyunit;
        this.rchargeperson = rchargeperson;
        this.rchargetelephone = rchargetelephone;
        this.rcontactsperson = rcontactsperson;
        this.rcontacttelephone = rcontacttelephone;
        this.rspecificcontent = rspecificcontent;
        this.rstatus = rstatus;
    }

    // Property accessors

    public Long getRid() {
        return this.rid;
    }

    public void setRid(Long rid) {
        this.rid = rid;
    }

    public ResStudent getResStudent() {
        return this.resStudent;
    }

    public void setResStudent(ResStudent resStudent) {
        this.resStudent = resStudent;
    }

    public ResTimequantum getResTimequantum() {
        return this.resTimequantum;
    }

    public void setResTimequantum(ResTimequantum resTimequantum) {
        this.resTimequantum = resTimequantum;
    }

    public ResClass getResClass() {
        return this.resClass;
    }

    public void setResClass(ResClass resClass) {
        this.resClass = resClass;
    }

    public String getCname() {
        return this.cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public Date getRtargetdate() {
        return this.rtargetdate;
    }

    public void setRtargetdate(Date rtargetdate) {
        this.rtargetdate = rtargetdate;
    }

    public Integer getRweeknum() {
        return this.rweeknum;
    }

    public void setRweeknum(Integer rweeknum) {
        this.rweeknum = rweeknum;
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

    public Date getRspecificbegintime() {
        return this.rspecificbegintime;
    }

    public void setRspecificbegintime(Date rspecificbegintime) {
        this.rspecificbegintime = rspecificbegintime;
    }

    public Date getRspecificendtime() {
        return this.rspecificendtime;
    }

    public void setRspecificendtime(Date rspecificendtime) {
        this.rspecificendtime = rspecificendtime;
    }

    public Date getRrecordcreatetime() {
        return this.rrecordcreatetime;
    }

    public void setRrecordcreatetime(Date rrecordcreatetime) {
        this.rrecordcreatetime = rrecordcreatetime;
    }

    public String getRapplyunit() {
        return this.rapplyunit;
    }

    public void setRapplyunit(String rapplyunit) {
        this.rapplyunit = rapplyunit;
    }

    public String getRchargeperson() {
        return this.rchargeperson;
    }

    public void setRchargeperson(String rchargeperson) {
        this.rchargeperson = rchargeperson;
    }

    public String getRchargetelephone() {
        return this.rchargetelephone;
    }

    public void setRchargetelephone(String rchargetelephone) {
        this.rchargetelephone = rchargetelephone;
    }

    public String getRcontactsperson() {
        return this.rcontactsperson;
    }

    public void setRcontactsperson(String rcontactsperson) {
        this.rcontactsperson = rcontactsperson;
    }

    public String getRcontacttelephone() {
        return this.rcontacttelephone;
    }

    public void setRcontacttelephone(String rcontacttelephone) {
        this.rcontacttelephone = rcontacttelephone;
    }

    public String getRspecificcontent() {
        return this.rspecificcontent;
    }

    public void setRspecificcontent(String rspecificcontent) {
        this.rspecificcontent = rspecificcontent;
    }

    public String getRstatus() {
        return this.rstatus;
    }

    public void setRstatus(String rstatus) {
        this.rstatus = rstatus;
    }

}