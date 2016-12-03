package edu.zju.reservation.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * ResStudent entity. @author MyEclipse Persistence Tools
 */

public class ResStudent implements java.io.Serializable {

	// Fields

	private Integer sid;
	private String sno;
	private String spassword;
	private String sname;
	private String ssex;
	private String smajor;
	private String sclasscode;
	private String sphone;
	private String sremark;
	private Date slastlogin;
	private Integer slogincount;
	private String status;
	private Set resReservations = new HashSet(0);

	// Constructors

	/** default constructor */
	public ResStudent() {
	}

	/** minimal constructor */
	public ResStudent(String sno, String spassword, String sname, String ssex,
			String smajor, String sclasscode, String sphone, String status) {
		this.sno = sno;
		this.spassword = spassword;
		this.sname = sname;
		this.ssex = ssex;
		this.smajor = smajor;
		this.sclasscode = sclasscode;
		this.sphone = sphone;
		this.status = status;
	}

	/** full constructor */
	public ResStudent(String sno, String spassword, String sname, String ssex,
			String smajor, String sclasscode, String sphone, String sremark,
			Date slastlogin, Integer slogincount, String status,
			Set resReservations) {
		this.sno = sno;
		this.spassword = spassword;
		this.sname = sname;
		this.ssex = ssex;
		this.smajor = smajor;
		this.sclasscode = sclasscode;
		this.sphone = sphone;
		this.sremark = sremark;
		this.slastlogin = slastlogin;
		this.slogincount = slogincount;
		this.status = status;
		this.resReservations = resReservations;
	}

	// Property accessors

	public Integer getSid() {
		return this.sid;
	}

	public void setSid(Integer sid) {
		this.sid = sid;
	}

	public String getSno() {
		return this.sno;
	}

	public void setSno(String sno) {
		this.sno = sno;
	}

	public String getSpassword() {
		return this.spassword;
	}

	public void setSpassword(String spassword) {
		this.spassword = spassword;
	}

	public String getSname() {
		return this.sname;
	}

	public void setSname(String sname) {
		this.sname = sname;
	}

	public String getSsex() {
		return this.ssex;
	}

	public void setSsex(String ssex) {
		this.ssex = ssex;
	}

	public String getSmajor() {
		return this.smajor;
	}

	public void setSmajor(String smajor) {
		this.smajor = smajor;
	}

	public String getSclasscode() {
		return this.sclasscode;
	}

	public void setSclasscode(String sclasscode) {
		this.sclasscode = sclasscode;
	}

	public String getSphone() {
		return this.sphone;
	}

	public void setSphone(String sphone) {
		this.sphone = sphone;
	}

	public String getSremark() {
		return this.sremark;
	}

	public void setSremark(String sremark) {
		this.sremark = sremark;
	}

	public Date getSlastlogin() {
		return this.slastlogin;
	}

	public void setSlastlogin(Date slastlogin) {
		this.slastlogin = slastlogin;
	}

	public Integer getSlogincount() {
		return this.slogincount;
	}

	public void setSlogincount(Integer slogincount) {
		this.slogincount = slogincount;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Set getResReservations() {
		return this.resReservations;
	}

	public void setResReservations(Set resReservations) {
		this.resReservations = resReservations;
	}

	@Override
	public String toString() {
		return "ResStudent [sid=" + sid + ", sno=" + sno + ", spassword="
				+ spassword + ", sname=" + sname + ", ssex=" + ssex
				+ ", smajor=" + smajor + ", sclasscode=" + sclasscode
				+ ", sphone=" + sphone + ", sremark=" + sremark
				+ ", slastlogin=" + slastlogin + ", slogincount=" + slogincount
				+ ", status=" + status + ", resReservations=" + resReservations
				+ "]";
	}

}