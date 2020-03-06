package com.ebc.definitions.admission.panel;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import com.ebc.definitions.admission.entity.AdmissionDTO;
import com.ebc.definitions.admission.entity.AdmissionStatus;

public class AdmissionTableModel extends AbstractTableModel {

	private ArrayList<AdmissionDTO> adDtoList = new ArrayList<AdmissionDTO>();

	private String[] culumnNames = { "Admission No", "Organization Name",
			"Doctor Name", "Admission Type", "Admission Date", "Admission Status" };

	public AdmissionTableModel() {
	}

	public String getColumnName(int col) {
		return culumnNames[col];
	}

	public int getColumnCount() {
		return culumnNames.length;
	}

	public int getRowCount() {
		return adDtoList == null ? 0 : adDtoList.size();
	}

	public Object getValueAt(int rowIndex, int columnIndex) {

		AdmissionDTO adDto = adDtoList.get(rowIndex);
		if (columnIndex == 0) {
			return adDto.getAdmissionNo();
		} else if (columnIndex == 1) {
			return adDto.getOrganizationDTO().getOrganizationName();
		} else if (columnIndex == 2) {
			return adDto.getStaff().getPrsName() + " "
					+ adDto.getStaff().getPrsSurname();
		} else if (columnIndex == 3) {
			return adDto.getAdmissionType();
		} else if (columnIndex == 4) {
			return adDto.getAdmissionDate();
		} else if (columnIndex == 5) {
			switch (adDto.getAdmissionStatus()) {
			case 0:
				return "OPEN";
			case 1:
				return "CLOSE";
			case 2:
				return "CANCEL";
			}
		} else if (columnIndex == 6) {
			return adDto.getAdmissionDate();
		}
		return null;
	}

	public ArrayList<AdmissionDTO> getAdDtoList() {
		return adDtoList;
	}

	public void setAdDtoList(ArrayList<AdmissionDTO> adDtoList) {
		this.adDtoList = adDtoList;
	}

	public String[] getCulumnNames() {
		return culumnNames;
	}

	public void setCulumnNames(String[] culumnNames) {
		this.culumnNames = culumnNames;
	}

}
