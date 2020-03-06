package com.ebc.admission.appointment.panel;

import java.awt.Color;
import java.awt.Component;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;

import com.ebc.admission.appointment.entity.AppointmentDto;


public class AppointmentTableModel extends AbstractTableModel {
	// holds our data
	private JLabel label = new JLabel();
	private ArrayList<AppointmentDto> appointmentDtos = new ArrayList<AppointmentDto>();
	private SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm");
	String[] columnNames = { "Control","Create Date" ,"Patient No", "Patient", "Organization", "Staff","Admission" };

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
		return appointmentDtos==null ? 0 : appointmentDtos.size();
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		AppointmentDto appointmentDto = appointmentDtos.get(rowIndex);
		if (columnIndex == 0) {
			return appointmentDto.isSelect();
		} else if (columnIndex == 1) {
			return dateFormat.format( appointmentDto.getCreatedAt());
		} else if (columnIndex == 2) {
			return appointmentDto.getPatient().getPatientno();
		} else if (columnIndex == 3) {
			return appointmentDto.getPatient().getName() + " " + appointmentDto.getPatient().getLastname();
		} else if (columnIndex == 4) {
			return appointmentDto.getOrganizationDTO().getOrganizationName();
		} else if (columnIndex == 5) {
			return appointmentDto.getStaff().getPrsName() + " " + appointmentDto.getStaff().getPrsSurname();
		}  else if (columnIndex == 6) {
			if(appointmentDto.getAdmissionid()> 0 ){
				return "Kabul edildi";
			} else {
				return "Kabul edilmedi ";
			}
		}
		return null;
	}

	@Override
	public String getColumnName(int col) {
		return columnNames[col];
	}

	@Override
	public void setValueAt(Object value, int rowIndex, int columnIndex) {
		AppointmentDto appointmentDto = appointmentDtos.get(rowIndex);
		if (value instanceof Boolean) {
			if (columnIndex == 0) {
				appointmentDto.setSelect(((Boolean) value).booleanValue());
				
			}
		}
	}

	public ArrayList<AppointmentDto> getAppointmentDtos() {
		return appointmentDtos;
	}

	public void setAppointmentDtos(ArrayList<AppointmentDto> appointmentDtos) {
		this.appointmentDtos = appointmentDtos;
	}

	public Object getValueAtRow(int row) {
		AppointmentDto appointmentDto = appointmentDtos.get(row);
		return appointmentDto;
	}
	
	@Override
	public boolean isCellEditable(int row, int col) {
		if(col == 0){
			 return true;
		} else {
			return false;
		}
		
	}
	
}
class WineCellRenderer extends DefaultTableCellRenderer {
	
	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column) {
		AppointmentTableModel wtm = (AppointmentTableModel) table.getModel();
//		Patient patient = (Patient) wtm.getValueAtRow(row);
		AppointmentDto dto = (AppointmentDto) wtm.getValueAtRow(row);
		if(dto.getOrganizationDTO().getOrganizationName().equals("Uroloji"))
			setBackground(new Color(200, 200, 150));
		else if(dto.getOrganizationDTO().getOrganizationName().equals("Radyoloji"))
			setBackground(new Color(200, 200, 100));
		else
			setBackground(new Color(200, 200, 50));

		return super.getTableCellRendererComponent(table, value, isSelected,
				hasFocus, row, column);
	}
}
