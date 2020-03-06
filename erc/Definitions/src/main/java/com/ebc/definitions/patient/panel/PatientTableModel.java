package com.ebc.definitions.patient.panel;

import java.awt.Color;
import java.awt.Component;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;

import com.ebc.definitions.patient.model.Patient;


public class PatientTableModel extends AbstractTableModel {
	// holds our data
	private JLabel label = new JLabel();
	private ImageIcon iconfemale = new ImageIcon( "C:\\Users\\celala\\Desktop\\female.png");
	private ImageIcon iconmale = new ImageIcon( "C:\\Users\\celala\\Desktop\\male.png");

	
	
	private static final long serialVersionUID = 1L;
	private ArrayList<Patient> patients = new ArrayList<Patient>();
	String[] columnNames = { "Control", "Patient No", "Name", "Surname", "Gender",
			 "City", "Image" };

	public int getColumnCount() {
		return columnNames.length;
	}

	public Class<?> getColumnClass(int columnIndex) {
		if (columnIndex==0)
			return Boolean.class;
		else
			return super.getColumnClass(columnIndex);
	}

	public int getRowCount() {
		return patients==null?0:patients.size();
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		Patient patient = patients.get(rowIndex);
		ImageIcon iconfemale = new ImageIcon( "C:\\Users\\celala\\Downloads\\female.png");
		ImageIcon iconmale = new ImageIcon( "C:\\Users\\celala\\Downloads\\male.png");
		if (columnIndex == 0) {
			return patient.isSelected();
		} else if (columnIndex == 1) {
			return patient.getPatientno();
		} else if (columnIndex == 2) {
			return patient.getName();
		} else if (columnIndex == 3) {
			return patient.getLastname();
		} else if (columnIndex == 4) {
			return patient.getGender();
		} else if (columnIndex == 5) {
			return patient.getCity();
		} else if (columnIndex == 6) {
			if (patient.getGender().equals("Female")) {
				label.setIcon(iconfemale);
			} else if (patient.getGender().equals("Male")) {
				label.setIcon(iconmale);
			} else{
				label.setIcon(null);
			}
			return label;
		}
		return null;
	}

	@Override
	public String getColumnName(int col) {
		return columnNames[col];
	}

	@Override
	public void setValueAt(Object value, int rowIndex, int columnIndex) {
		Patient patient = patients.get(rowIndex);
		if(columnIndex == 4){
			patient.setDirty(true);
			patient.setGender(String.valueOf(value));
		}
		
		if (value instanceof Boolean) {
			if (columnIndex == 0) {
				//Patient patient = patients.get(rowIndex);
				patient.setSelected(((Boolean) value).booleanValue());
				
			}
		}
	}


	public ArrayList<Patient> getPatients() {
		return patients;
	}

	public void setPatients(ArrayList<Patient> patients) {
		this.patients = patients;
	}

	public Object getValueAtRow(int row) {
		Patient p = patients.get(row);
		return p;
	}
	
	@Override
	public boolean isCellEditable(int row, int col) {
		if(col == 0 || col == 4){
			 return true;
		} else {
			return false;
		}
		
	}
}
class WineCellRenderer extends DefaultTableCellRenderer {
	private ImageIcon iconfemale = new ImageIcon( "C:\\Users\\celala\\Downloads\\female.png");
	private ImageIcon iconmale = new ImageIcon( "C:\\Users\\celala\\Downloads\\male.png");
	private JLabel label = new JLabel();
	
	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column) {
		PatientTableModel wtm = (PatientTableModel) table.getModel();
		Patient patient = (Patient) wtm.getValueAtRow(row);

		// insert image to label
		if (value instanceof JLabel && column == 6) {
			if (patient.getGender().equals("Female")) {
				label.setIcon(iconfemale);
				label.setOpaque(true);
				label.setBackground(new Color(245, 225, 245));

			} else if (patient.getGender().equals("Male")) {
				label.setIcon(iconmale);
				label.setOpaque(true);
				label.setBackground(new Color(245, 225, 245));
			} else {
				label.setIcon(null);
			}
			return label;
		}

		// color row regarding gender
		if (patient.getGender().equals("Male")) {
			setBackground(new Color(247, 255, 131));
		} else if (patient.getGender().equals("Female")) {
			setBackground(new Color(255, 119, 90));
		}
		return super.getTableCellRendererComponent(table, value, isSelected,
				hasFocus, row, column);
	}
}
