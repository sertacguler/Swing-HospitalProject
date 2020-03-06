package com.ebc.definitions.entity;

import com.ebc.core.AqBaseEntity;

public class TestDefinitionDTO extends AqBaseEntity {

	private String testName;
	private int testId;
	private boolean profile;
	private boolean active;
	private String testCode;
	private boolean selected;
	private String organizationName;
	private boolean status;
	private int testCategoriesId;
	private int minValue;
	private int maxValue;
	private TestCategoriesDTO testCategoriesDTO;
	
	



	public TestDefinitionDTO(String testName, int testId, boolean profile,
			boolean active, String testCode, boolean selected,
			String organizationName, boolean status, int testCategoriesId,
			int minValue, int maxValue, TestCategoriesDTO testCategoriesDTO) {
		
		this.testName = testName;
		this.testId = testId;
		this.profile = profile;
		this.active = active;
		this.testCode = testCode;
		this.selected = selected;
		this.organizationName = organizationName;
		this.status = status;
		this.testCategoriesId = testCategoriesId;
		this.minValue = minValue;
		this.maxValue = maxValue;
		this.testCategoriesDTO = testCategoriesDTO;
	}

	public TestCategoriesDTO getTestCategoriesDTO() {
		return testCategoriesDTO;
	}

	public void setTestCategoriesDTO(TestCategoriesDTO testCategoriesDTO) {
		this.testCategoriesDTO = testCategoriesDTO;
	}

	public int getTestCategoriesId() {
		return testCategoriesId;
	}

	public void setTestCategoriesId(int testCategoriesId) {
		this.testCategoriesId = testCategoriesId;
	}

	public int getMinValue() {
		return minValue;
	}

	public void setMinValue(int minValue) {
		this.minValue = minValue;
	}

	public int getMaxValue() {
		return maxValue;
	}

	public void setMaxValue(int maxValue) {
		this.maxValue = maxValue;
	}

	public String getOrganizationName() {
		return organizationName;
	}

	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	public boolean isProfile() {
		return profile;
	}

	public void setProfile(boolean profile) {
		this.profile = profile;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public int getTestId() {
		return testId;
	}

	public void setTestId(int testId) {
		this.testId = testId;
	}

	public TestDefinitionDTO() {

	}

	public String getTestName() {
		return testName;
	}

	public void setTestName(String testName) {
		this.testName = testName;
	}

	public String getTestCode() {
		return testCode;
	}

	public void setTestCode(String testCode) {
		this.testCode = testCode;
	}

}
