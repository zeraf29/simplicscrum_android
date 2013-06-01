package com.example.androidlogin.component;

public class Project {
	private String title;
	private String desc;
	private String access_key;
	private String pid;
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	private boolean openpj;
	private int rlevel;
	
	/*
	public Project(String t, String d, String a, boolean o, int r){
		title = t;
		desc = d;
		access_key = a;
		openpj = o;
		rlevel = r;
	}
	*/
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getAccess_key() {
		return access_key;
	}
	public void setAccess_key(String access_key) {
		this.access_key = access_key;
	}
	public boolean isOpenpj() {
		return openpj;
	}
	public void setOpenpj(boolean openpj) {
		this.openpj = openpj;
	}
	public int getRlevel() {
		return rlevel;
	}
	public void setRlevel(int rlevel) {
		this.rlevel = rlevel;
	}
	
}
