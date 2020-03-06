package com.ebc.definitions.organization.panel;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JScrollPane;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JTable;

import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.SimpleExpression;

import com.ebc.core.DBHibernate;
import com.ebc.definitions.organization.entity.OrganizationDTO;

public class OrganizationPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JButton btnAdd = new JButton("ADD");
	private JButton btnEdit = new JButton("EDIT");
	private final JScrollPane scrollPane = new JScrollPane();
	private final JTable table = new JTable();
	private OrganizationTableModel orgTableModel = new OrganizationTableModel();

	private ArrayList<OrganizationDTO> orgDtoList = new ArrayList<OrganizationDTO>();

	public OrganizationPanel() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 15, 0, 0, 0, 15, 15, 0 };
		gridBagLayout.rowHeights = new int[] { 15, 0, 0, 0, 0, 15, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 1.0, 0.0, 0.0, 0.0,
				0.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 1.0, 0.0, 0.0,
				Double.MIN_VALUE };
		setLayout(gridBagLayout);

		GridBagConstraints gbc_btnAdd = new GridBagConstraints();
		gbc_btnAdd.insets = new Insets(0, 0, 5, 5);
		gbc_btnAdd.gridx = 2;
		gbc_btnAdd.gridy = 1;
		add(btnAdd, gbc_btnAdd);

		GridBagConstraints gbc_btnEdit = new GridBagConstraints();
		gbc_btnEdit.insets = new Insets(0, 0, 5, 5);
		gbc_btnEdit.gridx = 3;
		gbc_btnEdit.gridy = 1;
		add(btnEdit, gbc_btnEdit);

		GenerealEventListener eventListener = new GenerealEventListener(this);
		btnAdd.setActionCommand("ADD");
		btnEdit.setActionCommand("EDIT");

		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridheight = 2;
		gbc_scrollPane.gridwidth = 4;
		gbc_scrollPane.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 1;
		gbc_scrollPane.gridy = 3;
		add(scrollPane, gbc_scrollPane);

		scrollPane.setViewportView(table);

		table.setModel(orgTableModel);
		table.setPreferredScrollableViewportSize(table.getPreferredSize());
		table.setRowHeight(45);
		SimpleExpression rest = Restrictions.eq("status", true);
		orgTableModel.setOrgDto(DBHibernate.getAll(OrganizationDTO.class,
				new ArrayList(), rest));
		orgTableModel.fireTableDataChanged();

		btnAdd.addActionListener(eventListener);
		btnEdit.addActionListener(eventListener);

	}

	class GenerealEventListener implements ActionListener {
		private JPanel panel;

		public GenerealEventListener(JPanel panel) {
			this.panel = panel;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			String cmd = e.getActionCommand();
			OrganizationDTO orgDto = null;

			int i = 0;

			if (cmd.equals("ADD")) {
				if (i == 0) {
					orgDto = openEditor(orgDto);
					if (orgDto != null) {
						try {
							DBHibernate.save(orgDto);
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
						orgTableModel.getOrgDto().add(orgDto);
						orgTableModel.fireTableDataChanged();
					}
				}
			} else if (cmd.equals("EDIT")) {
				i = table.getSelectedRow();
				if (i > 0) {
					orgDto = openEditor(orgTableModel.getOrgDto().get(i));
					DBHibernate.update(orgDto);
					if (orgDto.getStatus() == true)
						orgTableModel.fireTableDataChanged();
					else
						// orgTableModel.getOrgDto().remove();

						orgTableModel.fireTableDataChanged();
				} else {
					JOptionPane.showMessageDialog(null,
							"You didn`t select record!", "Warning",
							JOptionPane.WARNING_MESSAGE);
				}
			}
		}

		private OrganizationDTO openEditor(OrganizationDTO orgDto) {
			JDialog dialog = new JDialog(
					JOptionPane.getFrameForComponent(panel),
					"Create Organization", true);
			OrganizationEditor orgEditor = new OrganizationEditor(orgDto);
			orgEditor.setDialog(dialog);
			dialog.getContentPane().add(orgEditor);
			dialog.setLocationByPlatform(true);
			dialog.setSize(600, 250);
			dialog.setVisible(true);
			orgDto = orgEditor.getOrgDto();

			return orgDto;
		}
	}

}
