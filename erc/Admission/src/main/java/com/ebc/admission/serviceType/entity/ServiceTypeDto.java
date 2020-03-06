package com.ebc.admission.serviceType.entity;

import com.ebc.core.AqBaseEntity;

public class ServiceTypeDto  extends AqBaseEntity{
	private Long serviceTypeId;
	private String name;
	private Boolean status;
	public ServiceTypeDto() {
	}
	public Long getServiceTypeId() {
		return serviceTypeId;
	}
	public void setServiceTypeId(Long serviceTypeId) {
		this.serviceTypeId = serviceTypeId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public Boolean getStatus() {
		return status;
	}
	public void setStatus(Boolean status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "ServiceTypeDto [serviceTypeId=" + serviceTypeId + ", name="
				+ name + ", status=" + status + "]";
	}
}
