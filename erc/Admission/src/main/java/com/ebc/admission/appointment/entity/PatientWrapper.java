package com.ebc.admission.appointment.entity;

public class PatientWrapper {
    private int patientid;
    private String name;
    private String lastname;
	public PatientWrapper() {
		// TODO Auto-generated constructor stub
	}
	
    public PatientWrapper(int patientid, String name, String lastname) {
		super();
		this.patientid = patientid;
		this.name = name;
		this.lastname = lastname;
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
	@Override
    public String toString() {
        return name+" "+lastname;
    }
}
