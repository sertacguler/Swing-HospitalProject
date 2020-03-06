package com.ebc.admission.appointment.editor;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.ebc.admission.appointment.entity.AppointmentDto;
import com.ebc.admission.appointment.entity.OrganizationWrapper;
import com.ebc.admission.appointment.entity.PatientWrapper;
import com.ebc.admission.appointment.entity.StaffWrapper;
import com.ebc.core.AqBaseEntity;
import com.ebc.core.DBHibernate;
import com.ebc.definitions.combobox.ComboboxModel;
import com.ebc.definitions.organization.entity.OrganizationDTO;
import com.ebc.definitions.patient.model.Patient;
import com.ebc.definitions.staff.entity.Staff;

import java.awt.GridBagLayout;

import javax.swing.JLabel;

import java.awt.GridBagConstraints;
import java.awt.Insets;

import com.toedter.calendar.JDateChooser;

import javax.swing.JComboBox;

import java.awt.Font;

import javax.swing.JButton;

import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.SimpleExpression;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public class AppointmentEditor extends JPanel  {
	private JPanel panel;
	private JDialog dialog;
	private ArrayList<Patient> patients;
	private ArrayList<Staff> staffers;
	private ArrayList<OrganizationDTO> organizations;
	private JComboBox comboBoxPatient;
	private JComboBox comboBoxPersonel;
	private JComboBox comboBoxOrganization;
	private ArrayList<PatientWrapper> patientWrappers = new ArrayList<PatientWrapper>();
	private ArrayList<StaffWrapper> staffWrappers = new ArrayList<StaffWrapper>();
	private ArrayList<OrganizationWrapper> organizationWrappers = new ArrayList<OrganizationWrapper>();
	private JButton btnSave;
	private JButton btnCancel;
	private JDateChooser dateChooser;
	private AppointmentDto appointmentDto;
	private ComboboxModel comboboxModelPatient = new ComboboxModel();
	private ComboboxModel comboboxModelStaff = new ComboboxModel();
	private ComboboxModel comboboxModelOrganization = new ComboboxModel();
	
	public AppointmentEditor() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{10, 0, 10, 0, 0, 0, 10, 0};
		gridBagLayout.rowHeights = new int[]{10, 47, 10, 0, 10, 0, 10, 0, 20, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel lblCreateDate = new JLabel("Create Date");
		lblCreateDate.setFont(new Font("Tahoma", Font.BOLD, 17));
		GridBagConstraints gbc_lblCreateDate = new GridBagConstraints();
		gbc_lblCreateDate.insets = new Insets(0, 0, 5, 5);
		gbc_lblCreateDate.gridx = 1;
		gbc_lblCreateDate.gridy = 1;
		add(lblCreateDate, gbc_lblCreateDate);
		
		dateChooser = new JDateChooser();
		GridBagConstraints gbc_dateChooser = new GridBagConstraints();
		gbc_dateChooser.gridwidth = 3;
		gbc_dateChooser.insets = new Insets(0, 0, 5, 5);
		gbc_dateChooser.fill = GridBagConstraints.BOTH;
		gbc_dateChooser.gridx = 3;
		gbc_dateChooser.gridy = 1;
		dateChooser.setDateFormatString("dd-MM-yyyy HH:mm");
		dateChooser.setFont(new Font("Tahoma", Font.BOLD, 17));
		add(dateChooser, gbc_dateChooser);
		
		JLabel lblPatient = new JLabel("Patient");
		lblPatient.setFont(new Font("Tahoma", Font.BOLD, 17));
		GridBagConstraints gbc_lblPatient = new GridBagConstraints();
		gbc_lblPatient.insets = new Insets(0, 0, 5, 5);
		gbc_lblPatient.gridx = 1;
		gbc_lblPatient.gridy = 3;
		add(lblPatient, gbc_lblPatient);
		
		comboBoxPatient = new JComboBox();
		comboBoxPatient.setFont(new Font("Tahoma", Font.PLAIN, 17));
		GridBagConstraints gbc_comboBoxPatient = new GridBagConstraints();
		gbc_comboBoxPatient.gridwidth = 3;
		gbc_comboBoxPatient.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxPatient.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxPatient.gridx = 3;
		gbc_comboBoxPatient.gridy = 3;
		add(comboBoxPatient, gbc_comboBoxPatient);
		
		JLabel lblPersonel = new JLabel("Doctor");
		lblPersonel.setFont(new Font("Tahoma", Font.BOLD, 17));
		GridBagConstraints gbc_lblPersonel = new GridBagConstraints();
		gbc_lblPersonel.insets = new Insets(0, 0, 5, 5);
		gbc_lblPersonel.gridx = 1;
		gbc_lblPersonel.gridy = 5;
		add(lblPersonel, gbc_lblPersonel);
		
		comboBoxPersonel = new JComboBox();
		comboBoxPersonel.setFont(new Font("Tahoma", Font.PLAIN, 17));
		GridBagConstraints gbc_comboBoxPersonel = new GridBagConstraints();
		gbc_comboBoxPersonel.gridwidth = 3;
		gbc_comboBoxPersonel.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxPersonel.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxPersonel.gridx = 3;
		gbc_comboBoxPersonel.gridy = 5;
		add(comboBoxPersonel, gbc_comboBoxPersonel);
		
		JLabel lblOrganization = new JLabel("Organization");
		lblOrganization.setFont(new Font("Tahoma", Font.BOLD, 17));
		GridBagConstraints gbc_lblOrganization = new GridBagConstraints();
		gbc_lblOrganization.insets = new Insets(0, 0, 5, 5);
		gbc_lblOrganization.gridx = 1;
		gbc_lblOrganization.gridy = 7;
		add(lblOrganization, gbc_lblOrganization);
		
		comboBoxOrganization = new JComboBox();
		comboBoxOrganization.setFont(new Font("Tahoma", Font.PLAIN, 17));
		GridBagConstraints gbc_comboBoxOrganization = new GridBagConstraints();
		gbc_comboBoxOrganization.gridwidth = 3;
		gbc_comboBoxOrganization.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxOrganization.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxOrganization.gridx = 3;
		gbc_comboBoxOrganization.gridy = 7;
		add(comboBoxOrganization, gbc_comboBoxOrganization);
		AppointmentGenerelEvent event = new AppointmentGenerelEvent(this);
		
		btnCancel = new JButton("CANCEL");
		btnCancel.setFont(new Font("Tahoma", Font.BOLD, 17));
		GridBagConstraints gbc_btnCancel = new GridBagConstraints();
		gbc_btnCancel.insets = new Insets(0, 0, 5, 5);
		gbc_btnCancel.gridx = 3;
		gbc_btnCancel.gridy = 9;
		add(btnCancel, gbc_btnCancel);
		btnCancel.setActionCommand("CANCEL");
		btnCancel.addActionListener(event);
		
		btnSave = new JButton("SAVE");
		btnSave.setFont(new Font("Tahoma", Font.BOLD, 17));
		GridBagConstraints gbc_btnSave = new GridBagConstraints();
		gbc_btnSave.insets = new Insets(0, 0, 5, 5);
		gbc_btnSave.gridx = 5;
		gbc_btnSave.gridy = 9;
		add(btnSave, gbc_btnSave);
		btnSave.setActionCommand("SAVE");
		btnSave.addActionListener(event);
		fillInComboboxes();
	}
	// full in comboboxes
	private void fillInComboboxes(){
		getAllOrganization();
		getAllPatients();
		getAllStaff();
		//getAllPatient();
	}

	//get All Patients
	public void getAllPatients(){
		SimpleExpression rest = Restrictions.eq("status",1);
		ArrayList<AqBaseEntity> basenetities = DBHibernate.getAll(
				Patient.class, 
				new ArrayList<String> (Arrays.asList("patientid","name")),
				rest
				);

		comboboxModelPatient.setValues(basenetities);
		comboboxModelPatient.initialize();
		comboBoxPatient.setModel(comboboxModelPatient);
//		patients = (ArrayList<Patient>) basenetities.clone();
//		for (Patient patient : patients) {
//			patientWrappers.add(new PatientWrapper(
//					patient.getPatientid(),
//					patient.getName(),
//					patient.getLastname()));
//		}
//		patientWrappers.forEach(patient -> { comboBoxPatient.addItem(patient); });
	}

	
	//get All Staffes
	public void getAllStaff(){
		SimpleExpression rest = Restrictions.eq("prsTask","Doctor");
		ArrayList<AqBaseEntity> basenetities = DBHibernate.getAll(
				Staff.class, 
				new ArrayList<String> (Arrays.asList("prsId")),
				rest
				);

		comboboxModelStaff.setValues(basenetities);
		comboboxModelStaff.initialize();
		comboBoxPersonel.setModel(comboboxModelStaff);
//		staffers = (ArrayList<Staff>) basenetities.clone();
//		for (Staff staff : staffers) {
//			staffWrappers.add(new StaffWrapper(
//					staff.getPrsId(),
//					staff.getPrsName(),
//					staff.getPrsSurname()));
//		}
//		staffWrappers.forEach(staff -> { comboBoxPersonel.addItem(staff); });
	}
	
	
	//get All Organizations
	public void getAllOrganization(){
		SimpleExpression rest = Restrictions.eq("status",true);
		ArrayList<AqBaseEntity> basenetities = DBHibernate.getAll(
				OrganizationDTO.class, 
				new ArrayList<String> (Arrays.asList("organizationId")),
				rest
				);

		comboboxModelOrganization.setValues(basenetities);
		comboboxModelOrganization.initialize();
		comboBoxOrganization.setModel(comboboxModelOrganization);
//		organizations  = (ArrayList<OrganizationDTO>) basenetities.clone();
//		for (OrganizationDTO organization : organizations) {
//			organizationWrappers.add(new OrganizationWrapper(
//					organization.getOrganizationId(),
//					organization.getOrganizationName(), 
//					organization.getOrganizationCode()));
//		}
//		organizationWrappers.forEach(organization -> { comboBoxOrganization.addItem(organization); });
	}
	
	class AppointmentGenerelEvent implements ActionListener {
		private JPanel panel;
		ArrayList<AppointmentDto> appointmentDtos;
		public AppointmentGenerelEvent(JPanel panel) {
			super();
			this.panel = panel;
		}
		public void actionPerformed(ActionEvent e) {
			if (e.getActionCommand().equals("SAVE")) {
				if(dateChooser.getDate() instanceof Date){
					//PatientWrapper patientWrapper = (PatientWrapper) comboBoxPatient.getSelectedItem();
					//StaffWrapper staffWrapper = (StaffWrapper) comboBoxPersonel.getSelectedItem();
					//OrganizationWrapper organizationWrapper = (OrganizationWrapper) comboBoxOrganization.getSelectedItem();

					Patient patient = (Patient) comboBoxPatient.getSelectedItem();
					Staff staff = (Staff) comboBoxPersonel.getSelectedItem();
					OrganizationDTO organizationDTO = (OrganizationDTO) comboBoxOrganization.getSelectedItem();
					System.out.println(patient.getPatientid()+" "+patient.getName()+" "+patient.getLastname());
					System.out.println(staff.getPrsId()+" "+staff.getPrsName()+" "+staff.getPrsSurname());
					System.out.println(organizationDTO.getOrganizationId()+" "+organizationDTO.getOrganizationName());
					appointmentDto = new AppointmentDto(
							dateChooser.getDate(),
							staff.getPrsId(),
							patient.getPatientid(),
							organizationDTO.getOrganizationId(),
							1
							);
					appointmentDto.setAdmissionid(-1);
					
					Boolean control = ControlAppointmentOperation(
							patient.getPatientid(),
							dateChooser.getDate(),
							organizationDTO.getOrganizationId());
					if(control == true ){
						try {
							DBHibernate.save(appointmentDto);
						} catch (SQLException e1) {
							System.out.println("Problem save appointment : "+e);
						}
						dialog.dispose();
						JOptionPane.showMessageDialog(null,"Saved Appointment");
					}else {
						JOptionPane.showMessageDialog(null,"Uncorrect Choosing for same Organization and Date");
					}
				}else {
					JOptionPane.showMessageDialog(null,"Lütfen Tarih giriniz");
				}
			}
			
			else if (e.getActionCommand().equals("CANCEL")) {
				System.out.println("CANCEL");
				dialog.dispose();
				JOptionPane.showMessageDialog(null,"Cancel Saved Operation");
			}
		}
		
		
		// control Appointment Operation whether there is same operation on same day
		public Boolean ControlAppointmentOperation(int patientid,Date date, int organizationid){
			SimpleExpression rest = Restrictions.eq("patientid",patientid);
			ArrayList<AqBaseEntity> basenetities = DBHibernate.getAll(
					AppointmentDto.class, 
					new ArrayList<String> (Arrays.asList("appointmentid")),
					rest
					);
			appointmentDtos = (ArrayList<AppointmentDto>) basenetities.clone();
			for (AppointmentDto appointmentDto : appointmentDtos) {

				if(date.compareTo(appointmentDto.getCreatedAt()) == 0 && appointmentDto.getOrganizationid() == organizationid){
					return false;
				}
			}
			return true;
		}
	}
	
	
	public AppointmentDto getAppointmentDto() {
		return appointmentDto;
	}
	public void setAppointmentDto(AppointmentDto appointmentDto) {
		this.appointmentDto = appointmentDto;
	}
	public JPanel getPanel() {
		return panel;
	}
	public void setPanel(JPanel panel) {
		this.panel = panel;
	}
	public JDialog getDialog() {
		return dialog;
	}
	public void setDialog(JDialog dialog) {
		this.dialog = dialog;
	}
}
