package com.ebc.admission.appointment.entity;

import java.util.Date;

import com.ebc.core.AqBaseEntity;
import com.ebc.definitions.organization.entity.OrganizationDTO;
import com.ebc.definitions.patient.model.Patient;
import com.ebc.definitions.staff.entity.Staff;

public class AppointmentDto extends AqBaseEntity {

	private int appointmentid;
	private Date createdAt;
	private int staffid;
	private int patientid;
	private int organizationid;
	private int admissionid;
	private Integer status;
	private boolean select = false;
	private OrganizationDTO organizationDTO;
	private Staff staff;
	private Patient patient;

	public AppointmentDto() {
	}

	public AppointmentDto(Date createdAt, int staffid, int patientid,
			int organizationid, Integer status) {
		super();
		this.createdAt = createdAt;
		this.staffid = staffid;
		this.patientid = patientid;
		this.organizationid = organizationid;
		this.status = status;
	}

	public AppointmentDto(Date createdAt, int staffid, int patientid,
			int organizationid, int admissionid, Integer status,
			boolean select, OrganizationDTO organizationDTO, Staff staff,
			Patient patient) {
		super();
		this.createdAt = createdAt;
		this.staffid = staffid;
		this.patientid = patientid;
		this.organizationid = organizationid;
		this.admissionid = admissionid;
		this.status = status;
		this.select = select;
		this.organizationDTO = organizationDTO;
		this.staff = staff;
		this.patient = patient;
	}

	public int getAdmissionid() {
		return admissionid;
	}

	public void setAdmissionid(int admissionid) {
		this.admissionid = admissionid;
	}

	public int getAppointmentid() {
		return appointmentid;
	}

	public void setAppointmentid(int appointmentid) {
		this.appointmentid = appointmentid;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public int getStaffid() {
		return staffid;
	}

	public void setStaffid(int staffid) {
		this.staffid = staffid;
	}

	public int getPatientid() {
		return patientid;
	}

	public void setPatientid(int patientid) {
		this.patientid = patientid;
	}

	public int getOrganizationid() {
		return organizationid;
	}

	public void setOrganizationid(int organizationid) {
		this.organizationid = organizationid;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public boolean isSelect() {
		return select;
	}

	public void setSelect(boolean select) {
		this.select = select;
	}

	public OrganizationDTO getOrganizationDTO() {
		return organizationDTO;
	}

	public void setOrganizationDTO(OrganizationDTO organizationDTO) {
		this.organizationDTO = organizationDTO;
	}

	public Staff getStaff() {
		return staff;
	}

	public void setStaff(Staff staff) {
		this.staff = staff;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	@Override
	public String toString() {
		return "AppointmentDto [appointmentid=" + appointmentid
				+ ", createdAt=" + createdAt + ", staffid=" + staffid
				+ ", patientid=" + patientid + ", organizationid="
				+ organizationid + ", status=" + status + ", select=" + select
				+ "]";
	}
}
