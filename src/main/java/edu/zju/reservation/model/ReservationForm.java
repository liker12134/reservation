package edu.zju.reservation.model;

public class ReservationForm {
    private Integer sid; // 学生ID
    private Integer cid; // 教室ID
    private String rtargetdate; // 预约时间的字符串形式：yyyy-MM-dd
    private Integer timequantumid; // 预约时间段ID
    private String rspecificbegintime; // 具体起始时间
    private String rspecificendtime; // 具体结束时间
    private String rapplyunit; // 申请单位
    private String rchargeperson; // 负责人（当前用户）
    private String rchargetelephone; // 负责人电话
    private String rcontactsperson; // 联系人
    private String rcontacttelephone; // 联系人电话
    private String rspecificcontent; // 详细内容

    public ReservationForm() {

    }

    public ReservationForm(Integer sid, Integer cid, String rtargetdate,
                           Integer timequantumid, String rspecificbegintime,
                           String rspecificendtime, String rapplyunit, String rchargeperson,
                           String rchargetelephone, String rcontactsperson,
                           String rcontacttelephone, String rspecificcontent) {
        this.sid = sid;
        this.cid = cid;
        this.rtargetdate = rtargetdate;
        this.timequantumid = timequantumid;
        this.rspecificbegintime = rspecificbegintime;
        this.rspecificendtime = rspecificendtime;
        this.rapplyunit = rapplyunit;
        this.rchargeperson = rchargeperson;
        this.rchargetelephone = rchargetelephone;
        this.rcontactsperson = rcontactsperson;
        this.rcontacttelephone = rcontacttelephone;
        this.rspecificcontent = rspecificcontent;
    }

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public String getRtargetdate() {
        return rtargetdate;
    }

    public void setRtargetdate(String rtargetdate) {
        this.rtargetdate = rtargetdate;
    }

    public Integer getTimequantumid() {
        return timequantumid;
    }

    public void setTimequantumid(Integer timequantumid) {
        this.timequantumid = timequantumid;
    }

    public String getRspecificbegintime() {
        return rspecificbegintime;
    }

    public void setRspecificbegintime(String rspecificbegintime) {
        this.rspecificbegintime = rspecificbegintime;
    }

    public String getRspecificendtime() {
        return rspecificendtime;
    }

    public void setRspecificendtime(String rspecificendtime) {
        this.rspecificendtime = rspecificendtime;
    }

    public String getRapplyunit() {
        return rapplyunit;
    }

    public void setRapplyunit(String rapplyunit) {
        this.rapplyunit = rapplyunit;
    }

    public String getRchargeperson() {
        return rchargeperson;
    }

    public void setRchargeperson(String rchargeperson) {
        this.rchargeperson = rchargeperson;
    }

    public String getRchargetelephone() {
        return rchargetelephone;
    }

    public void setRchargetelephone(String rchargetelephone) {
        this.rchargetelephone = rchargetelephone;
    }

    public String getRcontactsperson() {
        return rcontactsperson;
    }

    public void setRcontactsperson(String rcontactsperson) {
        this.rcontactsperson = rcontactsperson;
    }

    public String getRcontacttelephone() {
        return rcontacttelephone;
    }

    public void setRcontacttelephone(String rcontacttelephone) {
        this.rcontacttelephone = rcontacttelephone;
    }

    public String getRspecificcontent() {
        return rspecificcontent;
    }

    public void setRspecificcontent(String rspecificcontent) {
        this.rspecificcontent = rspecificcontent;
    }

    @Override
    public String toString() {
        return "ReservationForm [cid=" + cid + ", rtargetdate=" + rtargetdate
                + ", timequantumid=" + timequantumid + ", rspecificbegintime="
                + rspecificbegintime + ", rspecificendtime=" + rspecificendtime
                + ", rapplyunit=" + rapplyunit + ", rchargeperson="
                + rchargeperson + ", rchargetelephone=" + rchargetelephone
                + ", rcontactsperson=" + rcontactsperson
                + ", rcontacttelephone=" + rcontacttelephone
                + ", rspecificcontent=" + rspecificcontent + "]";
    }

}
