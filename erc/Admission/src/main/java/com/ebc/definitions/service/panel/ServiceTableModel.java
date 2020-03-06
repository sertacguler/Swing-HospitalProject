package com.ebc.definitions.service.panel;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import com.ebc.definitions.service.entity.ServiceDTO;

public class ServiceTableModel extends AbstractTableModel {

	private ArrayList<ServiceDTO> serviceList = new ArrayList<ServiceDTO>();
	String[] columnNames = { "Code", "Name", "Count", "Price", "serviceTypeId" };

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	public String getColumnName(int col) {
		return columnNames[col];
	}

	@Override
	public int getRowCount() {
		return serviceList == null ? 0 : serviceList.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		ServiceDTO serviceDto = serviceList.get(rowIndex);
		if (columnIndex == 0) {
			return serviceDto.getCode();
		} else if (columnIndex == 1) {
			return serviceDto.getName();
		} else if (columnIndex == 2) {
			return serviceDto.getCount();
		} else if (columnIndex == 3) {
			return serviceDto.getPrice();
		} else if (columnIndex == 4) {
			return serviceDto.getServiceTypeId().toString();
		}
		return null;
	}

	public Object getValueAtRow(int row) {
		ServiceDTO typeDto = serviceList.get(row);
		return typeDto;
	}

	public ArrayList<ServiceDTO> getServiceList() {
		return serviceList;
	}

	public void setServiceList(ArrayList<ServiceDTO> serviceList) {
		this.serviceList = serviceList;
	}

}
