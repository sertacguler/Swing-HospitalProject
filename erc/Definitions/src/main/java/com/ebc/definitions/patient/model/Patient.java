package com.ebc.definitions.patient.model;

import java.util.Date;

import com.ebc.core.AqBaseEntity;

public class Patient extends AqBaseEntity {
	private int patientid;
	private String name;
	private String lastname;
	private String gender;
	private String city;
	private String patientno;
	private int age;
	private boolean dirty = false;
	private boolean selected = false;
	private Integer status;
	private Date createAt;
	public Patient() {
	}
	
	public Patient(int patientid, String name, String lastname, String gender,
			String city, int age) {
		super();
		this.patientid = patientid;
		this.name = name;
		this.lastname = lastname;
		this.gender = gender;
		this.city = city;
		this.age = age;
	}

	public int getPatientid() {
		return patientid;
	}
	public void setPatientid(int patientid) {
		this.patientid = patientid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public boolean isDirty() {
		return dirty;
	}
	public void setDirty(boolean dirty) {
		this.dirty = dirty;
	}
	public boolean isSelected() {
		return selected;
	}
	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getPatientno() {
		return patientno;
	}

	public void setPatientno(String patientno) {
		this.patientno = patientno;
	}

	public Date getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}

//	@Override
//	public String toString() {
//		return "Patient [patientid=" + patientid + ", name=" + name
//				+ ", lastname=" + lastname + ", gender=" + gender + ", city="
//				+ city + ", patientno=" + patientno + ", age=" + age
//				+ ", dirty=" + dirty + ", selected=" + selected + ", status="
//				+ status + ", createAt=" + createAt + "]";
//	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return name+ " "+ lastname;
	}


}
