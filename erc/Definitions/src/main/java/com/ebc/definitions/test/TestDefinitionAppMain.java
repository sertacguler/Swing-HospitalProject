package com.ebc.definitions.test;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

import javax.swing.JLabel;

import java.awt.Insets;
import java.awt.Font;

import javax.swing.JCheckBox;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.awt.Color;

import javax.swing.JButton;

import org.hibernate.SQLQuery;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.SimpleExpression;

import com.ebc.core.DBHibernate;
import com.ebc.definitions.entity.TestCategoriesDTO;
import com.ebc.definitions.entity.TestDefinitionDTO;
import com.ebc.definitions.organization.entity.OrganizationDTO;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;
import java.awt.SystemColor;

public class TestDefinitionAppMain extends JPanel {
	private JTable table;
	private final JButton btnAdd = new JButton("ADD");
	private final JButton btnDelete = new JButton("DELETE");
	private ArrayList<TestDefinitionDTO> tstDefDto = new ArrayList<TestDefinitionDTO>();
	private final JButton btnEdit = new JButton("EDIT");
	private TestDefinitionTableModel tblModel = new TestDefinitionTableModel();

	public TestDefinitionAppMain() {
		setForeground(new Color(153, 204, 255));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 0, 0, 0, 0, 0, 0, 5, 0, 0 };
		gridBagLayout.rowHeights = new int[] { 5, 0, 0, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 0.0, 1.0, 0.0, 0.0,
				0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 1.0, 0.0,
				Double.MIN_VALUE };
		setLayout(gridBagLayout);
		GenerealEventListener eventListener = new GenerealEventListener(this);
		JButton btnAdd_1 = new JButton("ADD");
		btnAdd_1.setFont(new Font("Tahoma", Font.PLAIN, 21));
		GridBagConstraints gbc_btnAdd_1 = new GridBagConstraints();
		gbc_btnAdd_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnAdd_1.insets = new Insets(0, 0, 5, 5);
		gbc_btnAdd_1.gridx = 5;
		gbc_btnAdd_1.gridy = 1;
		add(btnAdd_1, gbc_btnAdd_1);
		btnAdd_1.setActionCommand("ADD");
		btnAdd_1.addActionListener(eventListener);

		JButton btnEdit_1 = new JButton("EDIT");
		btnEdit_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		GridBagConstraints gbc_btnEdit_1 = new GridBagConstraints();
		gbc_btnEdit_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnEdit_1.insets = new Insets(0, 0, 5, 5);
		gbc_btnEdit_1.gridx = 6;
		gbc_btnEdit_1.gridy = 1;
		add(btnEdit_1, gbc_btnEdit_1);
		btnEdit_1.setActionCommand("EDIT");
		btnEdit_1.addActionListener(eventListener);

		JButton btnDelete_1 = new JButton("DELETE");
		btnDelete_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		GridBagConstraints gbc_btnDelete_1 = new GridBagConstraints();
		gbc_btnDelete_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnDelete_1.insets = new Insets(0, 0, 5, 5);
		gbc_btnDelete_1.gridx = 7;
		gbc_btnDelete_1.gridy = 1;
		add(btnDelete_1, gbc_btnDelete_1);
		btnDelete_1.setActionCommand("DELETE");
		btnDelete_1.addActionListener(eventListener);

		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridwidth = 7;
		gbc_scrollPane.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 1;
		gbc_scrollPane.gridy = 2;
		add(scrollPane, gbc_scrollPane);

		table = new JTable();
		table.setRowHeight(45);
		table.setBackground(new Color(255, 255, 255));
		table.setFont(new Font("Tahoma", Font.PLAIN, 20));
		table.setForeground(new Color(0, 0, 51));
		scrollPane.setColumnHeaderView(table);
		scrollPane.setViewportView(table);
		table.setModel(tblModel);
		table.setPreferredScrollableViewportSize(table.getPreferredSize());

		// System.out.println(DBHibernate.getAll(OrganizationDTO.class, new
		// ArrayList<>(), rest));

		// SimpleExpression rest = Restrictions.eq("status", true);
		// DBHibernate.getAll(TestDefinitionDTO.class, new ArrayList(), rest);
		// tblModel.setTstDto(DBHibernate.getAll(TestDefinitionDTO.class,
		// new ArrayList(), rest));
		// byId();

