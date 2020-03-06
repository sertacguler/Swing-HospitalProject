package com.ebc.definitions.staff.panel;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.SimpleExpression;

import com.ebc.core.AqBaseEntity;
import com.ebc.core.DBHibernate;
import com.ebc.definitions.combobox.ComboboxModel;
import com.ebc.definitions.organization.entity.OrganizationDTO;
import com.ebc.definitions.staff.entity.MD5PasswordGenerator;
import com.ebc.definitions.staff.entity.Staff;
import com.toedter.calendar.JDateChooser;

public class StaffEditor extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField txtname;
	private JTextField txtsurname;
	private JTextField txtage;
	private JTextField txtemail;
	private JTextField txtadress;
	private JTextField txtmobilephone;
	private JTextField txthomephone;
	private JTextField txtworkphone;
	private JTextField txtpassword;
	private JDialog jDialog;
	private ArrayList<AqBaseEntity> organizations;
	private Staff staff;
	private ComboboxModel comboboxModel2 = new ComboboxModel();

	public ArrayList<AqBaseEntity> getOrganizations() {
		return organizations;
	}

	public void setOrganizations(ArrayList<AqBaseEntity> organizations) {
		this.organizations = organizations;
	}

	public JTextField getTxtname() {
		return txtname;
	}

	public void setTxtname(JTextField txtname) {
		this.txtname = txtname;
	}

	public JTextField getTxtsurname() {
		return txtsurname;
	}

	public void setTxtsurname(JTextField txtsurname) {
		this.txtsurname = txtsurname;
	}

	public JTextField getTxtage() {
		return txtage;
	}

	public void setTxtage(JTextField txtage) {
		this.txtage = txtage;
	}

	public JTextField getTxtemail() {
		return txtemail;
	}

	public void setTxtemail(JTextField txtemail) {
		this.txtemail = txtemail;
	}

	public JTextField getTxtadress() {
		return txtadress;
	}

	public void setTxtadress(JTextField txtadress) {
		this.txtadress = txtadress;
	}

	public JTextField getTxtmobilephone() {
		return txtmobilephone;
	}

	public void setTxtmobilephone(JTextField txtmobilephone) {
		this.txtmobilephone = txtmobilephone;
	}

	public JTextField getTxthomephone() {
		return txthomephone;
	}

	public void setTxthomephone(JTextField txthomephone) {
		this.txthomephone = txthomephone;
	}

	public JTextField getTxtworkphone() {
		return txtworkphone;
	}

	public void setTxtworkphone(JTextField txtworkphone) {
		this.txtworkphone = txtworkphone;
	}

	public Staff getStaff() {
		return staff;
	}

	public void setStaff(Staff staff) {
		this.staff = staff;
	}

	public JDialog getjDialog() {
		return jDialog;
	}

	public void setjDialog(JDialog jDialog) {
		this.jDialog = jDialog;
	}

	public StaffEditor(Staff staffdetail) {

		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 15, 0, 81, 81, 0, 0, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 33, 38, 37,
				36, 0, 0, 0, 0, 0, 15, 0, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 1.0, 0.0, 0.0, 0.0, 1.0,
				0.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 1.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 1.0,
				Double.MIN_VALUE };
		setLayout(gridBagLayout);

		JLabel jname = new JLabel("Name");
		GridBagConstraints gbc_jPassword = new GridBagConstraints();
		gbc_jPassword.anchor = GridBagConstraints.WEST;
		gbc_jPassword.insets = new Insets(0, 0, 5, 5);
		gbc_jPassword.gridx = 1;
		gbc_jPassword.gridy = 1;
		add(jname, gbc_jPassword);

		txtname = new JTextField();
		GridBagConstraints gbc_txtname = new GridBagConstraints();
		gbc_txtname.gridwidth = 2;
		gbc_txtname.insets = new Insets(0, 0, 5, 5);
		gbc_txtname.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtname.gridx = 2;
		gbc_txtname.gridy = 1;
		add(txtname, gbc_txtname);
		txtname.setColumns(10);

		JLabel jSurname = new JLabel("Surname");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 1;
		gbc_lblNewLabel_1.gridy = 2;
		add(jSurname, gbc_lblNewLabel_1);

		txtsurname = new JTextField();
		GridBagConstraints gbc_txtsurname = new GridBagConstraints();
		gbc_txtsurname.gridwidth = 2;
		gbc_txtsurname.insets = new Insets(0, 0, 5, 5);
		gbc_txtsurname.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtsurname.gridx = 2;
		gbc_txtsurname.gridy = 2;
		add(txtsurname, gbc_txtsurname);
		txtsurname.setColumns(10);

		JLabel jGender = new JLabel("Gender");
		GridBagConstraints gbc_lblGender = new GridBagConstraints();
		gbc_lblGender.anchor = GridBagConstraints.WEST;
		gbc_lblGender.insets = new Insets(0, 0, 5, 5);
		gbc_lblGender.gridx = 1;
		gbc_lblGender.gridy = 3;
		add(jGender, gbc_lblGender);

		JRadioButton jrMale = new JRadioButton("Male");
		GridBagConstraints gbc_rdbtnNewRadioButton_1 = new GridBagConstraints();
		gbc_rdbtnNewRadioButton_1.anchor = GridBagConstraints.WEST;
		gbc_rdbtnNewRadioButton_1.insets = new Insets(0, 0, 5, 5);
		gbc_rdbtnNewRadioButton_1.gridx = 2;
		gbc_rdbtnNewRadioButton_1.gridy = 3;
		add(jrMale, gbc_rdbtnNewRadioButton_1);

		JRadioButton jrFemale = new JRadioButton("Female");
		GridBagConstraints gbc_rdbtnNewRadioButton = new GridBagConstraints();
		gbc_rdbtnNewRadioButton.anchor = GridBagConstraints.WEST;
		gbc_rdbtnNewRadioButton.insets = new Insets(0, 0, 5, 5);
		gbc_rdbtnNewRadioButton.gridx = 3;
		gbc_rdbtnNewRadioButton.gridy = 3;
		add(jrFemale, gbc_rdbtnNewRadioButton);

		JLabel jAge = new JLabel("Age");
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_2.gridx = 1;
		gbc_lblNewLabel_2.gridy = 4;
		add(jAge, gbc_lblNewLabel_2);

		txtage = new JTextField();
		GridBagConstraints gbc_txtage = new GridBagConstraints();
		gbc_txtage.gridwidth = 2;
		gbc_txtage.insets = new Insets(0, 0, 5, 5);
		gbc_txtage.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtage.gridx = 2;
		gbc_txtage.gridy = 4;
		add(txtage, gbc_txtage);
		txtage.setColumns(10);

		JLabel jTask = new JLabel("Task");
		GridBagConstraints gbc_lblTask = new GridBagConstraints();
		gbc_lblTask.anchor = GridBagConstraints.WEST;
		gbc_lblTask.insets = new Insets(0, 0, 5, 5);
		gbc_lblTask.gridx = 1;
		gbc_lblTask.gridy = 5;
		add(jTask, gbc_lblTask);

		JComboBox comboboxTask = new JComboBox();
		comboboxTask.setModel(new DefaultComboBoxModel(new String[] { "Doctor",
				"Nurse", "Technician", "Pharmacist" }));
		GridBagConstraints gbc_comboboxTask = new GridBagConstraints();
		gbc_comboboxTask.gridwidth = 2;
		gbc_comboboxTask.insets = new Insets(0, 0, 5, 5);
		gbc_comboboxTask.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboboxTask.gridx = 2;
		gbc_comboboxTask.gridy = 5;
		add(comboboxTask, gbc_comboboxTask);

		JLabel jOrganizationname = new JLabel("Organization Name");
		GridBagConstraints gbc_jOrganizationname = new GridBagConstraints();
		gbc_jOrganizationname.anchor = GridBagConstraints.WEST;
		gbc_jOrganizationname.insets = new Insets(0, 0, 5, 5);
		gbc_jOrganizationname.gridx = 1;
		gbc_jOrganizationname.gridy = 6;
		add(jOrganizationname, gbc_jOrganizationname);

		JComboBox comboOrganization = new JComboBox();
		GridBagConstraints gbc_comboOrganization = new GridBagConstraints();
		gbc_comboOrganization.gridwidth = 2;
		gbc_comboOrganization.insets = new Insets(0, 0, 5, 5);
		gbc_comboOrganization.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboOrganization.gridx = 2;
		gbc_comboOrganization.gridy = 6;
		add(comboOrganization, gbc_comboOrganization);
		getOrganizationS();
		comboboxModel2.setValues(getOrganizations());
		comboboxModel2.initialize();
		comboOrganization.setModel(comboboxModel2);

		JLabel jBirthdate = new JLabel("Birthdate");
		GridBagConstraints gbc_lblNewLabel_4 = new GridBagConstraints();
		gbc_lblNewLabel_4.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_4.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_4.gridx = 1;
		gbc_lblNewLabel_4.gridy = 7;
		add(jBirthdate, gbc_lblNewLabel_4);

		JLabel jStarteddate = new JLabel("Starteddate");
		GridBagConstraints gbc_lblStarteddate = new GridBagConstraints();
		gbc_lblStarteddate.anchor = GridBagConstraints.WEST;
		gbc_lblStarteddate.insets = new Insets(0, 0, 5, 5);
		gbc_lblStarteddate.gridx = 1;
		gbc_lblStarteddate.gridy = 8;
		add(jStarteddate, gbc_lblStarteddate);

		JLabel jEmail = new JLabel("Email");
		GridBagConstraints gbc_lblNewLabel_5 = new GridBagConstraints();
		gbc_lblNewLabel_5.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_5.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_5.gridx = 1;
		gbc_lblNewLabel_5.gridy = 9;
		add(jEmail, gbc_lblNewLabel_5);

		txtemail = new JTextField();
		GridBagConstraints gbc_txtemail = new GridBagConstraints();
		gbc_txtemail.gridwidth = 2;
		gbc_txtemail.insets = new Insets(0, 0, 5, 5);
		gbc_txtemail.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtemail.gridx = 2;
		gbc_txtemail.gridy = 9;
		add(txtemail, gbc_txtemail);
		txtemail.setColumns(10);

		JLabel jAdress = new JLabel("Adress");
		GridBagConstraints gbc_lblNewLabel_6 = new GridBagConstraints();
		gbc_lblNewLabel_6.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_6.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_6.gridx = 1;
		gbc_lblNewLabel_6.gridy = 10;
		add(jAdress, gbc_lblNewLabel_6);

		txtadress = new JTextField();
		GridBagConstraints gbc_txtadress = new GridBagConstraints();
		gbc_txtadress.gridwidth = 2;
		gbc_txtadress.insets = new Insets(0, 0, 5, 5);
		gbc_txtadress.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtadress.gridx = 2;
		gbc_txtadress.gridy = 10;
		add(txtadress, gbc_txtadress);
		txtadress.setColumns(10);

		JLabel jPnumber = new JLabel("Phone Number");
		GridBagConstraints gbc_lblNewLabel_7 = new GridBagConstraints();
		gbc_lblNewLabel_7.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_7.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_7.gridx = 1;
		gbc_lblNewLabel_7.gridy = 11;
		add(jPnumber, gbc_lblNewLabel_7);

		txtmobilephone = new JTextField();
		GridBagConstraints gbc_txtmobilephone = new GridBagConstraints();
		gbc_txtmobilephone.gridwidth = 2;
		gbc_txtmobilephone.insets = new Insets(0, 0, 5, 5);
		gbc_txtmobilephone.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtmobilephone.gridx = 2;
		gbc_txtmobilephone.gridy = 11;
		add(txtmobilephone, gbc_txtmobilephone);
		txtmobilephone.setColumns(10);

		JLabel jHnumber = new JLabel("Home Phone Number");
		GridBagConstraints gbc_lblNewLabel_8 = new GridBagConstraints();
		gbc_lblNewLabel_8.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_8.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_8.gridx = 1;
		gbc_lblNewLabel_8.gridy = 12;
		add(jHnumber, gbc_lblNewLabel_8);

		txthomephone = new JTextField();
		GridBagConstraints gbc_txthomephone = new GridBagConstraints();
		gbc_txthomephone.gridwidth = 2;
		gbc_txthomephone.insets = new Insets(0, 0, 5, 5);
		gbc_txthomephone.fill = GridBagConstraints.HORIZONTAL;
		gbc_txthomephone.gridx = 2;
		gbc_txthomephone.gridy = 12;
		add(txthomephone, gbc_txthomephone);
		txthomephone.setColumns(10);

		JLabel jWNumber = new JLabel("Work Phone");
		GridBagConstraints gbc_lblNewLabel_9 = new GridBagConstraints();
		gbc_lblNewLabel_9.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_9.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_9.gridx = 1;
		gbc_lblNewLabel_9.gridy = 13;
		add(jWNumber, gbc_lblNewLabel_9);

		txtworkphone = new JTextField();
		GridBagConstraints gbc_txtworkphone = new GridBagConstraints();
		gbc_txtworkphone.gridwidth = 2;
		gbc_txtworkphone.insets = new Insets(0, 0, 5, 5);
		gbc_txtworkphone.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtworkphone.gridx = 2;
		gbc_txtworkphone.gridy = 13;
		add(txtworkphone, gbc_txtworkphone);
		txtworkphone.setColumns(10);

		JDateChooser jbirthDateChooser = new JDateChooser();
		GridBagConstraints gbc_birthDateChooser = new GridBagConstraints();
		gbc_birthDateChooser.gridwidth = 2;
		gbc_birthDateChooser.insets = new Insets(0, 0, 5, 5);
		gbc_birthDateChooser.fill = GridBagConstraints.BOTH;
		gbc_birthDateChooser.gridx = 2;
		gbc_birthDateChooser.gridy = 7;
		add(jbirthDateChooser, gbc_birthDateChooser);

		JDateChooser jstartedDateChooser = new JDateChooser();
		GridBagConstraints gbc_startedDateChooser = new GridBagConstraints();
		gbc_startedDateChooser.gridwidth = 2;
		gbc_startedDateChooser.insets = new Insets(0, 0, 5, 5);
		gbc_startedDateChooser.fill = GridBagConstraints.BOTH;
		gbc_startedDateChooser.gridx = 2;
		gbc_startedDateChooser.gridy = 8;
		add(jstartedDateChooser, gbc_startedDateChooser);

		JLabel jPassword = new JLabel("Password");
		GridBagConstraints gbc_jPassword1 = new GridBagConstraints();
		gbc_jPassword1.anchor = GridBagConstraints.WEST;
		gbc_jPassword1.insets = new Insets(0, 0, 5, 5);
		gbc_jPassword1.gridx = 1;
		gbc_jPassword1.gridy = 14;
		add(jPassword, gbc_jPassword1);

		txtpassword = new JTextField();
		GridBagConstraints gbc_txtpassword = new GridBagConstraints();
		gbc_txtpassword.gridwidth = 2;
		gbc_txtpassword.insets = new Insets(0, 0, 5, 5);
		gbc_txtpassword.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtpassword.gridx = 2;
		gbc_txtpassword.gridy = 14;
		add(txtpassword, gbc_txtpassword);
		txtpassword.setColumns(10);

		ButtonGroup gp = new ButtonGroup();
		gp.add(jrMale);
		gp.add(jrFemale);

		JButton btnSave = new JButton("Save");
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.anchor = GridBagConstraints.EAST;
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton.gridx = 2;
		gbc_btnNewButton.gridy = 15;
		add(btnSave, gbc_btnNewButton);

		JButton btnCancel = new JButton("Cancel");
		GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
		gbc_btnNewButton_1.anchor = GridBagConstraints.EAST;
		gbc_btnNewButton_1.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_1.gridx = 3;
		gbc_btnNewButton_1.gridy = 15;
		add(btnCancel, gbc_btnNewButton_1);

		btnCancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				jDialog.dispose();

			}
		});

		btnSave.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				if (staff == null) {
					staff = new Staff();

				}

				staff.setPrsName(txtname.getText());
				staff.setPrsSurname(txtsurname.getText());

				if (jrMale.isSelected()) {
					staff.setPrsGender("Male");
				} else
					staff.setPrsGender("Female");

				staff.setPrsStatus(true);
				staff.setPrsAge(Integer.parseInt(txtage.getText()));
				staff.setPrsTask(String.valueOf(comboboxTask.getSelectedItem()));
				// staff.setPrsBirthdate(jbirthDateChooser.getDate());
				// staff.setPrsStarteddate(jstartedDateChooser.getDate());
				staff.setPrsEmail(txtemail.getText());
				staff.setPrsAdress(txtadress.getText());
				staff.setPrsPhonenumber(txtmobilephone.getText());
				staff.setPrsHomePhonenumber(txthomephone.getText());
				staff.setPrsWorkphone(txtworkphone.getText());
				String cevir = MD5PasswordGenerator
						.passwordGenerator(txtpassword.getText());
				staff.setPrsPassword(cevir);
				staff.setPrsState(true);

				OrganizationDTO org = (OrganizationDTO) comboOrganization
						.getSelectedItem();

				staff.setPrsOrganizationId(org.getOrganizationId());

				jDialog.dispose();
			}

		});

		if (staffdetail != null) {

			txtname.setText(staffdetail.getPrsName());
			txtsurname.setText(staffdetail.getPrsSurname());
			txtage.setText(String.valueOf(staffdetail.getPrsAge()));

			comboboxTask.setSelectedItem(staffdetail.getPrsTask());

			if (staffdetail.getPrsGender().equals("Male")) {
				jrMale.setSelected(true);
				jrFemale.setSelected(false);
			} else {
				jrMale.setSelected(false);
				jrFemale.setSelected(true);
			}

			jbirthDateChooser.setDate(staffdetail.getPrsBirthdate());
			jstartedDateChooser.setDate(staffdetail.getPrsStarteddate());
			txtemail.setText(staffdetail.getPrsEmail());
			txtadress.setText(staffdetail.getPrsAdress());
			txtmobilephone.setText(staffdetail.getPrsPhonenumber());
			txthomephone.setText(staffdetail.getPrsHomePhonenumber());
			txtworkphone.setText(staffdetail.getPrsWorkphone());

			// comboboxTask.setSelectedItem(staffdetail);

		}

		staff = staffdetail;

	}

	private void getOrganizationS() {
		SimpleExpression rest = Restrictions.eq("status", true);
		this.organizations = (DBHibernate.getAll(OrganizationDTO.class,
				new ArrayList(), rest));

	}

}