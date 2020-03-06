package com.ebc.admission.appointment.entity;

public class StaffWrapper {

	private int prsId;
	private String prsName;
	private String prsSurname;
	public StaffWrapper() {
		// TODO Auto-generated constructor stub
	}
	public StaffWrapper(int prsId, String prsName, String prsSurname) {
		super();
		this.prsId = prsId;
		this.prsName = prsName;
		this.prsSurname = prsSurname;
	}
	public int getPrsId() {
		return prsId;
	}
	public void setPrsId(int prsId) {
		this.prsId = prsId;
	}
	public String getPrsName() {
		return prsName;
	}
	public void setPrsName(String prsName) {
		this.prsName = prsName;
	}
	public String getPrsSurname() {
		return prsSurname;
	}
	public void setPrsSurname(String prsSurname) {
		this.prsSurname = prsSurname;
	}
	@Override
	public String toString() {
		return  prsName+" "+prsSurname;
	}
}