		tstDefDto = tblModel.getTstDto();
		tblModel.setTstDto(byId());

		tblModel.fireTableDataChanged();

	}

	class GenerealEventListener implements ActionListener {

		private JPanel panel;

		public GenerealEventListener(JPanel panel) {
			// TODO Auto-generated constructor stub
			this.panel = panel;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			DBHibernate db = new DBHibernate();
			String wrt = e.getActionCommand();
			TestDefinitionDTO tstDTO = null;

			if (wrt.equals("ADD")) {
				tstDTO = openEditor(tstDTO);

				if (tstDTO != null) {
					try {

						tstDTO.setStatus(true);
						db.save(tstDTO);
						db.update(tstDTO);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					tblModel.getDatas().add(tstDTO);
					tblModel.fireTableDataChanged();

				}
			} else if (wrt.equals("DELETE")) {
				SimpleExpression rest = Restrictions.eq("status", true);
				int control = JOptionPane
						.showConfirmDialog(null, "Are u sure?", "Delete Test",
								JOptionPane.YES_NO_OPTION);
				TestDefinitionDTO tstDefinitionDTO = new TestDefinitionDTO();
				if (control == 0) {

					for (TestDefinitionDTO tstDfn : tblModel.getTstDto()) {

						if (tstDfn.isSelected()) {

							tstDfn.setStatus(false);
							db.update(tstDfn);
						}
					}

					tblModel.setTstDto(db.getAll(TestDefinitionDTO.class,
							new ArrayList(), rest));
					tblModel.fireTableDataChanged();

				}
			} else if (wrt.equals("EDIT")) {
				ArrayList<TestDefinitionDTO> testDefinition = new ArrayList<TestDefinitionDTO>();
				int count = 0;
				for (TestDefinitionDTO tstDefinition : tblModel.getTstDto()) {
					if (tstDefinition.isSelected()) {
						count++;
						tstDefinition = openEditor(tstDefinition);
						db.update(tstDefinition);
						tblModel.fireTableDataChanged();
					}
				}
				if (count == 0)
					JOptionPane.showMessageDialog(null,
							"Please select a record to edit");

			}

		}

		private TestDefinitionDTO openEditor(TestDefinitionDTO tstDto) {

			JDialog dialog = new JDialog(
					JOptionPane.getFrameForComponent(panel), "Create Patient",
					true);
			TestDefinitionAddEditor tstAddModel = new TestDefinitionAddEditor(
					tstDto);
			tstAddModel.setDialog(dialog);
			dialog.getContentPane().add(tstAddModel);
			dialog.setLocationByPlatform(true);
			dialog.setSize(800, 350);
			dialog.setVisible(true);
			tstDto = tstAddModel.getTstDto();
			// System.out.println("3e-"+patient.getPatientId());
			return tstDto;
		}

	}

	public static ArrayList<TestDefinitionDTO> byId() {
		String sql = "SELECT t1.*, t2.* FROM AQTEST t1 JOIN AQTESTCATEGORIES t2 ON t1.TESTCATEGORIESID=t2.TESTCATEGORIESID WHERE t1.status= :status";

		SQLQuery query = DBHibernate.getSession().createSQLQuery(sql);
		query.addEntity("t1", TestDefinitionDTO.class);
		query.addEntity("t2", TestCategoriesDTO.class);

		// query.setParameter("admissionId", admissionId);
		query.setParameter("status", true);

		ArrayList<Object[]> results = (ArrayList<Object[]>) query.list();
		ArrayList<TestDefinitionDTO> tstCt = new ArrayList();

		for (Object[] objects : results) {
			
			TestDefinitionDTO tstDTO = (TestDefinitionDTO) objects[0];
			TestCategoriesDTO tstCtDTO = (TestCategoriesDTO) objects[1];
			tstDTO.setTestCategoriesDTO(tstCtDTO);
			// admissionDTO.setTestCategoriesDTO(admissionDTO);
			// tstCtDTO.add(tstDTO);
			tstCt.add(tstDTO);
		}
		return tstCt;

	}
}
