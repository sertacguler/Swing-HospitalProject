package com.ebc.definitions.staff.entity;

import java.util.Date;

import com.ebc.core.AqBaseEntity;

public class Staff extends AqBaseEntity {

	private int prsId;
	private String prsName;
	private String prsSurname;
	private String prsGender;
	private int prsAge;
	private boolean prsStatus;
	private String prsTask;
	private Date prsBirthdate;
	private String prsEmail;
	private String prsAdress;
	private String prsPhonenumber;
	private String prsHomePhonenumber;
	private Date prsStarteddate;
	private String prsWorkphone;
	private boolean selected;
	private String prsPassword;
	private boolean prsState;
	private int prsOrganizationId;

	public int getPrsOrganizationId() {
		return prsOrganizationId;
	}

	public void setPrsOrganizationId(int prsOrganizationId) {
		this.prsOrganizationId = prsOrganizationId;
	}

	public boolean isPrsState() {
		return prsState;
	}

	public void setPrsState(boolean prsState) {
		this.prsState = prsState;
	}

	public String getPrsPassword() {
		return prsPassword;
	}

	public void setPrsPassword(String prsPassword) {
		this.prsPassword = prsPassword;
	}

	public boolean isSelected() {
		return selected;
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

	public String getPrsGender() {
		return prsGender;
	}

	public void setPrsGender(String prsGender) {
		this.prsGender = prsGender;
	}

	public boolean isPrsStatus() {
		return prsStatus;
	}

	public void setPrsStatus(boolean prsStatus) {
		this.prsStatus = prsStatus;
	}

	public String getPrsTask() {
		return prsTask;
	}

	public void setPrsTask(String prsTask) {
		this.prsTask = prsTask;
	}

	public Date getPrsBirthdate() {
		return prsBirthdate;
	}

	public void setPrsBirthdate(Date prsBirthdate) {
		this.prsBirthdate = prsBirthdate;
	}

	public String getPrsEmail() {
		return prsEmail;
	}

	public void setPrsEmail(String prsEmail) {
		this.prsEmail = prsEmail;
	}

	public String getPrsAdress() {
		return prsAdress;
	}

	public void setPrsAdress(String prsAdress) {
		this.prsAdress = prsAdress;
	}

	public String getPrsPhonenumber() {
		return prsPhonenumber;
	}

	public void setPrsPhonenumber(String prsPhonenumber) {
		this.prsPhonenumber = prsPhonenumber;
	}

	public String getPrsHomePhonenumber() {
		return prsHomePhonenumber;
	}

	public void setPrsHomePhonenumber(String prsHomePhonenumber) {
		this.prsHomePhonenumber = prsHomePhonenumber;
	}

	public Date getPrsStarteddate() {
		return prsStarteddate;
	}

	public void setPrsStarteddate(Date prsStarteddate) {
		this.prsStarteddate = prsStarteddate;
	}

	public String getPrsWorkphone() {
		return prsWorkphone;
	}

	public void setPrsWorkphone(String prsWorkphone) {
		this.prsWorkphone = prsWorkphone;
	}

	public int getPrsAge() {
		return prsAge;
	}

	public void setPrsAge(int prsAge) {
		this.prsAge = prsAge;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	@Override
	public String toString() {
		return getPrsName() + " " + getPrsSurname();
	}

}
