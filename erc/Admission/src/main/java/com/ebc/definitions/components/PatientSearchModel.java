package com.ebc.definitions.components;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import com.ebc.definitions.patient.model.Patient;

public class PatientSearchModel extends AbstractTableModel {
	private ArrayList<Patient> patient = new ArrayList<Patient>();
	String[] columns = {"Patient_No","Patient_Name","Patient_LastName","Patient_Age","Patient_Gender","Patient_City"};
	
	
	
	
	
	public ArrayList<Patient> getPatient() {
		return patient;
	}



	public void setPatient(ArrayList<Patient> patient) {
		this.patient = patient;
	}



	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return 6;
	}
	
	
	
	@Override
	public String getColumnName(int column) {
		// TODO Auto-generated method stub
		return columns[column];
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return patient==null ? 0 : patient.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Patient pat = patient.get(rowIndex);
		 if(columnIndex==0) {
		return pat.getPatientno();	
		}
		else if(columnIndex==1) {
			return pat.getName();
		}
		else if(columnIndex==2) {
			return pat.getLastname();
		}
		else if(columnIndex==3) {
			return pat.getAge();
		}
		else if(columnIndex==4) {
			return pat.getGender();
		}
		else if(columnIndex==5) {
		return pat.getCity();	
		}
		return null;
	}

}
