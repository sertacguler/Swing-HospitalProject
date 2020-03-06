package com.ebc.outpatient.doctor;

import javax.swing.JPanel;

import java.awt.GridBagLayout;

import javax.swing.JTabbedPane;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import com.ebc.definitions.admission.entity.AdmissionDTO;
import com.ebc.outpatient.anamnez.panel.AnamnezPanel;
import com.ebc.outpatient.protocol.ProtocolDTO;
import com.ebc.outpatient.services.OutpatientService;
import com.toedter.calendar.JDateChooser;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.util.Date;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class DoctorMainPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private DoctorAppointmentTableModel appmentTableModel = new DoctorAppointmentTableModel();
	private JTable table;
	private JDateChooser dateChooser;

	private JPanel panelPatientInformation = new JPanel();
	private JPanel panelAdmissionInformation = new JPanel();
	private PatientInformationPanel patientInformationPanel = new PatientInformationPanel();
	private AdmissionInformationPanel admInfoPanel = new AdmissionInformationPanel();
	private JPopupMenu popupmenu = new JPopupMenu();
	private ProtocolDTO protocolDTO;

	private static Long AdmissionIdForAnamnez;

	public DoctorMainPanel() {

		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 15, 336, 224, 195, 15, 15, 0 };
		gridBagLayout.rowHeights = new int[] { 23, 150, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 1.0, 1.0, 1.0, 0.0,
				0.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 1.0,
				Double.MIN_VALUE };
		setLayout(gridBagLayout);

		JPanel panelPatientList = new JPanel();
		GridBagConstraints gbc_panelPatientList = new GridBagConstraints();
		gbc_panelPatientList.gridheight = 2;
		gbc_panelPatientList.insets = new Insets(0, 0, 0, 5);
		gbc_panelPatientList.fill = GridBagConstraints.BOTH;
		gbc_panelPatientList.gridx = 1;
		gbc_panelPatientList.gridy = 1;
		add(panelPatientList, gbc_panelPatientList);
		GridBagLayout gbl_panelPatientList = new GridBagLayout();
		gbl_panelPatientList.columnWidths = new int[] { 143, 0, 0, 0 };
		gbl_panelPatientList.rowHeights = new int[] { 0, 0, 0 };
		gbl_panelPatientList.columnWeights = new double[] { 0.0, 0.0, 1.0,
				Double.MIN_VALUE };
		gbl_panelPatientList.rowWeights = new double[] { 0.0, 1.0,
				Double.MIN_VALUE };
		panelPatientList.setLayout(gbl_panelPatientList);

		dateChooser = new JDateChooser();
		GridBagConstraints gbc_dateChooser = new GridBagConstraints();
		gbc_dateChooser.fill = GridBagConstraints.HORIZONTAL;
		gbc_dateChooser.insets = new Insets(0, 0, 5, 5);
		gbc_dateChooser.gridx = 0;
		gbc_dateChooser.gridy = 0;
		panelPatientList.add(dateChooser, gbc_dateChooser);

		dateChooser.setDate(new Date());
		dateChooser.setDateFormatString("dd-MM-yyyy");
		dateChooser.setFont(new Font("Tahoma", Font.BOLD, 17));
		fullInTable(dateChooser.getDate());

		dateChooser.getDateEditor().addPropertyChangeListener(
				new PropertyChangeListener() {

					@Override
					public void propertyChange(PropertyChangeEvent evt) {

						fullInTable(dateChooser.getDate());

					}
				});

		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridwidth = 3;
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 1;
		panelPatientList.add(scrollPane, gbc_scrollPane);

		GridBagConstraints gbc_panelPatientInformation = new GridBagConstraints();
		gbc_panelPatientInformation.insets = new Insets(0, 0, 5, 5);
		gbc_panelPatientInformation.fill = GridBagConstraints.BOTH;
		gbc_panelPatientInformation.gridx = 2;
		gbc_panelPatientInformation.gridy = 1;
		add(panelPatientInformation, gbc_panelPatientInformation);
		panelPatientInformation.setLayout(new BorderLayout(0, 0));

		panelPatientInformation.add(patientInformationPanel,
				BorderLayout.CENTER);

		GridBagConstraints gbc_panelAdmissionInformation = new GridBagConstraints();
		gbc_panelAdmissionInformation.insets = new Insets(0, 0, 5, 5);
		gbc_panelAdmissionInformation.fill = GridBagConstraints.BOTH;
		gbc_panelAdmissionInformation.gridx = 3;
		gbc_panelAdmissionInformation.gridy = 1;
		add(panelAdmissionInformation, gbc_panelAdmissionInformation);
		panelAdmissionInformation.setLayout(new BorderLayout(0, 0));

		panelAdmissionInformation.add(admInfoPanel, BorderLayout.CENTER);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		GridBagConstraints gbc_tabbedPane = new GridBagConstraints();
		gbc_tabbedPane.insets = new Insets(0, 0, 0, 5);
		gbc_tabbedPane.gridwidth = 2;
		gbc_tabbedPane.fill = GridBagConstraints.BOTH;
		gbc_tabbedPane.gridx = 2;
		gbc_tabbedPane.gridy = 2;
		add(tabbedPane, gbc_tabbedPane);

		JPanel anemnezTabPanel = new AnamnezPanel();
		tabbedPane.addTab("mahmut", anemnezTabPanel);

		table = new JTable();
		table.setModel(appmentTableModel);
		table.setRowHeight(40);
		table.getColumnModel().getColumn(0).setPreferredWidth(1);
		table.getColumnModel().getColumn(1).setPreferredWidth(150);
		table.setPreferredScrollableViewportSize(table.getPreferredSize());
		scrollPane.setViewportView(table);

		table.addMouseListener(new MouseAdapter() {

			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					table.setComponentPopupMenu(popupmenu);
					showMenu(e);
				}
			}
		});

		table.getSelectionModel().addListSelectionListener(
				new ListSelectionListener() {

					@Override
					public void valueChanged(ListSelectionEvent e) {

						int selectedRow = table.getSelectedRow();

						if (selectedRow != -1) {

							OutpatientInfoDTO outpatientInfoDTO = appmentTableModel
									.getOutpatientInfoDTOs().get(selectedRow);

							// if(outpatientInfoDTO.isProtocolCreated() ==
							// false){
							// popupmenuInit();
							// }

							patientInformationPanel
									.setOutpatientInfoDTO(outpatientInfoDTO);
							patientInformationPanel.initialize();

							admInfoPanel
									.setOutpatientInfoDTO(outpatientInfoDTO);
							admInfoPanel.initialize();

							setAdmissionIdForAnamnez((long) outpatientInfoDTO
									.getAdmissionDTO().getAdmissionid());

						}

					}

				});

	}

	private void fullInTable(Date date) {
		appmentTableModel.setOutpatientInfoDTOs(OutpatientService
				.getPatientInfAndAmissionInf(date));
		appmentTableModel.fireTableDataChanged();
	}

	private void popupmenuInit() {

		JMenuItem protocol = new JMenuItem("Create Protocol");

		protocol.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				createProtocol();
			}
		});

		popupmenu.add(protocol);
	}

	private void createProtocol() {

	}

	private void showMenu(MouseEvent e) {

		if (table.getSelectedRow() == -1) {
		} else {
			popupmenu.show(e.getComponent(), e.getX(), e.getY());
		}
	}

	public Long getAdmissionIdForAnamnez() {
		return AdmissionIdForAnamnez;
	}

	public void setAdmissionIdForAnamnez(Long admissionIdForAnamnez) {
		AdmissionIdForAnamnez = admissionIdForAnamnez;
	}

}
