package edu.zju.reservation.domain;

/**
 * ResManager entity. @author MyEclipse Persistence Tools
 */

public class ResConfig implements java.io.Serializable {

	// Fields

	private Integer cid;
	private String ckey;
	private String cvalue;

	public ResConfig() {

	}

	public ResConfig(String ckey, String cvalue) {
		this.ckey = ckey;
		this.cvalue = cvalue;
	}

	public ResConfig(Integer cid, String ckey, String cvalue) {
		this.cid = cid;
		this.ckey = ckey;
		this.cvalue = cvalue;
	}

	public Integer getCid() {
		return cid;
	}

	public void setCid(Integer cid) {
		this.cid = cid;
	}

	public String getCkey() {
		return ckey;
	}

	public void setCkey(String ckey) {
		this.ckey = ckey;
	}

	public String getCvalue() {
		return cvalue;
	}

	public void setCvalue(String cvalue) {
		this.cvalue = cvalue;
	}

	@Override
	public String toString() {
		return "ResConfig [cid=" + cid + ", ckey=" + ckey + ", cvalue="
				+ cvalue + "]";
	}

}