package com.ebc.outpatient.anamnez.panel;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import com.ebc.outpatient.anamnez.entity.Anamnez;

public class AnamnezTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 1L;

	private ArrayList<Anamnez> anamnezS = new ArrayList<Anamnez>();
	private String[] culumnNames = { "Admission Id", "Detail", "Created By",
			"Created At", "Update By", "Update At" };

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
		return anamnezS == null ? 0 : anamnezS.size();
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		return super.getColumnClass(columnIndex);
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Anamnez anamnez = anamnezS.get(rowIndex);

		if (columnIndex == 0) {
			return anamnez.getAdmissionId();
		} else if (columnIndex == 1) {
			return anamnez.getDetail();
		} else if (columnIndex == 2) {
			return anamnez.getStaff(); // getCreatedBy()
		} else if (columnIndex == 3) {
			return anamnez.getCreatedAt();
		} else if (columnIndex == 4) {
			return anamnez.getUpdatedBy(); // getUpdatedBy()
		} else if (columnIndex == 5) {
			return anamnez.getUpdatedAt();
		}

		return null;
	}

	public ArrayList<Anamnez> getAnamnezS() {
		return anamnezS;
	}

	public void setAnamnezS(ArrayList<Anamnez> anamnezS) {
		this.anamnezS = anamnezS;
	}

}
