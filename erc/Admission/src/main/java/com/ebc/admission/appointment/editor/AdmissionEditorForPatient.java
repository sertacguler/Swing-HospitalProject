package com.ebc.admission.appointment.editor;

import java.util.ArrayList;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.ebc.admission.appointment.entity.AppointmentDto;
import com.ebc.admission.appointment.service.AppointmentService;
import com.ebc.definitions.admission.entity.AdmissionDTO;
import com.ebc.definitions.admission.panel.AdmissionPanel;
import com.ebc.definitions.admission.panel.AdmissionTableModel;
import com.ebc.definitions.admission.panel.CallAdmission;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;

import javax.swing.JScrollPane;

import java.awt.GridBagConstraints;

import javax.swing.JTable;

import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class AdmissionEditorForPatient extends JPanel  {

	private static JPanel panel;
	private ArrayList<AdmissionDTO> admissionDTOs;
	private JTable table;
	private JDialog dialog;
	private AdmissionTableModel admissionTableModel = new AdmissionTableModel();
	private int patientid;
	private JButton btnNewAdmission;
	private JButton btnSelect;
	private int admissionid;
	
	public AdmissionEditorForPatient() {
	    init();
	}
	public AdmissionEditorForPatient(int patientid) {
		this.patientid = patientid;
	    init();
	}
	public void init(){
		GenerelEvent event = new GenerelEvent();

		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{10, 0, 0, 0, 10, 0};
		gridBagLayout.rowHeights = new int[]{10, 0, 10, 0, 10, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		btnNewAdmission = new JButton("New Admission");
		btnNewAdmission.setFont(new Font("Tahoma", Font.BOLD, 15));
		GridBagConstraints gbc_btnNewAdmission = new GridBagConstraints();
		gbc_btnNewAdmission.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewAdmission.gridx = 2;
		gbc_btnNewAdmission.gridy = 1;
		add(btnNewAdmission, gbc_btnNewAdmission);
		
		btnSelect = new JButton("Select");
		btnSelect.setFont(new Font("Tahoma", Font.BOLD, 15));
		GridBagConstraints gbc_btnSelect = new GridBagConstraints();
		gbc_btnSelect.insets = new Insets(0, 0, 5, 5);
		gbc_btnSelect.gridx = 3;
		gbc_btnSelect.gridy = 1;
		add(btnSelect, gbc_btnSelect);
		btnSelect.setActionCommand("SAVE");
		btnSelect.addActionListener(event);
		btnNewAdmission.setActionCommand("NEW_ADMISSION");
		btnNewAdmission.addActionListener(event);
		
		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridwidth = 3;
		gbc_scrollPane.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 1;
		gbc_scrollPane.gridy = 3;
		add(scrollPane, gbc_scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(admissionTableModel);
		table.getTableHeader().setPreferredSize(new Dimension(50, 40));
	    table.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 18));
		table.setFont(new Font("Serif", Font.BOLD, 20));
		table.setRowHeight(40);
	    admissionTableModel.setAdDtoList(AppointmentService.getAllAdmissionByPatientid(this.patientid));
	}
	
	class GenerelEvent implements ActionListener {

		public GenerelEvent() {
			super();
		}

		public void actionPerformed(ActionEvent e) {

			if (e.getActionCommand().equals("SAVE")) {

				if (table.getSelectedRow() == -1) {
					System.out.println("Not Selected....");
				} else {
					AdmissionDTO admissionDTO = admissionTableModel.getAdDtoList().get(table.getSelectedRow());
					setAdmissionid(admissionDTO.getAdmissionid());
					dialog.dispose();
					JOptionPane.showMessageDialog(null,"Selected admission is ok");
				}
			}
			if (e.getActionCommand().equals("NEW_ADMISSION")) {
				System.out.println("NEW_ADMISSION");
				openAdmissionDetailsForPatient();
				dialog.dispose();
				JOptionPane.showMessageDialog(null,"Creating new admission is ok");
			}
		}
	}
	
	private void openAdmissionDetailsForPatient() {
		JDialog dialog = new JDialog(JOptionPane.getFrameForComponent(panel),
				"Admissions Panel", true);
		AdmissionPanel admissionPanel = new AdmissionPanel();
		admissionPanel.setDialog(dialog);
		admissionPanel.setIsCalled(CallAdmission.isCalledFromAppointment);
		admissionPanel.fillPatientInfoById(patientid);
		dialog.getContentPane().add(admissionPanel);
		dialog.setLocationByPlatform(true);
		dialog.setSize(1200, 1200);
		dialog.setVisible(true);
		System.out.println("Admission id : "+admissionPanel.getAdmissionid());
		setAdmissionid(admissionPanel.getAdmissionid());
	}
	
	public int getAdmissionid() {
		return admissionid;
	}
	public void setAdmissionid(int admissionid) {
		this.admissionid = admissionid;
	}
	public ArrayList<AdmissionDTO> getAdmissionDTOs() {
		return admissionDTOs;
	}
	public void setAdmissionDTOs(ArrayList<AdmissionDTO> admissionDTOs) {
		this.admissionDTOs = admissionDTOs;
	}
	public JDialog getDialog() {
		return dialog;
	}
	public void setDialog(JDialog dialog) {
		this.dialog = dialog;
	}
	public int getPatientid() {
		return patientid;
	}
	public void setPatientid(int patientid) {
		this.patientid = patientid;
	}
}
