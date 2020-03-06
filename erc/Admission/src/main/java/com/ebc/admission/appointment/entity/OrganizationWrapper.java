package com.ebc.admission.appointment.entity;

public class OrganizationWrapper {

	private int organizationId;
	private String organizationName;
	private String organizationCode;
	public OrganizationWrapper() {
		// TODO Auto-generated constructor stub
	}
	public OrganizationWrapper(int organizationId, String organizationName,
			String organizationCode) {
		super();
		this.organizationId = organizationId;
		this.organizationName = organizationName;
		this.organizationCode = organizationCode;
	}
	public int getOrganizationId() {
		return organizationId;
	}
	public void setOrganizationId(int organizationId) {
		this.organizationId = organizationId;
	}
	public String getOrganizationName() {
		return organizationName;
	}
	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
	}
	public String getOrganizationCode() {
		return organizationCode;
	}
	public void setOrganizationCode(String organizationCode) {
		this.organizationCode = organizationCode;
	}
	@Override
	public String toString() {
		return organizationName;
	}
}
