package com.ebc.definitions.admission.entity;

import java.util.Date;

import com.ebc.core.AqBaseEntity;
import com.ebc.definitions.organization.entity.OrganizationDTO;
import com.ebc.definitions.patient.model.Patient;
import com.ebc.definitions.staff.entity.Staff;

public class AdmissionDTO extends AqBaseEntity {

	private int admissionid;

	private int patientid;
	private int organizationid;
	private int staffId;

	private Date admissionDate;
	private String admissionType;
	private String admissionNo;
	private int admissionStatus;

	private Staff staff;
	private Patient patient;
	private OrganizationDTO organizationDTO;

	private String cancelReason;

	private boolean status;

	public int getAdmissionid() {
		return admissionid;
	}

	public void setAdmissionid(int admissionid) {
		this.admissionid = admissionid;
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

	public int getStaffId() {
		return staffId;
	}

	public void setStaffId(int staffId) {
		this.staffId = staffId;
	}

	public Date getAdmissionDate() {
		return admissionDate;
	}

	public void setAdmissionDate(Date admissionDate) {
		this.admissionDate = admissionDate;
	}

	public String getAdmissionType() {
		return admissionType;
	}

	public void setAdmissionType(String admissionType) {
		this.admissionType = admissionType;
	}

	public String getAdmissionNo() {
		return admissionNo;
	}

	public void setAdmissionNo(String admissionNo) {
		this.admissionNo = admissionNo;
	}

	public int getAdmissionStatus() {
		return admissionStatus;
	}

	public void setAdmissionStatus(int admissionStatus) {
		this.admissionStatus = admissionStatus;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
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

	public String getCancelReason() {
		return cancelReason;
	}

	public void setCancelReason(String cancelReason) {
		this.cancelReason = cancelReason;
	}

	@Override
	public String toString() {
		return "AdmissionDTO [admissionid=" + admissionid + ", patientid="
				+ patientid + ", organizationid=" + organizationid
				+ ", staffId=" + staffId + ", admissionDate=" + admissionDate
				+ ", admissionType=" + admissionType + ", staff=" + staff
				+ ", patient=" + patient + ", organizationDTO="
				+ organizationDTO + ", status=" + status + "]";
	}

}
