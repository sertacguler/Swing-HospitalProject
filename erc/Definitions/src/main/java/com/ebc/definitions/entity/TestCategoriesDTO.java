package com.ebc.definitions.entity;

import com.ebc.core.AqBaseEntity;

public class TestCategoriesDTO extends AqBaseEntity {
	private int testCategoriesId;
	private String testCategoriesName;
	private boolean status;
	
	
	public TestCategoriesDTO() {
		
	}
	
	
	public TestCategoriesDTO(int testCategoriesId, String testCategoriesName,
			boolean status) {
		
		this.testCategoriesId = testCategoriesId;
		this.testCategoriesName = testCategoriesName;
		this.status = status;
	}
	public int getTestCategoriesId() {
		return testCategoriesId;
	}
	public void setTestCategoriesId(int testCategoriesId) {
		this.testCategoriesId = testCategoriesId;
	}
	public String getTestCategoriesName() {
		return testCategoriesName;
	}
	public void setTestCategoriesName(String testCategoriesName) {
		this.testCategoriesName = testCategoriesName;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	
	
	@Override
	public String toString() {
		
		return getTestCategoriesName();
	}
}
