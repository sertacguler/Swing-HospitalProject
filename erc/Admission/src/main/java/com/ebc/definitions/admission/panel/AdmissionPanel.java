package com.ebc.definitions.admission.panel;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.hibernate.SQLQuery;
import org.hibernate.annotations.Parent;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.SimpleExpression;

import com.ebc.core.AqBaseEntity;
import com.ebc.core.DBHibernate;
import com.ebc.definitions.admission.entity.AdmissionDTO;
import com.ebc.definitions.admission.entity.AdmissionStatus;
import com.ebc.definitions.combobox.ComboboxModel;
import com.ebc.definitions.components.PatientSearchButton;
import com.ebc.definitions.components.PatientSearchPanel;
import com.ebc.definitions.organization.entity.OrganizationDTO;
import com.ebc.definitions.patient.model.Patient;
import com.ebc.definitions.staff.entity.Staff;
import com.toedter.calendar.JDateChooser;
import java.awt.Font;

public class AdmissionPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField nameTextField;
	private JTextField ageTextField;
	private JTextField genderTextField;
	private JTextField cityTextField;
	private JTextField searchTextField;
	private JTextField lastnameTextField;
	private JLabel lblNewLabel_5;
	private JButton btnSearch;
	private JButton btnClean;
	private JLabel lblNewLabel_6;
	private JLabel lblNewLabel_7;
	private JDateChooser dateChooser;
	private JComboBox admissionComboBox;
	private JComboBox doctorComboBox;
	private JLabel lblNewLabel_8;
	private JButton btnSave;
	private JButton btnAdClose;
	private JButton btnAdCancel;
	private JScrollPane scrollPane;
	private JTable table = new JTable();
	private JComboBox organizationComboBox;
	private JLabel lblOrganization;
	private JLabel lblNewLabel_9;
	private JButton btnAdmissionNoSearch;
	private JTextField admissionNoTextField;
	private JDialog dialog;
	private PatientSearchButton btnFindPatient;
	private int admissionid;
	private AdmissionStatus admissionEnum;
	private AdmissionDTO selectedAdmissionDTO;
	private CallAdmission isCalled= CallAdmission.isCalledFromAdmission;
	private SimpleDateFormat dateFormat;
	private PatientSearchPanel panel;
 	private ArrayList<AdmissionDTO> admissionList = new ArrayList<AdmissionDTO>();
	private ArrayList<Staff> staffList = new ArrayList<Staff>();
	private ArrayList<OrganizationDTO> organizationList = new ArrayList<OrganizationDTO>();

	private ArrayList<AqBaseEntity> admissions;
	private ComboboxModel cbxModelStaffs = new ComboboxModel();
	private ComboboxModel cbModelOrganizations = new ComboboxModel();

	private AdmissionTableModel adTableModel = new AdmissionTableModel();

	public AdmissionPanel() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 20, 0, 0, 15, 0, 0, 0, 0, 20,
				0 };
		gridBagLayout.rowHeights = new int[] { 20, 0, 0, 0, 0, 0, 0, 0, 10, 0,
				0, 0, 15, 0, 10, 0, 15, 0 };
		gridBagLayout.columnWeights = new double[] { 1.0, 0.0, 0.0, 0.0, 1.0,
				0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0,
				Double.MIN_VALUE };
		setLayout(gridBagLayout);

		lblNewLabel_9 = new JLabel("ADMISSION NO");
		GridBagConstraints gbc_lblNewLabel_9 = new GridBagConstraints();
		gbc_lblNewLabel_9.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_9.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_9.gridx = 2;
		gbc_lblNewLabel_9.gridy = 1;
		add(lblNewLabel_9, gbc_lblNewLabel_9);

		admissionNoTextField = new JTextField();
		GridBagConstraints gbc_admissionNoTextField = new GridBagConstraints();
		gbc_admissionNoTextField.insets = new Insets(0, 0, 5, 5);
		gbc_admissionNoTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_admissionNoTextField.gridx = 4;
		gbc_admissionNoTextField.gridy = 1;
		add(admissionNoTextField, gbc_admissionNoTextField);
		admissionNoTextField.setColumns(10);

		btnAdmissionNoSearch = new JButton("SEARCH");
		GridBagConstraints gbc_btnAdmissionNoSearch = new GridBagConstraints();
		gbc_btnAdmissionNoSearch.insets = new Insets(0, 0, 5, 5);
		gbc_btnAdmissionNoSearch.gridx = 5;
		gbc_btnAdmissionNoSearch.gridy = 1;
		add(btnAdmissionNoSearch, gbc_btnAdmissionNoSearch);

		lblNewLabel_5 = new JLabel("PATIENT NO");
		GridBagConstraints gbc_lblNewLabel_5 = new GridBagConstraints();
		gbc_lblNewLabel_5.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_5.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_5.gridx = 2;
		gbc_lblNewLabel_5.gridy = 2;
		add(lblNewLabel_5, gbc_lblNewLabel_5);

		searchTextField = new JTextField();
		GridBagConstraints gbc_searchTextField = new GridBagConstraints();
		gbc_searchTextField.insets = new Insets(0, 0, 5, 5);
		gbc_searchTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_searchTextField.gridx = 4;
		gbc_searchTextField.gridy = 2;
		add(searchTextField, gbc_searchTextField);
		searchTextField.setColumns(10);

		btnSearch = new JButton("SEARCH");
		GridBagConstraints gbc_btnSearch = new GridBagConstraints();
		gbc_btnSearch.insets = new Insets(0, 0, 5, 5);
		gbc_btnSearch.gridx = 5;
		gbc_btnSearch.gridy = 2;
		add(btnSearch, gbc_btnSearch);
		
		btnFindPatient = new PatientSearchButton();
		GridBagConstraints gbc_btnFindPatient = new GridBagConstraints();
		gbc_btnFindPatient.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnFindPatient.anchor = GridBagConstraints.SOUTH;
		gbc_btnFindPatient.insets = new Insets(0, 0, 5, 5);
		gbc_btnFindPatient.gridx = 6;
		gbc_btnFindPatient.gridy = 1;
		add(btnFindPatient, gbc_btnFindPatient);
		btnFindPatient.setText("FIND");

		btnClean = new JButton("CLEAN");
		GridBagConstraints gbc_btnClean = new GridBagConstraints();
		gbc_btnClean.insets = new Insets(0, 0, 5, 5);
		gbc_btnClean.gridx = 6;
		gbc_btnClean.gridy = 2;
		add(btnClean, gbc_btnClean);

		JLabel lblNewLabel = new JLabel("NAME");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 2;
		gbc_lblNewLabel.gridy = 3;
		add(lblNewLabel, gbc_lblNewLabel);

		nameTextField = new JTextField();
		GridBagConstraints gbc_nameTextField = new GridBagConstraints();
		gbc_nameTextField.gridwidth = 3;
		gbc_nameTextField.insets = new Insets(0, 0, 5, 5);
		gbc_nameTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_nameTextField.gridx = 4;
		gbc_nameTextField.gridy = 3;
		add(nameTextField, gbc_nameTextField);
		nameTextField.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("LASTNAME");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 2;
		gbc_lblNewLabel_1.gridy = 4;
		add(lblNewLabel_1, gbc_lblNewLabel_1);

		lastnameTextField = new JTextField();
		GridBagConstraints gbc_lastnameTextField = new GridBagConstraints();
		gbc_lastnameTextField.gridwidth = 3;
		gbc_lastnameTextField.insets = new Insets(0, 0, 5, 5);
		gbc_lastnameTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_lastnameTextField.gridx = 4;
		gbc_lastnameTextField.gridy = 4;
		add(lastnameTextField, gbc_lastnameTextField);
		lastnameTextField.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("AGE");
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_2.gridx = 2;
		gbc_lblNewLabel_2.gridy = 5;
		add(lblNewLabel_2, gbc_lblNewLabel_2);

		ageTextField = new JTextField();
		GridBagConstraints gbc_ageTextField = new GridBagConstraints();
		gbc_ageTextField.gridwidth = 3;
		gbc_ageTextField.insets = new Insets(0, 0, 5, 5);
		gbc_ageTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_ageTextField.gridx = 4;
		gbc_ageTextField.gridy = 5;
		add(ageTextField, gbc_ageTextField);
		ageTextField.setColumns(10);

		JLabel lblNewLabel_3 = new JLabel("GENDER");
		GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
		gbc_lblNewLabel_3.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_3.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_3.gridx = 2;
		gbc_lblNewLabel_3.gridy = 6;
		add(lblNewLabel_3, gbc_lblNewLabel_3);

		genderTextField = new JTextField();
		GridBagConstraints gbc_genderTextField = new GridBagConstraints();
		gbc_genderTextField.gridwidth = 3;
		gbc_genderTextField.insets = new Insets(0, 0, 5, 5);
		gbc_genderTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_genderTextField.gridx = 4;
		gbc_genderTextField.gridy = 6;
		add(genderTextField, gbc_genderTextField);
		genderTextField.setColumns(10);

		JLabel lblNewLabel_4 = new JLabel("CITY");
		GridBagConstraints gbc_lblNewLabel_4 = new GridBagConstraints();
		gbc_lblNewLabel_4.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_4.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_4.gridx = 2;
		gbc_lblNewLabel_4.gridy = 7;
		add(lblNewLabel_4, gbc_lblNewLabel_4);

		cityTextField = new JTextField();
		GridBagConstraints gbc_cityTextField = new GridBagConstraints();
		gbc_cityTextField.gridwidth = 3;
		gbc_cityTextField.insets = new Insets(0, 0, 5, 5);
		gbc_cityTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_cityTextField.gridx = 4;
		gbc_cityTextField.gridy = 7;
		add(cityTextField, gbc_cityTextField);
		cityTextField.setColumns(10);

		lblNewLabel_7 = new JLabel("ADMISSION DATE");
		GridBagConstraints gbc_lblNewLabel_7 = new GridBagConstraints();
		gbc_lblNewLabel_7.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_7.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_7.gridx = 2;
		gbc_lblNewLabel_7.gridy = 9;
		add(lblNewLabel_7, gbc_lblNewLabel_7);

		dateChooser = new JDateChooser();
		GridBagConstraints gbc_dateChooser = new GridBagConstraints();
		gbc_dateChooser.insets = new Insets(0, 0, 5, 5);
		gbc_dateChooser.fill = GridBagConstraints.BOTH;
		gbc_dateChooser.gridx = 4;
		gbc_dateChooser.gridy = 9;
		dateChooser.setDate(new Date());
		dateChooser.setDateFormatString("dd-MM-yyyy hh:mm");
		add(dateChooser, gbc_dateChooser);

		lblNewLabel_6 = new JLabel("ADMISSION TYPE");
		GridBagConstraints gbc_lblNewLabel_6 = new GridBagConstraints();
		gbc_lblNewLabel_6.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_6.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_6.gridx = 2;
		gbc_lblNewLabel_6.gridy = 10;
		add(lblNewLabel_6, gbc_lblNewLabel_6);

		admissionComboBox = new JComboBox();
		GridBagConstraints gbc_admissionComboBox = new GridBagConstraints();
		gbc_admissionComboBox.insets = new Insets(0, 0, 5, 5);
		gbc_admissionComboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_admissionComboBox.gridx = 4;
		gbc_admissionComboBox.gridy = 10;
		add(admissionComboBox, gbc_admissionComboBox);

		lblNewLabel_8 = new JLabel("DOCTOR");
		GridBagConstraints gbc_lblNewLabel_8 = new GridBagConstraints();
		gbc_lblNewLabel_8.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_8.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_8.gridx = 2;
		gbc_lblNewLabel_8.gridy = 11;
		add(lblNewLabel_8, gbc_lblNewLabel_8);

		doctorComboBox = new JComboBox();
		GridBagConstraints gbc_doctorComboBox = new GridBagConstraints();
		gbc_doctorComboBox.insets = new Insets(0, 0, 5, 5);
		gbc_doctorComboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_doctorComboBox.gridx = 4;
		gbc_doctorComboBox.gridy = 11;
		add(doctorComboBox, gbc_doctorComboBox);

		admissionComboBox.addItem("Gunubirlik");
		admissionComboBox.addItem("Ayaktan");
		admissionComboBox.addItem("Yatan");

		lblOrganization = new JLabel("ORGANIZATION");
		GridBagConstraints gbc_lblOrganization = new GridBagConstraints();
		gbc_lblOrganization.anchor = GridBagConstraints.WEST;
		gbc_lblOrganization.insets = new Insets(0, 0, 5, 5);
		gbc_lblOrganization.gridx = 2;
		gbc_lblOrganization.gridy = 12;
		add(lblOrganization, gbc_lblOrganization);

		organizationComboBox = new JComboBox();
		GridBagConstraints gbc_organizationComboBox = new GridBagConstraints();
		gbc_organizationComboBox.insets = new Insets(0, 0, 5, 5);
		gbc_organizationComboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_organizationComboBox.gridx = 4;
		gbc_organizationComboBox.gridy = 12;
		add(organizationComboBox, gbc_organizationComboBox);

		btnAdClose = new JButton("Admission Close");
		GridBagConstraints gbc_btnAdClose = new GridBagConstraints();
		gbc_btnAdClose.insets = new Insets(0, 0, 5, 5);
		gbc_btnAdClose.gridx = 2;
		gbc_btnAdClose.gridy = 13;
		add(btnAdClose, gbc_btnAdClose);

		btnAdCancel = new JButton("Admission Cancel");
		GridBagConstraints gbc_btnAdCancel = new GridBagConstraints();
		gbc_btnAdCancel.insets = new Insets(0, 0, 5, 5);
		gbc_btnAdCancel.gridx = 4;
		gbc_btnAdCancel.gridy = 13;
		add(btnAdCancel, gbc_btnAdCancel);

		btnSave = new JButton("Admission Save");
		GridBagConstraints gbc_btnSave = new GridBagConstraints();
		gbc_btnSave.insets = new Insets(0, 0, 5, 5);
		gbc_btnSave.gridx = 6;
		gbc_btnSave.gridy = 13;
		add(btnSave, gbc_btnSave);

		nameTextField.setEditable(false);
		lastnameTextField.setEditable(false);
		ageTextField.setEditable(false);
		genderTextField.setEditable(false);
		cityTextField.setEditable(false);

		scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridwidth = 7;
		gbc_scrollPane.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 1;
		gbc_scrollPane.gridy = 15;
		add(scrollPane, gbc_scrollPane);

		table = new JTable();
		table.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		scrollPane.setViewportView(table);
		table.setModel(adTableModel);
		table.setRowHeight(45);
		myDoctorCombobox();
		myOrganizationCombobox();

		btnAdClose.setVisible(false);
		btnAdCancel.setVisible(false);
		

		table.getSelectionModel().addListSelectionListener(
				new ListSelectionListener() {
					@Override
					public void valueChanged(ListSelectionEvent arg0) {
						tableSelectedRowData();
					}
				});

		btnAdClose.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				admissionCloseButton();
			}
		});

		btnAdCancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				admissionCancelButton();
			}
		});

		btnAdmissionNoSearch.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cleanAllComponent();

				admissionNoSearchButton();
			}

		});

		btnSearch.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				searchButton();
			}
		});

		btnClean.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cleanAllComponent();
			}

		});
		
	btnFindPatient.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				
				 Patient patient = openEditor2();
				 
				 if(patient!=null){
					 fillPatientInfoById(patient.getPatientid());
				 }
				  
	 
					
				
			}
		});

		btnSave.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				saveBtn();
			}
		});

	}

	private void admissionNoSearchButton() {
		if (!admissionNoTextField.getText().isEmpty()) {
			String id = admissionNoTextField.getText();

			admissionList = admissionNoBy(id);

			Patient patient = admissionList.isEmpty() ? null : admissionList
					.stream().findFirst().get().getPatient();
			AdmissionDTO admissionDTO = admissionList.isEmpty() ? null
					: admissionList.stream().findFirst().get();

			if (patient != null) {
				searchTextField.setText(String.valueOf(patient.getPatientid()));
				searchTextField.setEditable(false);
				nameTextField.setText(patient.getName());
				lastnameTextField.setText(patient.getLastname());
				ageTextField.setText(String.valueOf(patient.getAge()));
				genderTextField.setText(patient.getGender());
				cityTextField.setText(patient.getCity());

				dateChooser.setDate(admissionDTO.getAdmissionDate());
				admissionComboBox.setSelectedItem(admissionDTO
						.getAdmissionType());
				doctorComboBox.setSelectedItem(admissionDTO.getStaff());
				organizationComboBox.setSelectedItem(admissionDTO
						.getOrganizationDTO());

				adTableModel.setAdDtoList(admissionList);
				adTableModel.fireTableDataChanged();

				btnAdClose.setVisible(true);
				btnAdCancel.setVisible(true);
			} else {
				JOptionPane.showMessageDialog(null, "record isn't find!",
						"Warning", JOptionPane.WARNING_MESSAGE);
			}
		}
	}

	private void searchButton() {
		if (!searchTextField.getText().isEmpty()) {
			int id = Integer.valueOf(searchTextField.getText());

			fillPatientInfoById(id);

		}
	}

	public void fillPatientInfoById(int id) {
		
		Patient patient = patientSeacrh(id);
		admissionList = admissionById(id);
		cleanAllComponent();

		if (patient != null) {
			searchTextField.setText(String.valueOf(patient.getPatientid()));
			nameTextField.setText(patient.getName());
			lastnameTextField.setText(patient.getLastname());
			ageTextField.setText(String.valueOf(patient.getAge()));
			genderTextField.setText(patient.getGender());
			cityTextField.setText(patient.getCity());

			adTableModel.setAdDtoList(admissionList);
			adTableModel.fireTableDataChanged();
		} else {
			JOptionPane.showMessageDialog(null, "record isn't find!",
					"Warning", JOptionPane.WARNING_MESSAGE);
		}

	}
 
	private void tableSelectedRowData() {
		if (table.getSelectedRow() > -1) {
			AdmissionDTO admissionDTO = adTableModel.getAdDtoList().get(
					table.getSelectedRow());

			selectedAdmissionDTO = admissionDTO;

			admissionNoTextField.setText(admissionDTO.getAdmissionNo());
			searchTextField
					.setText(String.valueOf(admissionDTO.getPatientid()));
			searchTextField.setEditable(false);
			nameTextField.setText(admissionDTO.getPatient().getName());
			lastnameTextField.setText(admissionDTO.getPatient().getLastname());
			ageTextField.setText(String.valueOf(admissionDTO.getPatient()
					.getAge()));
			genderTextField.setText(admissionDTO.getPatient().getGender());
			cityTextField.setText(admissionDTO.getPatient().getCity());

			dateChooser.setDate(admissionDTO.getAdmissionDate());
			admissionComboBox.setSelectedItem(admissionDTO.getAdmissionType());

			doctorComboBox.setSelectedItem(admissionDTO.getStaff());

			organizationComboBox.setSelectedItem(admissionDTO
					.getOrganizationDTO());

			btnAdClose.setVisible(true);
			btnAdCancel.setVisible(true);
		}
	}

	private void saveBtn() {
		AdmissionDTO adDto = new AdmissionDTO();
		adDto.setPatientid(Integer.valueOf(searchTextField.getText()));
		adDto.setAdmissionDate(dateChooser.getDate());
		adDto.setAdmissionType((String) admissionComboBox.getSelectedItem());

		Staff staff = (Staff) doctorComboBox.getSelectedItem();
		adDto.setStaffId(staff.getPrsId());

		OrganizationDTO organizationDTO = (OrganizationDTO) organizationComboBox
				.getSelectedItem();
		adDto.setOrganizationid(organizationDTO.getOrganizationId());

		adDto.setStatus(true);
		adDto.setAdmissionStatus(AdmissionStatus.OPEN);
		try {
			if (typeControl())
				adDto = (AdmissionDTO) DBHibernate.save(adDto);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		adTableModel.setAdDtoList(admissionById(Integer.valueOf(searchTextField
				.getText())));

		if(isCalled == CallAdmission.isCalledFromAppointment){
			this.admissionid = adDto.getAdmissionid();
			dialog.dispose();
		}
		adTableModel.fireTableDataChanged();
	}

	private void admissionCloseButton() {
		if (selectedAdmissionDTO != null) {
			if (selectedAdmissionDTO.getAdmissionStatus() != AdmissionStatus.CLOSE) {
				selectedAdmissionDTO.setAdmissionStatus(AdmissionStatus.CLOSE);
			} else {
				JOptionPane.showMessageDialog(null, "record isn't find!",
						"Warning", JOptionPane.WARNING_MESSAGE);
			}
		}
		DBHibernate.update(selectedAdmissionDTO);
	}

	private void admissionCancelButton() {
		if (selectedAdmissionDTO != null) {
			if (selectedAdmissionDTO.getAdmissionStatus() == AdmissionStatus.OPEN) {
				String x = openEditor();
				selectedAdmissionDTO.setCancelReason(x);
				selectedAdmissionDTO.setAdmissionStatus(AdmissionStatus.CANCEL);
			} else {
				JOptionPane.showMessageDialog(null, "Record isn't find!",
						"Warning", JOptionPane.WARNING_MESSAGE);
			}
		}
		DBHibernate.update(selectedAdmissionDTO);
	}

	private void cleanAllComponent() {
		searchTextField.setEditable(true);
		admissionNoTextField.setEditable(true);
		nameTextField.setText("");
		lastnameTextField.setText("");
		ageTextField.setText("");
		genderTextField.setText("");
		cityTextField.setText("");

		dateChooser.setDate(null);

		doctorComboBox.setSelectedIndex(-1);
		organizationComboBox.setSelectedIndex(-1);
		admissionComboBox.setSelectedIndex(-1);

		btnAdClose.setVisible(false);
		btnAdCancel.setVisible(false);

		adTableModel.setAdDtoList(null);
		adTableModel.getRowCount();
		adTableModel.fireTableDataChanged();
	}

	private boolean typeControl() {
		boolean yatan = true;
		for (AdmissionDTO admDto : admissionList)
			if (admDto.getAdmissionType() == "Yatan") {
				JOptionPane
						.showMessageDialog(
								null,
								"Registration is not possible. The patient has an inpatient record.",
								"Warning", JOptionPane.WARNING_MESSAGE);
				yatan = false;
			}
		return yatan;
	}

	private void myDoctorCombobox() {
		SimpleExpression rest = Restrictions.eq("prsStatus", true);
		cbxModelStaffs.setValues(DBHibernate.getAll(Staff.class,
				new ArrayList(), rest));
		cbxModelStaffs.initialize();
		doctorComboBox.setModel(cbxModelStaffs);
	}

	private void myOrganizationCombobox() {
		SimpleExpression rest = Restrictions.eq("status", true);
		cbModelOrganizations.setValues(DBHibernate.getAll(
				OrganizationDTO.class, new ArrayList(), rest));
		cbModelOrganizations.initialize();
		organizationComboBox.setModel(cbModelOrganizations);
	}

	public Patient patientSeacrh(int id) {
		return (Patient) DBHibernate.findById(Patient.class, id);
	}

	public static ArrayList<AdmissionDTO> admissionById(int admissionId) {
		String sql = "SELECT t1.*, t2.*, t3.*, t4.* FROM AQADMISSION t1 JOIN AQORGANIZATION t2 ON t1.ORGANIZATIONID=t2.ORGANIZATIONID JOIN AQPATIENT t3 ON t1.PATIENTID = t3.PATIENTID JOIN AQSTAFF t4 ON t1.STAFFID = t4.PRSID WHERE t3.PATIENTID  = :admissionId AND t1.status = :status";

		SQLQuery query = DBHibernate.getSession().createSQLQuery(sql);
		query.addEntity("t1", AdmissionDTO.class);
		query.addEntity("t2", OrganizationDTO.class);
		query.addEntity("t3", Patient.class);
		query.addEntity("t4", Staff.class);
		query.setParameter("admissionId", admissionId);
		query.setParameter("status", true);

		ArrayList<Object[]> results = (ArrayList<Object[]>) query.list();
		ArrayList<AdmissionDTO> admissions = new ArrayList();

		for (Object[] objects : results) {
			AdmissionDTO admissionDTO = (AdmissionDTO) objects[0];
			OrganizationDTO organizationDTO = (OrganizationDTO) objects[1];
			Patient patient = (Patient) objects[2];
			Staff staff = (Staff) objects[3];
			admissionDTO.setPatient(patient);
			admissionDTO.setStaff(staff);
			admissionDTO.setOrganizationDTO(organizationDTO);
			admissions.add(admissionDTO);
		}
		return admissions;
	}

	public static ArrayList<AdmissionDTO> admissionNoBy(String admissionNo) {
		String sql = "SELECT t1.*, t2.*, t3.*, t4.* FROM AQADMISSION t1 JOIN AQORGANIZATION t2 ON t1.ORGANIZATIONID=t2.ORGANIZATIONID JOIN AQPATIENT t3 ON t1.PATIENTID = t3.PATIENTID JOIN AQSTAFF t4 ON t1.STAFFID = t4.PRSID WHERE t1.ADMISSIONNO = :admissionNo AND t1.status = :status";

		SQLQuery query = DBHibernate.getSession().createSQLQuery(sql);
		query.addEntity("t1", AdmissionDTO.class);
		query.addEntity("t2", OrganizationDTO.class);
		query.addEntity("t3", Patient.class);
		query.addEntity("t4", Staff.class);
		query.setParameter("admissionNo", admissionNo);
		query.setParameter("status", true);

		ArrayList<Object[]> results = (ArrayList<Object[]>) query.list();
		ArrayList<AdmissionDTO> admissions = new ArrayList();

		for (Object[] objects : results) {
			AdmissionDTO admissionDTO = (AdmissionDTO) objects[0];
			OrganizationDTO organizationDTO = (OrganizationDTO) objects[1];
			Patient patient = (Patient) objects[2];
			Staff staff = (Staff) objects[3];
			admissionDTO.setPatient(patient);
			admissionDTO.setStaff(staff);
			admissionDTO.setOrganizationDTO(organizationDTO);
			admissions.add(admissionDTO);
		}
		return admissions;
	}

	private String openEditor() {
		JDialog dialog = new JDialog(JOptionPane.getFrameForComponent(this),
				"Create Patient", true);
		AdmissionCancelEditor admissionCancelEditor = new AdmissionCancelEditor();
		admissionCancelEditor.setDialog(dialog);
		dialog.getContentPane().add(admissionCancelEditor);
		dialog.setLocationByPlatform(true);
		dialog.setSize(600, 250);
		dialog.setVisible(true);
		return admissionCancelEditor.getReason();
	}
	

	private Patient openEditor2() {
		JDialog dialog = new JDialog(JOptionPane.getFrameForComponent(this),
				"Find Patient", true);
		PatientSearchPanel patientSearchPanel = new PatientSearchPanel();
		patientSearchPanel.setDialog(dialog);
		dialog.getContentPane().add(patientSearchPanel);
		dialog.setLocationByPlatform(true);
		dialog.setSize(800,600);
		dialog.setVisible(true);
		return patientSearchPanel.getPatient();
	}

	
	

	public JDialog getDialog() {
		return dialog;
	}

	public void setDialog(JDialog dialog) {
		this.dialog = dialog;
	}

	public int getAdmissionid() {
		return admissionid;
	}

	public void setAdmissionid(int admissionid) {
		this.admissionid = admissionid;
	}

	public CallAdmission getIsCalled() {
		return isCalled;
	}

	public void setIsCalled(CallAdmission isCalled) {
		this.isCalled = isCalled;
	}

 
	
}
