package com.ebc.definitions.service.entity;

import com.ebc.admission.serviceType.entity.ServiceTypeDto;
import com.ebc.core.AqBaseEntity;

public class ServiceDTO extends AqBaseEntity {

	private Long serviceId;
	private String code;
	private String name;
	private String count;
	private String price;
	private Long serviceTypeId;
	private ServiceTypeDto serviceTypeDto;
	private boolean status;

	public Long getServiceId() {
		return serviceId;
	}

	public void setServiceId(Long serviceId) {
		this.serviceId = serviceId;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public Long getServiceTypeId() {
		return serviceTypeId;
	}

	public void setServiceTypeId(Long serviceTypeId) {
		this.serviceTypeId = serviceTypeId;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public ServiceTypeDto getServiceTypeDto() {
		return serviceTypeDto;
	}

	public void setServiceTypeDto(ServiceTypeDto serviceTypeDto) {
		this.serviceTypeDto = serviceTypeDto;
	}

	@Override
	public String toString() {
		return "ServiceDTO [serviceId=" + serviceId + ", code=" + code
				+ ", name=" + name + ", count=" + count + ", price=" + price
				+ ", serviceTypeId=" + serviceTypeId + ", status=" + status
				+ "]";
	}

}
