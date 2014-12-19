package cz.kamenitxan.klient;

public class Request {
	String name = "";
	String department = "";
	String place = "";
	String phone = "";
	boolean restart = false;
	String type = "";
	String desc = "";

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
