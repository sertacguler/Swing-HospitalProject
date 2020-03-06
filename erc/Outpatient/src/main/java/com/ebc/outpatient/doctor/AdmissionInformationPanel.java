package com.ebc.outpatient.doctor;

import javax.swing.JPanel;

import java.awt.GridBagLayout;

import javax.swing.JLabel;

import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.JTextField;

public class AdmissionInformationPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JLabel lblAdmissionNo;
	private JLabel lblAdmissionDate;
	private JLabel lblAdmission;
	private JTextField txtAdmissionDoctor;
	private JTextField txtAdmissionNo;
	private JTextField txtAdmissionDate;

	private OutpatientInfoDTO outpatientInfoDTO = new OutpatientInfoDTO();

	public OutpatientInfoDTO getOutpatientInfoDTO() {
		return outpatientInfoDTO;
	}

	public void setOutpatientInfoDTO(OutpatientInfoDTO outpatientInfoDTO) {
		this.outpatientInfoDTO = outpatientInfoDTO;
	}

	public AdmissionInformationPanel() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 14, 0, 94, 15, 0 };
		gridBagLayout.rowHeights = new int[] { 15, 0, 0, 15, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 0.0, 1.0, 0.0,
				Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0,
				Double.MIN_VALUE };
		setLayout(gridBagLayout);

		lblAdmissionNo = new JLabel("Admission No:");
		GridBagConstraints gbc_lblAdmissionNo = new GridBagConstraints();
		gbc_lblAdmissionNo.anchor = GridBagConstraints.WEST;
		gbc_lblAdmissionNo.insets = new Insets(0, 0, 5, 5);
		gbc_lblAdmissionNo.gridx = 1;
		gbc_lblAdmissionNo.gridy = 1;
		add(lblAdmissionNo, gbc_lblAdmissionNo);

		lblAdmissionDate = new JLabel("Admission Date:");
		GridBagConstraints gbc_lblAdmissionDate = new GridBagConstraints();
		gbc_lblAdmissionDate.anchor = GridBagConstraints.WEST;
		gbc_lblAdmissionDate.insets = new Insets(0, 0, 5, 5);
		gbc_lblAdmissionDate.gridx = 1;
		gbc_lblAdmissionDate.gridy = 2;
		add(lblAdmissionDate, gbc_lblAdmissionDate);

		txtAdmissionNo = new JTextField();
		GridBagConstraints gbc_txtAdmissionNo = new GridBagConstraints();
		gbc_txtAdmissionNo.insets = new Insets(0, 0, 5, 5);
		gbc_txtAdmissionNo.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtAdmissionNo.gridx = 2;
		gbc_txtAdmissionNo.gridy = 1;
		txtAdmissionNo.setEditable(false);
		add(txtAdmissionNo, gbc_txtAdmissionNo);
		txtAdmissionNo.setColumns(10);

		txtAdmissionDate = new JTextField();
		GridBagConstraints gbc_txtAdmissionDate = new GridBagConstraints();
		gbc_txtAdmissionDate.insets = new Insets(0, 0, 5, 5);
		gbc_txtAdmissionDate.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtAdmissionDate.gridx = 2;
		gbc_txtAdmissionDate.gridy = 2;
		txtAdmissionDate.setEditable(false);
		add(txtAdmissionDate, gbc_txtAdmissionDate);
		txtAdmissionDate.setColumns(10);

		lblAdmission = new JLabel("Admission Doctor:");
		GridBagConstraints gbc_lblAdmission = new GridBagConstraints();
		gbc_lblAdmission.anchor = GridBagConstraints.EAST;
		gbc_lblAdmission.insets = new Insets(0, 0, 5, 5);
		gbc_lblAdmission.gridx = 1;
		gbc_lblAdmission.gridy = 3;
		add(lblAdmission, gbc_lblAdmission);

		txtAdmissionDoctor = new JTextField();
		GridBagConstraints gbc_txtAdmissionDoctor = new GridBagConstraints();
		gbc_txtAdmissionDoctor.insets = new Insets(0, 0, 5, 5);
		gbc_txtAdmissionDoctor.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtAdmissionDoctor.gridx = 2;
		gbc_txtAdmissionDoctor.gridy = 3;
		txtAdmissionDoctor.setEditable(false);
		add(txtAdmissionDoctor, gbc_txtAdmissionDoctor);
		txtAdmissionDoctor.setColumns(10);

	}

	public void initialize() {

		if (outpatientInfoDTO != null) {

			txtAdmissionNo.setText(outpatientInfoDTO.getAdmissionDTO()
					.getAdmissionNo());
			txtAdmissionDate.setText(String.valueOf(outpatientInfoDTO
					.getAdmissionDTO().getAdmissionDate()));
			txtAdmissionDoctor.setText(outpatientInfoDTO.getStaff().getPrsName()+" "+outpatientInfoDTO.getStaff().getPrsSurname());

		} else {

		}

	}

}
