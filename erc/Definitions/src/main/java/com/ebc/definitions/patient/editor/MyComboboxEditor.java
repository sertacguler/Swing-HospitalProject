package com.ebc.definitions.patient.editor;

import java.awt.Component;
import java.awt.Font;

import javax.swing.AbstractCellEditor;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;

public class MyComboboxEditor extends AbstractCellEditor implements TableCellEditor{

	private JComboBox comboBoxGender = null;
	private String city="";
	private String gender="";

	public Component getTableCellEditorComponent(JTable table, Object value,
			boolean isSelected, int row, int column) {

		comboBoxGender = new JComboBox();
		comboBoxGender.setFont(new Font("Tahoma", Font.PLAIN, 17));
		comboBoxGender.addItem("Male");
		comboBoxGender.addItem("Female");
		return comboBoxGender;
	}

	public Object getCellEditorValue() {
		return comboBoxGender.getSelectedItem();
	}

}