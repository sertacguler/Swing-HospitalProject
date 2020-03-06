package com.ebc.definitions.staff.panel;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import com.ebc.definitions.staff.entity.Staff;

public class StaffTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 1L;
	ArrayList<Staff> staffs = new ArrayList<Staff>();

	private String[] culumnNames = { "Choose", "Name", "Surname", "Gender",
			"Task", "BirthDate", "StartedDate", "Email", "Adress",
			"MobilePhone", "HomePhone", "WorkPhone", };

	@Override
	public String getColumnName(int col) {
		return culumnNames[col];
	}

	@Override
	public int getColumnCount() {
		return culumnNames.length;
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		if (columnIndex == 0)
			return Boolean.class;
		else
			return super.getColumnClass(columnIndex);
	}

	@Override
	public int getRowCount() {
		return staffs == null ? 0 : staffs.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {

		Staff staffrow = staffs.get(rowIndex);

		if (columnIndex == 0)
			return staffrow.isSelected();
		else if (columnIndex == 1)
			return staffrow.getPrsName();
		else if (columnIndex == 2)
			return staffrow.getPrsSurname();
		else if (columnIndex == 3)
			return staffrow.getPrsGender();
		else if (columnIndex == 4)
			return staffrow.getPrsTask();
		else if (columnIndex == 5)
			return staffrow.getPrsBirthdate();
		else if(columnIndex == 6)
			return staffrow.getPrsStarteddate();
		else if (columnIndex == 7)
			return staffrow.getPrsEmail();
		else if (columnIndex == 8)
			return staffrow.getPrsAdress();
		else if (columnIndex == 9)
			return staffrow.getPrsPhonenumber();
		else if (columnIndex == 10)
			return staffrow.getPrsHomePhonenumber();
		else if (columnIndex == 11)
			return staffrow.getPrsWorkphone();

		return null;

	}

	public Object getValueAtRow(int row) {
		Staff s = staffs.get(row);
		return s;
	}

	public boolean isCellEditable(int row, int col) {
		if (col == 0)
			return true;
		else
			return false;
	}

	public ArrayList<Staff> getStaffs() {
		return staffs;
	}

	public void setStaffs(ArrayList<Staff> staffs) {
		this.staffs = staffs;
	}

	@Override
	public void setValueAt(Object value, int rowIndex, int columnIndex) {
		if (value instanceof Boolean) {
			if (columnIndex == 0) {
				Staff staff = staffs.get(rowIndex);
				staff.setSelected(((Boolean) value).booleanValue());
			}

		}

	}

}
