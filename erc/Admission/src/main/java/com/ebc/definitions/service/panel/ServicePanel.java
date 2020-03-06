package com.ebc.definitions.service.panel;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JTable;

import org.hibernate.SQLQuery;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.SimpleExpression;

import com.ebc.admission.serviceType.entity.ServiceTypeDto;
import com.ebc.core.DBHibernate;
import com.ebc.definitions.components.PatientSearchPanel;
import com.ebc.definitions.organization.entity.OrganizationDTO;
import com.ebc.definitions.patient.model.Patient;
import com.ebc.definitions.service.entity.ServiceDTO;
import com.ebc.definitions.staff.entity.Staff;

public class ServicePanel extends JPanel {
	private JTable table;
	private ServiceTableModel serviceTableModel = new ServiceTableModel();

	public ServicePanel() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 15, 0, 0, 0, 0, 0, 0, 0, 15, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 0, 0, 15, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 0.0, 0.0, 1.0, 0.0,
				0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 1.0, 0.0,
				Double.MIN_VALUE };
		setLayout(gridBagLayout);

		JButton btnAdd = new JButton("ADD");
		GridBagConstraints gbc_btnAdd = new GridBagConstraints();
		gbc_btnAdd.insets = new Insets(0, 0, 5, 5);
		gbc_btnAdd.gridx = 4;
		gbc_btnAdd.gridy = 1;
		add(btnAdd, gbc_btnAdd);

		JButton btnEdit = new JButton("EDIT");
		GridBagConstraints gbc_btnEdit = new GridBagConstraints();
		gbc_btnEdit.insets = new Insets(0, 0, 5, 5);
		gbc_btnEdit.gridx = 5;
		gbc_btnEdit.gridy = 1;
		add(btnEdit, gbc_btnEdit);

		JButton btnDelete = new JButton("DELETE");
		GridBagConstraints gbc_btnDelete = new GridBagConstraints();
		gbc_btnDelete.insets = new Insets(0, 0, 5, 5);
		gbc_btnDelete.gridx = 6;
		gbc_btnDelete.gridy = 1;
		add(btnDelete, gbc_btnDelete);

		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane.gridwidth = 7;
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 1;
		gbc_scrollPane.gridy = 2;
		add(scrollPane, gbc_scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);

		table.setModel(serviceTableModel);
		serviceTableModel.setServiceList(getAllService());

		btnAdd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ServiceDTO serviceDTO = openEditor();
				try {
					DBHibernate.save(serviceDTO);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		btnEdit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ServiceDTO serviceDTO = serviceTableModel.getServiceList().get(
						table.getSelectedRow());
				//serviceDTO = openEditor(serviceDTO);
				try {
					DBHibernate.save(serviceDTO);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});

		btnDelete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

			}
		});

	}

	public ArrayList<ServiceDTO> getAllService() {
		SimpleExpression rest = Restrictions.eq("status", true);
		return DBHibernate.getAll(ServiceDTO.class, new ArrayList(), rest);
	}

	public ArrayList<ServiceDTO> getAnamnezsForAdmissionId(Long serviceId) {
		String sql = "SELECT t1.*, t2.*FROM AQSERVICE t1 JOIN AQSERVICETYPE t2 ON t1.SERVICETYPEID=t2.SERVICETYPEID WHERE t1.SERVICEID = :serviceId AND t1.status = 1;";

		SQLQuery query = DBHibernate.getSession().createSQLQuery(sql);
		query.addEntity("t1", ServiceDTO.class);
		query.addEntity("t2", ServiceTypeDto.class);
		query.setParameter("serviceId", serviceId);

		ArrayList<Object[]> results = (ArrayList<Object[]>) query.list();
		ArrayList<ServiceDTO> serviceList = new ArrayList();

		for (Object[] objects : results) {
			ServiceDTO serviceDTO = (ServiceDTO) objects[0];
			ServiceTypeDto serviceTypeDto = (ServiceTypeDto) objects[1];
			serviceDTO.setServiceTypeDto(serviceTypeDto);
			serviceList.add(serviceDTO);
		}
		return serviceList;
	}

	private ServiceDTO openEditor() {
		JDialog dialog = new JDialog(JOptionPane.getFrameForComponent(this),
				"Find Patient", true);
		ServiceEditor serviceEditor = new ServiceEditor();
		serviceEditor.setDialog(dialog);
		dialog.getContentPane().add(serviceEditor);
		dialog.setLocationByPlatform(true);
		dialog.setSize(400, 200);
		dialog.setVisible(true);

		return serviceEditor.getServiceDTO();
	}
}
