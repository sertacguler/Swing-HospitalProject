package com.ebc.deginitions.test.categories;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import com.ebc.definitions.entity.TestCategoriesDTO;

public class TestCategoriesTableModel extends AbstractTableModel {
	private ArrayList<TestCategoriesDTO> tstCategories = new ArrayList<TestCategoriesDTO>();

	public ArrayList<TestCategoriesDTO> getTstCategories() {
		return tstCategories;
	}

	public void setTstCategories(ArrayList<TestCategoriesDTO> tstCategories) {
		this.tstCategories = tstCategories;
	}

	public String[] columns = { "TEST  CATEGORIES  NAME" };

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return 1;
	}

	public ArrayList<TestCategoriesDTO> getDatas() {
		return tstCategories;
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return tstCategories == null ? 0 : tstCategories.size();
	}

	@Override
	public String getColumnName(int column) {
		// TODO Auto-generated method stub
		return columns[column];
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		TestCategoriesDTO tstCt = tstCategories.get(rowIndex);
		if (columnIndex == 0) {

			return tstCt.getTestCategoriesName();

		}
		return null;
	}

}
