package com.ebc.admission.serviceType.panel;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import com.ebc.admission.appointment.entity.AppointmentDto;
import com.ebc.admission.serviceType.entity.ServiceTypeDto;

public class ServiceTypeTableModel extends AbstractTableModel {
	private ArrayList<ServiceTypeDto> typeDtos = new ArrayList<ServiceTypeDto>();
	String[] columnNames = {"Service Type No" ,"Name"};

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

//	public Class<?> getColumnClass(int columnIndex) {
//		if (columnIndex==0)
//			return Boolean.class;
//		else
//			return super.getColumnClass(columnIndex);
//	}
	
	@Override
	public int getRowCount() {
		return typeDtos==null ? 0 : typeDtos.size();
	}


	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		ServiceTypeDto typeDto = typeDtos.get(rowIndex);
		if (columnIndex == 0) {
			return typeDto.getServiceTypeId();
		} else if (columnIndex == 1) {
			return typeDto.getName();
		}
		return null;
	}
	@Override
	public String getColumnName(int col) {
		return columnNames[col];
	}
	

	public Object getValueAtRow(int row) {
		ServiceTypeDto typeDto = typeDtos.get(row);
		return typeDto;
	}
//	@Override
//	public void setValueAt(Object value, int rowIndex, int columnIndex) {
//		AppointmentDto appointmentDto = appointmentDtos.get(rowIndex);
//		if (value instanceof Boolean) {
//			if (columnIndex == 0) {
//				appointmentDto.setSelect(((Boolean) value).booleanValue());
//				
//			}
//		}
//	}

	public ArrayList<ServiceTypeDto> getTypeDtos() {
		return typeDtos;
	}

	public void setTypeDtos(ArrayList<ServiceTypeDto> typeDtos) {
		this.typeDtos = typeDtos;
	}
	

}
