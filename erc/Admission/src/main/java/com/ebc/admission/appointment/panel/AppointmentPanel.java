package com.ebc.admission.appointment.panel;

import javax.swing.JPanel;

import java.awt.GridBagLayout;

import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JButton;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import javax.swing.JTable;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;

import org.hibernate.SQLQuery;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.SimpleExpression;

import com.ebc.admission.appointment.editor.AdmissionEditorForPatient;
import com.ebc.admission.appointment.editor.AppointmentEditor;
import com.ebc.admission.appointment.entity.AppointmentDto;
import com.ebc.admission.appointment.service.AppointmentService;
import com.ebc.core.AqBaseEntity;
import com.ebc.core.DBHibernate;
import com.ebc.definitions.organization.entity.OrganizationDTO;
import com.ebc.definitions.patient.editor.MyComboboxEditor;
import com.ebc.definitions.patient.model.Patient;
import com.ebc.definitions.staff.entity.Staff;
import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDateChooser;

public class AppointmentPanel extends JPanel {
	private JTable table;
	private ArrayList<AppointmentDto> appointmentDtos = new ArrayList<AppointmentDto>();
	private AppointmentTableModel appointmentTableModel = new AppointmentTableModel();
	private JButton btnDelete;
	private JButton btnAdd;
	private static JPanel panel;
	private JDateChooser dateChooser;
	private SimpleDateFormat dateFormat;
	private JButton btnSearch;
	private JMenuItem menuItemAdmission;
	private JMenuItem menuItemCancel;
	private int admissionid;
	private AdmissionEditorForPatient admissionEditorForAppointment;

	public AppointmentPanel() {
		GenerelEvent event = new GenerelEvent(this);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 10, 228, 106, -109, 0, 0, 0,
				0, 10, 0 };
		gridBagLayout.rowHeights = new int[] { 10, 0, 10, 375, 10, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 0.0, 0.0, 1.0, 0.0,
				0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 1.0, 0.0,
				Double.MIN_VALUE };
		setLayout(gridBagLayout);

		dateChooser = new JDateChooser();
		GridBagConstraints gbc_dateChooser = new GridBagConstraints();
		gbc_dateChooser.insets = new Insets(0, 0, 5, 5);
		gbc_dateChooser.fill = GridBagConstraints.BOTH;
		gbc_dateChooser.gridx = 1;
		gbc_dateChooser.gridy = 1;
		dateChooser.setDate(new Date());
		dateChooser.setDateFormatString("dd-MM-yyyy");
		dateChooser.setFont(new Font("Tahoma", Font.BOLD, 17));

		add(dateChooser, gbc_dateChooser);

		btnSearch = new JButton("SEARCH");
		btnSearch.setFont(new Font("Tahoma", Font.BOLD, 15));
		GridBagConstraints gbc_btnSearch = new GridBagConstraints();
		gbc_btnSearch.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnSearch.insets = new Insets(0, 0, 5, 5);
		gbc_btnSearch.gridx = 2;
		gbc_btnSearch.gridy = 1;
		add(btnSearch, gbc_btnSearch);
		btnSearch.setActionCommand("SEARCH");
		btnSearch.addActionListener(event);

		btnAdd = new JButton("ADD");
		btnAdd.setFont(new Font("Tahoma", Font.BOLD, 15));
		GridBagConstraints gbc_btnAdd = new GridBagConstraints();
		gbc_btnAdd.insets = new Insets(0, 0, 5, 5);
		gbc_btnAdd.gridx = 5;
		gbc_btnAdd.gridy = 1;
		add(btnAdd, gbc_btnAdd);
		btnAdd.setActionCommand("ADD");
		btnAdd.addActionListener(event);

		JButton btnDelete_1 = new JButton("DELETE");
		btnDelete_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		GridBagConstraints gbc_btnDelete_1 = new GridBagConstraints();
		gbc_btnDelete_1.insets = new Insets(0, 0, 5, 5);
		gbc_btnDelete_1.gridx = 6;
		gbc_btnDelete_1.gridy = 1;
		add(btnDelete_1, gbc_btnDelete_1);

		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridwidth = 7;
		gbc_scrollPane.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 1;
		gbc_scrollPane.gridy = 3;
		add(scrollPane, gbc_scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(appointmentTableModel);
		fillInTable(dateChooser.getDate());

		JPopupMenu popupMenu = new JPopupMenu();
		menuItemAdmission = new JMenuItem("Admission");
		menuItemCancel = new JMenuItem("Cancel Appoint");

		menuItemAdmission.setActionCommand("OPENADMISSION");
		menuItemAdmission.addActionListener(event);
		menuItemCancel.setActionCommand("CANCELAPPOINTMENT");
		menuItemCancel.addActionListener(event);

		popupMenu.add(menuItemAdmission);
		popupMenu.add(menuItemCancel);
		table.setComponentPopupMenu(popupMenu);
	}

	private void fillInTable(Date date) {
		appointmentTableModel.setAppointmentDtos(AppointmentService.getAllAppointment(date));
		appointmentTableModel.fireTableDataChanged();
		formingTable();
	}

