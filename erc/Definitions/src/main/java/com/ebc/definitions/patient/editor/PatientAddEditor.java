package com.ebc.definitions.patient.editor;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import com.ebc.definitions.patient.model.Patient;

public class PatientAddEditor  extends JPanel  {
	private static final long serialVersionUID = 1L;
	private JTextField txtName;
	private JTextField txtLastName;
	private JTextField txtAge;
	private Patient patient;
	private JDialog dialog;
	private JRadioButton rdbtnMale ;
	private JRadioButton rdbtnFemale ;
	
	public PatientAddEditor(Patient patientDetail) {

		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 10, 0, 0, 0, 0, 0, 10, 0 };
		gridBagLayout.rowHeights = new int[] { 10, 0, 0, 0, 0, 0, 0, 0, 10, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 0.0, 0.0, 1.0, 0.0,
				0.0, 0.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				Double.MIN_VALUE };
		setLayout(gridBagLayout);

		JLabel lblName = new JLabel("Name");
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 17));
		GridBagConstraints gbc_lblName = new GridBagConstraints();
		gbc_lblName.anchor = GridBagConstraints.WEST;
		gbc_lblName.insets = new Insets(0, 0, 5, 5);
		gbc_lblName.gridx = 1;
		gbc_lblName.gridy = 1;
		add(lblName, gbc_lblName);

		txtName = new JTextField();
		txtName.setFont(new Font("Tahoma", Font.PLAIN, 17));
		GridBagConstraints gbc_txtName = new GridBagConstraints();
		gbc_txtName.gridwidth = 3;
		gbc_txtName.insets = new Insets(0, 0, 5, 5);
		gbc_txtName.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtName.gridx = 3;
		gbc_txtName.gridy = 1;
		add(txtName, gbc_txtName);
		txtName.setColumns(10);

		JLabel lblLastname = new JLabel("LastName");
		lblLastname.setFont(new Font("Tahoma", Font.PLAIN, 17));
		GridBagConstraints gbc_lblLastname = new GridBagConstraints();
		gbc_lblLastname.insets = new Insets(0, 0, 5, 5);
		gbc_lblLastname.gridx = 1;
		gbc_lblLastname.gridy = 2;
		add(lblLastname, gbc_lblLastname);

		txtLastName = new JTextField();
		txtLastName.setFont(new Font("Tahoma", Font.PLAIN, 17));
		GridBagConstraints gbc_txtLastName = new GridBagConstraints();
		gbc_txtLastName.gridwidth = 3;
		gbc_txtLastName.insets = new Insets(0, 0, 5, 5);
		gbc_txtLastName.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtLastName.gridx = 3;
		gbc_txtLastName.gridy = 2;
		add(txtLastName, gbc_txtLastName);
		txtLastName.setColumns(10);

		JLabel lblAge = new JLabel("Age");
		lblAge.setFont(new Font("Tahoma", Font.PLAIN, 17));
		GridBagConstraints gbc_lblAge = new GridBagConstraints();
		gbc_lblAge.anchor = GridBagConstraints.WEST;
		gbc_lblAge.insets = new Insets(0, 0, 5, 5);
		gbc_lblAge.gridx = 1;
		gbc_lblAge.gridy = 3;
		add(lblAge, gbc_lblAge);

		txtAge = new JTextField();
		txtAge.setFont(new Font("Tahoma", Font.PLAIN, 17));
		GridBagConstraints gbc_txtAge = new GridBagConstraints();
		gbc_txtAge.gridwidth = 3;
		gbc_txtAge.insets = new Insets(0, 0, 5, 5);
		gbc_txtAge.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtAge.gridx = 3;
		gbc_txtAge.gridy = 3;
		add(txtAge, gbc_txtAge);
		txtAge.setColumns(10);
		
		JLabel lblCinsiyet = new JLabel("Cinsiyet");
		lblCinsiyet.setFont(new Font("Tahoma", Font.PLAIN, 17));
		GridBagConstraints gbc_lblCinsiyet = new GridBagConstraints();
		gbc_lblCinsiyet.insets = new Insets(0, 0, 5, 5);
		gbc_lblCinsiyet.gridx = 1;
		gbc_lblCinsiyet.gridy = 4;
		add(lblCinsiyet, gbc_lblCinsiyet);
		
		rdbtnMale = new JRadioButton("Male");
		rdbtnMale.setFont(new Font("Tahoma", Font.PLAIN, 17));
		GridBagConstraints gbc_rdbtnMale = new GridBagConstraints();
		gbc_rdbtnMale.insets = new Insets(0, 0, 5, 5);
		gbc_rdbtnMale.gridx = 3;
		gbc_rdbtnMale.gridy = 4;
		add(rdbtnMale, gbc_rdbtnMale);
		
		rdbtnFemale = new JRadioButton("Female");
		rdbtnFemale.setFont(new Font("Tahoma", Font.PLAIN, 17));
		GridBagConstraints gbc_rdbtnFemale = new GridBagConstraints();
		gbc_rdbtnFemale.insets = new Insets(0, 0, 5, 5);
		gbc_rdbtnFemale.gridx = 4;
		gbc_rdbtnFemale.gridy = 4;
		add(rdbtnFemale, gbc_rdbtnFemale);
		
		ButtonGroup group = new ButtonGroup();
		group.add(rdbtnMale);
		group.add(rdbtnFemale);
		
		JLabel lblMemleket = new JLabel("Memleket");
		lblMemleket.setFont(new Font("Tahoma", Font.PLAIN, 17));
		GridBagConstraints gbc_lblMemleket = new GridBagConstraints();
		gbc_lblMemleket.insets = new Insets(0, 0, 5, 5);
		gbc_lblMemleket.gridx = 1;
		gbc_lblMemleket.gridy = 5;
		add(lblMemleket, gbc_lblMemleket);
		
		final JComboBox comboBox = new JComboBox();
		comboBox.setFont(new Font("Tahoma", Font.PLAIN, 17));
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox.gridx = 3;
		gbc_comboBox.gridy = 5;
		comboBox.addItem("Ankara");
		comboBox.addItem("Bursa");
		comboBox.addItem("istanbul");
		comboBox.addItem("Antalya");
		comboBox.addItem("Mugla");
		comboBox.addItem("Manisa");
		comboBox.addItem("Mu�la");
		comboBox.addItem("Konya");
		comboBox.addItem("Rize");
		comboBox.addItem("Van");
		comboBox.addItem("Zonguldak");
		add(comboBox, gbc_comboBox);

		JButton btnSave = new JButton("Save");
		btnSave.setFont(new Font("Tahoma", Font.PLAIN, 17));
		GridBagConstraints gbc_btnSave = new GridBagConstraints();
		gbc_btnSave.insets = new Insets(0, 0, 5, 5);
		gbc_btnSave.gridx = 4;
		gbc_btnSave.gridy = 7;
		add(btnSave, gbc_btnSave);

		JButton btnCancel = new JButton("Cancel");
		btnCancel.setFont(new Font("Tahoma", Font.PLAIN, 17));
		GridBagConstraints gbc_btnCancel = new GridBagConstraints();
		gbc_btnCancel.insets = new Insets(0, 0, 5, 5);
		gbc_btnCancel.gridx = 5;
		gbc_btnCancel.gridy = 7;
		add(btnCancel, gbc_btnCancel);
		patient = patientDetail;
		if(patientDetail != null ){
			txtName.setText(patientDetail.getName());
			txtLastName.setText(patientDetail.getLastname());
			txtAge.setText(String.valueOf(patientDetail.getAge()));
			if(patientDetail.getGender().equals("Male")){
				rdbtnMale.setSelected(true);
			}else{
				rdbtnFemale.setSelected(true);
			}
			comboBox.setSelectedItem(patientDetail.getCity());
		}
		btnSave.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) { 
				if (patient == null) {
					patient = new Patient();
				}
				patient.setName(txtName.getText());
				patient.setLastname(txtLastName.getText());
				patient.setCity(String.valueOf(comboBox.getSelectedItem()));
				patient.setStatus(1);
				if(rdbtnMale.isSelected()){
					patient.setGender("Male");
					
				}else{
					patient.setGender("Female");
				}
				patient.setAge(Integer.parseInt(txtAge.getText()));
				dialog.dispose();
			}
		});
		btnCancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dialog.dispose();
			}
		});

	}

	public JDialog getDialog() {
		return dialog;
	}

	public void setDialog(JDialog dialog) {
		this.dialog = dialog;
	}

	public JTextField getTxtName() {
		return txtName;
	}

	public void setTxtName(JTextField txtName) {
		this.txtName = txtName;
	}

	public JTextField getTxtLastName() {
		return txtLastName;
	}

	public void setTxtLastName(JTextField txtLastName) {
		this.txtLastName = txtLastName;
	}

	public JTextField getTxtAge() {
		return txtAge;
	}

	public void setTxtAge(JTextField txtAge) {
		this.txtAge = txtAge;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}
}
