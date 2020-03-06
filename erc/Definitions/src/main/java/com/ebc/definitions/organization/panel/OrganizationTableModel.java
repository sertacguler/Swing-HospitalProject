package com.ebc.definitions.organization.panel;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import com.ebc.definitions.organization.entity.OrganizationDTO;

public class OrganizationTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 1L;

	private ArrayList<OrganizationDTO> orgDtoList = new ArrayList<OrganizationDTO>();

	private String[] culumnNames = { "Organization Code", "Organization Name" };

	public OrganizationTableModel() {
	}

	@Override
	public String getColumnName(int col) {
		return culumnNames[col];
	}

	@Override
	public int getColumnCount() {
		return culumnNames.length;
	}

	@Override
	public int getRowCount() {
		return orgDtoList == null ? 0 : orgDtoList.size();
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		return super.getColumnClass(columnIndex);
	}

	public boolean isCellEditable(int row, int col) {
		return false;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {

		OrganizationDTO orgDto = orgDtoList.get(rowIndex);
		if (columnIndex == 0) {
			return orgDto.getOrganizationCode();
		} else if (columnIndex == 1) {
			return orgDto.getOrganizationName();
		}
		return null;
	}

	public ArrayList<OrganizationDTO> getOrgDto() {
		return orgDtoList;
	}

	public void setOrgDto(ArrayList<OrganizationDTO> orgDto) {
		this.orgDtoList = orgDto;
	}

}
