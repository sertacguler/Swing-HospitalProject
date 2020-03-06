package com.ebc.outpatient.anamnez.entity;

import com.ebc.core.AqBaseEntity;
import com.ebc.definitions.staff.entity.Staff;

public class Anamnez extends AqBaseEntity {

	private Long anamnezId;
	private Long admissionId;
	private String detail;

	private String createdAt;
	private String updatedAt;

	private Long createdBy;
	private Long updatedBy;

	private Staff staff;

	private Boolean status;

	public Long getAnamnezId() {
		return anamnezId;
	}

	public void setAnamnezId(Long anamnezId) {
		this.anamnezId = anamnezId;
	}

	public Long getAdmissionId() {
		return admissionId;
	}

	public void setAdmissionId(Long admissionId) {
		this.admissionId = admissionId;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public Long getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}

	public Long getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(Long updatedBy) {
		this.updatedBy = updatedBy;
	}

	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

	public String getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(String updatedAt) {
		this.updatedAt = updatedAt;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public Staff getStaff() {
		return staff;
	}

	public void setStaff(Staff staff) {
		this.staff = staff;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Anamnez [anamnezId=" + anamnezId + ", admissionId="
				+ admissionId + ", detail=" + detail + ", createdAt="
				+ createdAt + ", updatedAt=" + updatedAt + ", createdBy="
				+ createdBy + ", updatedBy=" + updatedBy + ", staff=" + staff
				+ ", status=" + status + "]";
	}

}
