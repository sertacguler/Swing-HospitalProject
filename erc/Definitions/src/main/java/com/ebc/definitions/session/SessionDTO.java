package com.ebc.definitions.session;

import java.util.Date;

import com.ebc.core.AqBaseEntity;
import com.ebc.definitions.staff.entity.Staff;

public class SessionDTO extends AqBaseEntity {

	private int sessionid;
	private int staffid;
	private Date logindate;
	private Date logoutdate;
	private Staff staffdto;

	public int getStaffid() {
		return staffid;
	}

	public void setStaffid(int staffid) {
		this.staffid = staffid;
	}

	public int getSessionid() {
		return sessionid;
	}

	public void setSessionid(int sessionid) {
		this.sessionid = sessionid;
	}

	public Staff getStaffdto() {
		return staffdto;
	}

	public void setStaffdto(Staff staffdto) {
		this.staffdto = staffdto;
	}

	public Date getLogindate() {
		return logindate;
	}

	public void setLogindate(Date logindate) {
		this.logindate = logindate;
	}

	public Date getLogoutdate() {
		return logoutdate;
	}

	public void setLogoutdate(Date logoutdate) {
		this.logoutdate = logoutdate;
	}

}
