package cz.kamenitxan.klient;

import java.io.Serializable;

public class Request implements Serializable{
	String name = "";
	String department = "";
	String place = "";
	String phone = "";

	String createTime = "";
	boolean restart = false;
	String type = "";
	String priority = "";
	String desc = "";

	String os = "";
	String osVersion = "";
	String totalRam = "";
	String freeRam = "";
	String cpu = "";
	String appv = "";

	String actionTime = "";
	String itWorker = "";
	String resolved = "";
	String fixes = "";

	public void setName(String name) {
		this.name = name;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public void setRestart(boolean restart) {
		this.restart = restart;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String isRestart() {
		if (restart) {
			return "True";
		} else {
			return "False";
		}
	}
}
