package com.ebc.outpatient.anamnez.panel;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.time.LocalDateTime; // Import the LocalDateTime class
import java.time.format.DateTimeFormatter; // Import the DateTimeFormatter class
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import org.hibernate.SQLQuery;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.SimpleExpression;

import com.ebc.core.DBHibernate;
import com.ebc.definitions.admission.entity.AdmissionDTO;
import com.ebc.definitions.organization.entity.OrganizationDTO;
import com.ebc.definitions.patient.model.Patient;
import com.ebc.definitions.session.Session;
import com.ebc.definitions.staff.entity.Staff;
import com.ebc.outpatient.anamnez.entity.Anamnez;
import com.ebc.outpatient.doctor.DoctorMainPanel;

public class AnamnezPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTable table;
	private JButton btnAdd;
	private JButton btnEdit;
	private JButton btnDelete;

	private LocalDateTime myDateObj = LocalDateTime.now();
	private DateTimeFormatter myFormatObj = DateTimeFormatter
			.ofPattern("dd-MM-yyyy HH:mm:ss");

	private Anamnez anamnez = new Anamnez();
	private ArrayList<Anamnez> anamnezList = new ArrayList<Anamnez>();
	private AnamnezTableModel anamnezTableModel = new AnamnezTableModel();

	public AnamnezPanel() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 15, 0, 0, 0, 0, 0, 0, 15, 0 };
		gridBagLayout.rowHeights = new int[] { 15, 0, 5, 0, 0, 0, 15, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 0.0, 1.0, 0.0, 0.0,
				0.0, 0.0, 0.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 1.0, 1.0, 0.0,
				0.0, Double.MIN_VALUE };
		setLayout(gridBagLayout);

		btnAdd = new JButton("ADD");
		GridBagConstraints gbc_btnAdd = new GridBagConstraints();
		gbc_btnAdd.insets = new Insets(0, 0, 5, 5);
		gbc_btnAdd.gridx = 3;
		gbc_btnAdd.gridy = 1;
		add(btnAdd, gbc_btnAdd);

		btnEdit = new JButton("EDIT");
		GridBagConstraints gbc_btnEdit = new GridBagConstraints();
		gbc_btnEdit.insets = new Insets(0, 0, 5, 5);
		gbc_btnEdit.gridx = 4;
		gbc_btnEdit.gridy = 1;
		add(btnEdit, gbc_btnEdit);

		btnDelete = new JButton("DELETE");
		GridBagConstraints gbc_btnDelete = new GridBagConstraints();
		gbc_btnDelete.insets = new Insets(0, 0, 5, 5);
		gbc_btnDelete.gridx = 5;
		gbc_btnDelete.gridy = 1;
		add(btnDelete, gbc_btnDelete);

		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridheight = 3;
		gbc_scrollPane.gridwidth = 6;
		gbc_scrollPane.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 1;
		gbc_scrollPane.gridy = 3;
		add(scrollPane, gbc_scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(anamnezTableModel);

		// anamnezTableModel.setAnamnezS(getAllAnamnez());
		// anamnezTableModel.fireTableDataChanged();

		btnAdd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				AnamnezButton();
			}
		});

		btnEdit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int i = table.getSelectedRow();
				anamnezTableModel.getAnamnezS().get(i);
			}
		});

		btnDelete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int i = table.getSelectedRow();
				Anamnez anamnez = anamnezTableModel.getAnamnezS().get(i);
				anamnez.setStatus(false);
				DBHibernate.update(anamnez);
			}
		});

	}

	public ArrayList<Anamnez> getAnamnezsForAdmissionId(Long admissionId) {
		String sql = "SELECT t1.*, t2.* FROM AQANAMNEZ t1 JOIN AQSTAFF t2 ON t2.PRSID=t1.CREATEDBY WHERE t1.ADMISSIONID = :admissionid AND t1.status = 1";

		SQLQuery query = DBHibernate.getSession().createSQLQuery(sql);
		query.addEntity("t1", Anamnez.class);
		query.addEntity("t2", Staff.class);
		query.setParameter("admissionid", admissionId);
		// query.setParameter("status", false);

		ArrayList<Object[]> results = (ArrayList<Object[]>) query.list();
		ArrayList<Anamnez> anamnezList = new ArrayList();

		for (Object[] objects : results) {
			Anamnez anamnez = (Anamnez) objects[0];
			Staff staff = (Staff) objects[1];
			anamnez.setStaff(staff);
			anamnezList.add(anamnez);
		}
		return anamnezList;
	}

	private ArrayList<Anamnez> getAllAnamnez() {
		SimpleExpression rest = Restrictions.eq("status", false);
		return DBHibernate.getAll(Anamnez.class, new ArrayList(), rest);
	}

	private String openEditor() {
		JDialog dialog = new JDialog(JOptionPane.getFrameForComponent(this),
				"Create Patient", true);
		AnamnezEditor anamnezEditor = new AnamnezEditor();
		anamnezEditor.setDialog(dialog);
		dialog.getContentPane().add(anamnezEditor);
		dialog.setLocationByPlatform(true);
		dialog.setSize(600, 250);
		dialog.setVisible(true);
		return anamnezEditor.getAnamnezDetail();
	}

	private void AnamnezButton() {
		anamnez.setDetail(openEditor());

		DoctorMainPanel doctorMainPanel = new DoctorMainPanel();
		Long admissionid = doctorMainPanel.getAdmissionIdForAnamnez();
		anamnez.setAdmissionId(admissionid);

		anamnez.setCreatedBy(Long.valueOf(Session.getSession().getStaffdto()
				.getPrsId()));

		anamnez.getUpdatedBy();

		String formattedDate = myDateObj.format(myFormatObj);
		anamnez.setCreatedAt(formattedDate);

		anamnez.setUpdatedAt(null);
		anamnez.setStatus(true);

		try {
			DBHibernate.save(anamnez);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		anamnezTableModel.setAnamnezS(getAnamnezsForAdmissionId(admissionid));
		anamnezTableModel.fireTableDataChanged();
	}

}