	// formating table (colour, resizing)
	void formingTable() {
		// insert image
		table.setRowHeight(40);
		table.getColumnModel().getColumn(0).setPreferredWidth(1);
		table.getColumnModel().getColumn(1).setPreferredWidth(150);
		table.setPreferredScrollableViewportSize(table.getPreferredSize());

		// to color and form checkbox
		table.setDefaultRenderer(table.getColumnClass(0), new BooleanRenderer());

		for (int i = 1; i < appointmentTableModel.getColumnCount(); i++) {
			table.setDefaultRenderer(table.getColumnClass(i),
					new WineCellRenderer());
		}
		table.getTableHeader().setPreferredSize(new Dimension(50, 40));
		table.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 18));
		table.setFont(new Font("Serif", Font.BOLD, 20));
		appointmentTableModel.fireTableDataChanged();
	}

	class GenerelEvent implements ActionListener {
		private AppointmentPanel appointmentPanel;
		public GenerelEvent(AppointmentPanel appointmentPanel) {
			super();
			this.appointmentPanel = appointmentPanel;
		}

		public void actionPerformed(ActionEvent e) {

			if (e.getActionCommand().equals("OPENADMISSION")) {
				int warning = JOptionPane.showConfirmDialog(null, "Open Admissions ", "UYARI", JOptionPane.YES_NO_OPTION);
				if (warning == 0) {
					AppointmentDto appointmentDto = appointmentTableModel
							.getAppointmentDtos().get(table.getSelectedRow());
					if(appointmentDto.getAdmissionid()<0){
						int admissionid= openAdmissionDetailsForPatient(appointmentDto.getPatientid());
						appointmentPanel.admissionid = admissionid;
						if(admissionid > 0 ){
							appointmentDto.setAdmissionid(appointmentPanel.admissionid);
							DBHibernate.update(appointmentDto);
							JOptionPane.showMessageDialog(null,"Updating admissionid for appointment is ok");
							System.out.println("update admissionid for appointment");
						}
					}else{
						JOptionPane.showMessageDialog(null,
										"Can't open Admissions for "+
										appointmentDto.getPatient().getName()+" "+
										appointmentDto.getPatient().getLastname());
					}
				} else {
					System.out.println("cancelled menuItemAdmission");
				}
			}
			if (e.getActionCommand().equals("CANCELAPPOINTMENT")) {
				System.out.println("CANCELED APPOINTMENT");
			}
			if (e.getActionCommand().equals("SEARCH")) {
				fillInTable(dateChooser.getDate());
			}
			if (e.getActionCommand().equals("ADD")) {
				openAddAppointmentEditor();
			}
			fillInTable(dateChooser.getDate());
		}
	}

	// to form checkbox's shape
	public class BooleanRenderer extends JCheckBox implements TableCellRenderer {
		public BooleanRenderer() {
			super();
			setHorizontalAlignment(JLabel.CENTER);
			setBorderPainted(true);
			setSize(100, 100);
			setOpaque(true);
		}

		@Override
		public Component getTableCellRendererComponent(JTable table,
				Object value, boolean isSelected, boolean hasFocus, int row,
				int column) {
			setBackground(new Color(245, 225, 245));
			setSelected((value != null && ((Boolean) value).booleanValue()));
			return this;
		}
	}

	// control Appointment Operation whether there is same operation on same day
	public Boolean ControlAppointmentOperation(int patientid, Date date,
			int organizationid) {
		SimpleExpression rest = Restrictions.eq("patientid", patientid);
		ArrayList<AqBaseEntity> basenetities = DBHibernate.getAll(
				AppointmentDto.class,
				new ArrayList<String>(Arrays.asList("appointmentid")), rest);
		appointmentDtos = (ArrayList<AppointmentDto>) basenetities.clone();
		for (AppointmentDto appointmentDto : appointmentDtos) {

			if (date.compareTo(appointmentDto.getCreatedAt()) == 0
					&& appointmentDto.getOrganizationid() == organizationid) {
				return false;
			}
		}
		return true;
	}

	private void openAddAppointmentEditor() {
		JDialog dialog = new JDialog(JOptionPane.getFrameForComponent(panel),
				"Create Appointment", true);
		AppointmentEditor appointmentEditor = new AppointmentEditor();
		appointmentEditor.setDialog(dialog);
		dialog.getContentPane().add(appointmentEditor);
		dialog.setLocationByPlatform(true);
		dialog.setSize(600, 300);
		dialog.setVisible(true);
	}

	private int openAdmissionDetailsForPatient(int patientid) {
		JDialog dialog = new JDialog(JOptionPane.getFrameForComponent(panel),
				"Patient Admissions", true);
		admissionEditorForAppointment = new AdmissionEditorForPatient(
				patientid);
		admissionEditorForAppointment.setDialog(dialog);
		dialog.getContentPane().add(admissionEditorForAppointment);
		dialog.setLocationByPlatform(true);
		dialog.setSize(1200, 500);
		dialog.setVisible(true);
		return admissionEditorForAppointment.getAdmissionid();
	}
}
