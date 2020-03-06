package com.ebc.outpatient.doctor;

import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.JTextField;

public class PatientInformationPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JLabel lblPatientNo = new JLabel("Patient No:");
	private JLabel lPatientName = new JLabel("Patient Name:");
	private JLabel lblPatientSurname = new JLabel("Patient Surname:");
	private JLabel lblPatientAge = new JLabel("Patient Age:");
	private JLabel lblPatientGender = new JLabel("Patient Gender:");

	private JTextField txtPatientNo = new JTextField();
	private JTextField txtPatientName = new JTextField();
	private JTextField txtPatientSurname = new JTextField();
	private JTextField txtPatientAge = new JTextField();
	private JTextField txtPatientGender = new JTextField();

	private OutpatientInfoDTO outpatientInfoDTO = new OutpatientInfoDTO();
	

	public OutpatientInfoDTO getOutpatientInfoDTO() {
		return outpatientInfoDTO;
	}

	public void setOutpatientInfoDTO(OutpatientInfoDTO outpatientInfoDTO) {
		this.outpatientInfoDTO = outpatientInfoDTO;
	}

	public PatientInformationPanel() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 20, 0, 74, 34, 0, 75, 15, 0 };
		gridBagLayout.rowHeights = new int[] { 10, 0, 0, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0,
				0.0, 0.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0,
				Double.MIN_VALUE };
		setLayout(gridBagLayout);

		GridBagConstraints gbc_lblPatientNo = new GridBagConstraints();
		gbc_lblPatientNo.anchor = GridBagConstraints.WEST;
		gbc_lblPatientNo.insets = new Insets(0, 0, 5, 5);
		gbc_lblPatientNo.gridx = 1;
		gbc_lblPatientNo.gridy = 1;
		add(lblPatientNo, gbc_lblPatientNo);

		GridBagConstraints gbc_txtPatientNo = new GridBagConstraints();
		gbc_txtPatientNo.insets = new Insets(0, 0, 5, 5);
		gbc_txtPatientNo.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtPatientNo.gridx = 2;
		gbc_txtPatientNo.gridy = 1;
		add(txtPatientNo, gbc_txtPatientNo);
		txtPatientNo.setColumns(10);
		txtPatientNo.setEditable(false);

		GridBagConstraints gbc_lblPatientAge = new GridBagConstraints();
		gbc_lblPatientAge.anchor = GridBagConstraints.WEST;
		gbc_lblPatientAge.insets = new Insets(0, 0, 5, 5);
		gbc_lblPatientAge.gridx = 4;
		gbc_lblPatientAge.gridy = 1;
		add(lblPatientAge, gbc_lblPatientAge);

		GridBagConstraints gbc_txtPatientAge = new GridBagConstraints();
		gbc_txtPatientAge.insets = new Insets(0, 0, 5, 5);
		gbc_txtPatientAge.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtPatientAge.gridx = 5;
		gbc_txtPatientAge.gridy = 1;
		add(txtPatientAge, gbc_txtPatientAge);
		txtPatientAge.setColumns(10);
		txtPatientAge.setEditable(false);

		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 1;
		gbc_lblNewLabel.gridy = 2;
		add(lPatientName, gbc_lblNewLabel);

		GridBagConstraints gbc_txtPatientName = new GridBagConstraints();
		gbc_txtPatientName.insets = new Insets(0, 0, 5, 5);
		gbc_txtPatientName.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtPatientName.gridx = 2;
		gbc_txtPatientName.gridy = 2;
		add(txtPatientName, gbc_txtPatientName);
		txtPatientName.setColumns(10);
		txtPatientName.setEditable(false);

		GridBagConstraints gbc_lblPatientGender = new GridBagConstraints();
		gbc_lblPatientGender.anchor = GridBagConstraints.EAST;
		gbc_lblPatientGender.insets = new Insets(0, 0, 5, 5);
		gbc_lblPatientGender.gridx = 4;
		gbc_lblPatientGender.gridy = 2;
		add(lblPatientGender, gbc_lblPatientGender);

		GridBagConstraints gbc_txtPatientGender = new GridBagConstraints();
		gbc_txtPatientGender.insets = new Insets(0, 0, 5, 5);
		gbc_txtPatientGender.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtPatientGender.gridx = 5;
		gbc_txtPatientGender.gridy = 2;
		add(txtPatientGender, gbc_txtPatientGender);
		txtPatientGender.setColumns(10);
		txtPatientGender.setEditable(false);

		GridBagConstraints gbc_lblPatientSurname = new GridBagConstraints();
		gbc_lblPatientSurname.anchor = GridBagConstraints.EAST;
		gbc_lblPatientSurname.insets = new Insets(0, 0, 0, 5);
		gbc_lblPatientSurname.gridx = 1;
		gbc_lblPatientSurname.gridy = 3;
		add(lblPatientSurname, gbc_lblPatientSurname);

		GridBagConstraints gbc_txtPatientSurname = new GridBagConstraints();
		gbc_txtPatientSurname.insets = new Insets(0, 0, 0, 5);
		gbc_txtPatientSurname.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtPatientSurname.gridx = 2;
		gbc_txtPatientSurname.gridy = 3;
		add(txtPatientSurname, gbc_txtPatientSurname);
		txtPatientSurname.setColumns(10);
		txtPatientSurname.setEditable(false);
		
	}

	public void initialize(){
		
		if(outpatientInfoDTO != null){
			
			txtPatientName.setText(outpatientInfoDTO.getPatientDTO().getName());
			txtPatientSurname.setText(outpatientInfoDTO.getPatientDTO().getLastname());
			txtPatientNo.setText(String.valueOf(outpatientInfoDTO.getPatientDTO().getPatientno()));
			txtPatientAge.setText(String.valueOf(outpatientInfoDTO.getPatientDTO().getAge()));
			txtPatientGender.setText(outpatientInfoDTO.getPatientDTO().getGender());
			
		}
		else{
			
		}
		
	}
}
