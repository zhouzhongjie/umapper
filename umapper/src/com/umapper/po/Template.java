package com.umapper.po;

import java.sql.Timestamp;

public class Template {

	private Integer tid;
	private String tname;
	private String tdescrption;
	private String tpath;
	private String createUser;
	private Timestamp createTime;
	public Integer getTid() {
		return tid;
	}
	public void setTid(Integer tid) {
		this.tid = tid;
	}
	public String getTname() {
		return tname;
	}
	public void setTname(String tname) {
		this.tname = tname;
	}
	public String getTdescrption() {
		return tdescrption;
	}
	public void setTdescrption(String tdescrption) {
		this.tdescrption = tdescrption;
	}
	public String getTpath() {
		return tpath;
	}
	public void setTpath(String tpath) {
		this.tpath = tpath;
	}
	public String getCreateUser() {
		return createUser;
	}
	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}
	public Timestamp getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
	
	
}
