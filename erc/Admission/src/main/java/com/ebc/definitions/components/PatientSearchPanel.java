package com.ebc.definitions.components;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;

import org.hibernate.SQLQuery;

import com.ebc.core.AqBaseEntity;
import com.ebc.core.DBHibernate;
import com.ebc.definitions.*;
import com.ebc.definitions.admission.entity.AdmissionDTO;
import com.ebc.definitions.admission.panel.AdmissionPanel;
import com.ebc.definitions.entity.TestDefinitionDTO;
import com.ebc.definitions.patient.model.Patient;
import com.ebc.definitions.patient.panel.PatientTableModel;

import javax.swing.JScrollPane;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class PatientSearchPanel extends JPanel {
	private JTextField txtPatientSearchName;
	private JTextField txtPatientSearchLName;
	private JDialog dialog;
	private Patient patient;
	private PatientSearchModel patientSearchModel = new PatientSearchModel();
	private JTable tblPatientSearch;
	private AdmissionPanel panel;

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public JDialog getDialog() {
		return dialog;
	}

	public void setDialog(JDialog dialog) {
		this.dialog = dialog;
	}

	public PatientSearchPanel() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 5, 0, 0, 0, 5, 0 };
		gridBagLayout.rowHeights = new int[] { 5, 0, 0, 0, 5, 0, 0, 0, 0, 0, 0,
				5, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 0.0, 1.0, 0.0, 0.0,
				Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				0.0, 1.0, 1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		setLayout(gridBagLayout);

		JLabel lblPatientSearchName = new JLabel("Patient Name : ");
		lblPatientSearchName.setFont(new Font("Tahoma", Font.PLAIN, 16));
		GridBagConstraints gbc_lblPatientSearchName = new GridBagConstraints();
		gbc_lblPatientSearchName.anchor = GridBagConstraints.WEST;
		gbc_lblPatientSearchName.insets = new Insets(0, 0, 5, 5);
		gbc_lblPatientSearchName.gridx = 1;
		gbc_lblPatientSearchName.gridy = 1;
		add(lblPatientSearchName, gbc_lblPatientSearchName);

		txtPatientSearchName = new JTextField();
		GridBagConstraints gbc_txtPatientSearchName = new GridBagConstraints();
		gbc_txtPatientSearchName.gridwidth = 2;
		gbc_txtPatientSearchName.insets = new Insets(0, 0, 5, 5);
		gbc_txtPatientSearchName.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtPatientSearchName.gridx = 2;
		gbc_txtPatientSearchName.gridy = 1;
		add(txtPatientSearchName, gbc_txtPatientSearchName);
		txtPatientSearchName.setColumns(10);

		JLabel lblNewLabel = new JLabel("Patient Last Name : ");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 1;
		gbc_lblNewLabel.gridy = 2;
		add(lblNewLabel, gbc_lblNewLabel);

		txtPatientSearchLName = new JTextField();
		GridBagConstraints gbc_txtPatientSearchLName = new GridBagConstraints();
		gbc_txtPatientSearchLName.gridwidth = 2;
		gbc_txtPatientSearchLName.insets = new Insets(0, 0, 5, 5);
		gbc_txtPatientSearchLName.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtPatientSearchLName.gridx = 2;
		gbc_txtPatientSearchLName.gridy = 2;
		add(txtPatientSearchLName, gbc_txtPatientSearchLName);
		txtPatientSearchLName.setColumns(10);

		JButton btnPatientSearch = new JButton("Search");
		
			
		
		GridBagConstraints gbc_btnPatientSearch = new GridBagConstraints();
		gbc_btnPatientSearch.anchor = GridBagConstraints.NORTH;
		gbc_btnPatientSearch.insets = new Insets(0, 0, 5, 5);
		gbc_btnPatientSearch.gridx = 3;
		gbc_btnPatientSearch.gridy = 3;
		add(btnPatientSearch, gbc_btnPatientSearch);

		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridwidth = 3;
		gbc_scrollPane.gridheight = 5;
		gbc_scrollPane.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 1;
		gbc_scrollPane.gridy = 6;
		add(scrollPane, gbc_scrollPane);

		tblPatientSearch = new JTable();
		tblPatientSearch.setFont(new Font("Tahoma", Font.PLAIN, 17));
		scrollPane.setColumnHeaderView(tblPatientSearch);
		tblPatientSearch.setRowHeight(45);
		scrollPane.setViewportView(tblPatientSearch);
		tblPatientSearch.setModel(patientSearchModel);

		
		
		txtPatientSearchName.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				if(e.getKeyCode()==KeyEvent.VK_ENTER) {
					getAll();
				}
			}
		});
		
		
		txtPatientSearchLName.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				if(e.getKeyCode()==KeyEvent.VK_ENTER) {
					getAll();
				}
			}
		});
		
		
		
		
		btnPatientSearch.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
					getAll();
				
			}
		});
		
		
		
		

		tblPatientSearch.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				if (e.getClickCount() == 2) {

					int selectedRow = tblPatientSearch.getSelectedRow();

					Patient patient = patientSearchModel.getPatient().get(
							selectedRow);
					setPatient(patient);
					dialog.dispose();

				}

			}
		});

	}
	
	public void getAll() {
		String name = txtPatientSearchName.getText();
		String lastName = txtPatientSearchLName.getText();

		StringBuilder sql = new StringBuilder(
				"select t1.* from  aqpatient t1 ");
		sql.append(" WHERE ");
		sql.append("  ltrim(rtrim(lower(t1.name))) like ?  ");
		sql.append(" AND lower(t1.lastName) like ?  ");

		SQLQuery query = DBHibernate.getSession().createSQLQuery(
				sql.toString());

		query.addEntity("t1", Patient.class);
		query.setString(0, "%" + name.toLowerCase() + "%");
		query.setString(1, "%" + lastName.toLowerCase() + "%");
		ArrayList<Patient> list = (ArrayList<Patient>) query.list();

		patientSearchModel.setPatient(list);

		patientSearchModel.fireTableDataChanged();

	}

}
