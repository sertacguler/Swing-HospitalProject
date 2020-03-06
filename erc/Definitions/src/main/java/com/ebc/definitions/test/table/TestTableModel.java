package com.ebc.definitions.test.table;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import com.ebc.definitions.entity.TestDefinitionDTO;

public class TestTableModel extends AbstractTableModel {
	public String[] columns = { "TEST_NAME", "TEST_CODE", "ORGANIZATION_NAME" };
	private ArrayList<TestDefinitionDTO> tstDef = new ArrayList<TestDefinitionDTO>();

	public ArrayList<TestDefinitionDTO> getTstDef() {
		return tstDef;
	}

	public void setTstDef(ArrayList<TestDefinitionDTO> tstDef) {
		this.tstDef = tstDef;
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return 3;
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return tstDef == null ? 0 : tstDef.size();
	}

	@Override
	public String getColumnName(int column) {
		// TODO Auto-generated method stub
		return columns[column];
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		TestDefinitionDTO t = tstDef.get(rowIndex);
		if (columnIndex == 0) {
			return t.getTestName();
		} else if (columnIndex == 1) {
			return t.getTestCode();
		} else if (columnIndex == 2) {
			return t.getOrganizationName();
		}

		return null;
	}

}
