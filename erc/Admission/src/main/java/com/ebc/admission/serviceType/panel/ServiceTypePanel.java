package com.ebc.admission.serviceType.panel;

import javax.swing.JPanel;

import com.ebc.admission.appointment.editor.AppointmentEditor;
import com.ebc.admission.appointment.entity.AppointmentDto;
import com.ebc.admission.appointment.panel.AppointmentPanel;
import com.ebc.admission.serviceType.entity.ServiceTypeDto;
import com.ebc.core.AqBaseEntity;
import com.ebc.core.DBHibernate;
import com.ebc.definitions.patient.model.Patient;

import java.awt.GridBagLayout;

import javax.swing.JButton;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.SimpleExpression;

public class ServiceTypePanel extends JPanel {
	private ServiceTypeTableModel serviceTypeTableModel = new ServiceTypeTableModel();
	private JButton btnAdd;
	private JButton btnEdit;
	private JButton btnDelete;
	private JTable table;
	private JPanel panel;

	public ServiceTypePanel() {
		GenerelEvent event = new GenerelEvent(this);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 0, 0, 0, 0, 0, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 0, 0, 0, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 1.0, 0.0, 0.0, 0.0,
				0.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 1.0, 0.0,
				Double.MIN_VALUE };
		setLayout(gridBagLayout);

		btnAdd = new JButton("Add");
		GridBagConstraints gbc_btnAdd = new GridBagConstraints();
		gbc_btnAdd.insets = new Insets(0, 0, 5, 5);
		gbc_btnAdd.gridx = 2;
		gbc_btnAdd.gridy = 1;
		add(btnAdd, gbc_btnAdd);
		btnAdd.setActionCommand("ADD");
		btnAdd.addActionListener(event);

		btnEdit = new JButton("Edit");
		GridBagConstraints gbc_btnEdit = new GridBagConstraints();
		gbc_btnEdit.insets = new Insets(0, 0, 5, 5);
		gbc_btnEdit.gridx = 3;
		gbc_btnEdit.gridy = 1;
		add(btnEdit, gbc_btnEdit);
		btnEdit.setActionCommand("EDIT");
		btnEdit.addActionListener(event);

		btnDelete = new JButton("Delete");
		GridBagConstraints gbc_btnDelete = new GridBagConstraints();
		gbc_btnDelete.insets = new Insets(0, 0, 5, 5);
		gbc_btnDelete.gridx = 4;
		gbc_btnDelete.gridy = 1;
		add(btnDelete, gbc_btnDelete);
		btnDelete.setActionCommand("DELETE");
		btnDelete.addActionListener(event);

		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridwidth = 4;
		gbc_scrollPane.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 1;
		gbc_scrollPane.gridy = 3;
		add(scrollPane, gbc_scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(serviceTypeTableModel);
		table.getTableHeader().setPreferredSize(new Dimension(50, 40));
	    table.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 18));
		table.setFont(new Font("Tahoma", Font.PLAIN, 20));
		table.setRowHeight(30);
		getAllServiceTypes();

	}

	private void getAllServiceTypes() {
		SimpleExpression rest = Restrictions.eq("status", true);
		ArrayList<AqBaseEntity> basenetities = DBHibernate.getAll(
				ServiceTypeDto.class,
				new ArrayList<String>(Arrays.asList("serviceTypeId")), rest);
		serviceTypeTableModel .setTypeDtos((ArrayList<ServiceTypeDto>) basenetities.clone());
	}

	class GenerelEvent implements ActionListener {
		private ServiceTypePanel panel;

		public GenerelEvent(ServiceTypePanel panel) {
			super();
			this.panel = panel;
		}

		public void actionPerformed(ActionEvent e) {

			if (e.getActionCommand().equals("ADD")) {
				try {
					DBHibernate.save(openAddServiceTypeEditor(null));
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				getAllServiceTypes();
				serviceTypeTableModel.fireTableDataChanged();
				JOptionPane.showMessageDialog(null,"İnserted Service type is ok");
			}
			if (e.getActionCommand().equals("EDIT")) {
				if (table.getSelectedRow() == -1) {
					JOptionPane.showMessageDialog(null,"Not Selected row");
				} else {
					int warning = JOptionPane.showConfirmDialog(null,
							"Are you sure to edit service ? ", "WARNİNG",
							JOptionPane.YES_NO_OPTION);
					if (warning == 0) {
						ServiceTypeDto dto = serviceTypeTableModel.getTypeDtos().get(table.getSelectedRow());
						dto = openAddServiceTypeEditor(dto);
					    System.out.println(dto);
						DBHibernate.update(dto);
						getAllServiceTypes();
						serviceTypeTableModel.fireTableDataChanged();
						JOptionPane.showMessageDialog(null,"Updated Service type is ok");
					}else{
						
					}
				}
			}
			if (e.getActionCommand().equals("DELETE")) {
				if (table.getSelectedRow() == -1) {
					System.out.println("Not Selected.");
					JOptionPane.showMessageDialog(null,"Not Selected row");
				} else {
					int warning = JOptionPane.showConfirmDialog(null,
							"Are you sure to delete service ? ", "WARNİNG",
							JOptionPane.YES_NO_OPTION);
					if (warning == 0) {
						ServiceTypeDto dto = serviceTypeTableModel.getTypeDtos().get(table.getSelectedRow());
						dto.setStatus(false);
						DBHibernate.update(dto);
						getAllServiceTypes();
						serviceTypeTableModel.fireTableDataChanged();
						JOptionPane.showMessageDialog(null,"Deleting service type is ok");
					}else{
						
					}
				}
			}
		}
	}

	private ServiceTypeDto openAddServiceTypeEditor(ServiceTypeDto dto) {
		JDialog dialog = new JDialog(JOptionPane.getFrameForComponent(panel),
				"Create Service Type", true);
		ServiceTypeEditor serviceTypeEditor = new ServiceTypeEditor(dto);
		serviceTypeEditor.setDialog(dialog);
		dialog.getContentPane().add(serviceTypeEditor);
		dialog.setLocationByPlatform(true);
		dialog.setSize(600, 200);
		dialog.setVisible(true);
		
		return serviceTypeEditor.getDto();
	}
}
