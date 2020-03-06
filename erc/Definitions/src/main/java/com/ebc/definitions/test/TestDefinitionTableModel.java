package com.ebc.definitions.test;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import com.ebc.definitions.entity.TestDefinitionDTO;

public class TestDefinitionTableModel extends AbstractTableModel {

	private String[] columns = { "CHOOSE", "TESTCODE", "TESTNAME",
			"TESTCATEGORIES NAME", "ORGANIZATION_NAME", "MINVALUE", "MAXVALUE",
			"ISPROFILE", "ISACTIVE" };
	private ArrayList<TestDefinitionDTO> tstDto = new ArrayList<TestDefinitionDTO>();

	public ArrayList<TestDefinitionDTO> getTstDto() {
		return tstDto;
	}

	public void setTstDto(ArrayList<TestDefinitionDTO> tstDto) {
		this.tstDto = tstDto;
	}

	public ArrayList<TestDefinitionDTO> getDatas() {
		return tstDto;
	}

	public boolean isCellEditable(int row, int col) {
		if (col == 0) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public String getColumnName(int column) {
		// TODO Auto-generated method stub
		return columns[column];
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return 9;
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		// TODO Auto-generated method stub
		if (columnIndex == 0)
			return Boolean.class;
		else
			return super.getColumnClass(columnIndex);
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return tstDto == null ? 0 : tstDto.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		TestDefinitionDTO tst = tstDto.get(rowIndex);
		if (columnIndex == 0) {
			return tst.isSelected();
		}

		else if (columnIndex == 1) {
			return tst.getTestCode();
		}

		else if (columnIndex == 2) {
			return tst.getTestName();

		}

		else if (columnIndex == 3) {
			return tst.getTestCategoriesDTO().getTestCategoriesName();
		}

		else if (columnIndex == 7) {
			return tst.isProfile() ? "PROFILE" : "NOT PROFILE";

		} else if (columnIndex == 8) {
			return tst.isActive() ? "ACTIVE" : "PASSIVE";
		} else if (columnIndex == 4) {
			return tst.getOrganizationName();
		} else if (columnIndex == 5) {
			return tst.getMinValue();
		} else if (columnIndex == 6) {
			return tst.getMaxValue();
		} else
			return null;
	}

	@Override
	public void setValueAt(Object value, int rowIndex, int columnIndex) {
		if (value instanceof Boolean) {
			if (columnIndex == 0) {
				TestDefinitionDTO test = tstDto.get(rowIndex);
				test.setSelected(((Boolean) value).booleanValue());
			}
		}

	}

}
