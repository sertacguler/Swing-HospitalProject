package com.ebc.outpatient.protocol;

import java.util.Date;

import com.ebc.core.AqBaseEntity;

public class ProtocolDTO extends AqBaseEntity {

	private int medicalOrderId;
	private String module;
	private Long orderNo;
	private Long admissionId;
	private Long organizationId;
	private Long doctorId;
	private boolean status;
	private Date dateCreated;
	private Long userCreatedId;
	private Date dateUpdated;
	private Long userUpdatedId;
	private Date dischargeDate;
	private Long dischargeTypeId;
	private String dischargeNote;
	private Long dischargeUserId;

	public int getMedicalOrderId() {
		return medicalOrderId;
	}

	public void setMedicalOrderId(int medicalOrderId) {
		this.medicalOrderId = medicalOrderId;
	}

	public String getModule() {
		return module;
	}

	public void setModule(String module) {
		this.module = module;
	}

	public Long getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(Long orderNo) {
		this.orderNo = orderNo;
	}

	public Long getAdmissionId() {
		return admissionId;
	}

	public void setAdmissionId(Long admissionId) {
		this.admissionId = admissionId;
	}

	public Long getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Long organizationId) {
		this.organizationId = organizationId;
	}

	public Long getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(Long doctorId) {
		this.doctorId = doctorId;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	public Long getUserCreatedId() {
		return userCreatedId;
	}

	public void setUserCreatedId(Long userCreatedId) {
		this.userCreatedId = userCreatedId;
	}

	public Date getDateUpdated() {
		return dateUpdated;
	}

	public void setDateUpdated(Date dateUpdated) {
		this.dateUpdated = dateUpdated;
	}

	public Long getUserUpdatedId() {
		return userUpdatedId;
	}

	public void setUserUpdatedId(Long userUpdatedId) {
		this.userUpdatedId = userUpdatedId;
	}

	public Date getDischargeDate() {
		return dischargeDate;
	}

	public void setDischargeDate(Date dischargeDate) {
		this.dischargeDate = dischargeDate;
	}

	public Long getDischargeTypeId() {
		return dischargeTypeId;
	}

	public void setDischargeTypeId(Long dischargeTypeId) {
		this.dischargeTypeId = dischargeTypeId;
	}

	public String getDischargeNote() {
		return dischargeNote;
	}

	public void setDischargeNote(String dischargeNote) {
		this.dischargeNote = dischargeNote;
	}

	public Long getDischargeUserId() {
		return dischargeUserId;
	}

	public void setDischargeUserId(Long dischargeUserId) {
		this.dischargeUserId = dischargeUserId;
	}

}
