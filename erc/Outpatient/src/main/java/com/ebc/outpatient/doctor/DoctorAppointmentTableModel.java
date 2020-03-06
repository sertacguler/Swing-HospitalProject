package com.ebc.outpatient.doctor;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

public class DoctorAppointmentTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 1L;

	public ArrayList<OutpatientInfoDTO> outpatientInfoDTOs = new ArrayList<OutpatientInfoDTO>();
	
	public ArrayList<OutpatientInfoDTO> getOutpatientInfoDTOs() {
		return outpatientInfoDTOs;
	}

	public void setOutpatientInfoDTOs(
			ArrayList<OutpatientInfoDTO> outpatientInfoDTOs) {
		this.outpatientInfoDTOs = outpatientInfoDTOs;
	}

	String[] columnNames = { "Patient No", "Patient Name", };

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	public int getRowCount() {
		return outpatientInfoDTOs == null ? 0 : outpatientInfoDTOs.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		
		OutpatientInfoDTO outpatientInfoDTO = outpatientInfoDTOs.get(rowIndex);
		if(outpatientInfoDTO==null){
			return "";
		}
		if (columnIndex == 0)
			return outpatientInfoDTO.getPatientDTO().getPatientno();
		else if (columnIndex == 1)
			return outpatientInfoDTO.getPatientDTO().getName() + " " + outpatientInfoDTO.getPatientDTO().getLastname();

		return null;
	}

	public Object getValueAtRow(int row) {
		OutpatientInfoDTO outpatientInfoDTO = outpatientInfoDTOs.get(row);
		return outpatientInfoDTO;
	}

	public boolean isCellEditable(int row, int col) {
		return false;
	}

	@Override
	public String getColumnName(int col) {
		return columnNames[col];
	}

}
