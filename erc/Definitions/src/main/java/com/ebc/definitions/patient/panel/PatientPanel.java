package com.ebc.definitions.patient.panel;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableRowSorter;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.SimpleExpression;

import com.ebc.core.AqBaseEntity;
import com.ebc.core.DBHibernate;
import com.ebc.definitions.patient.editor.MyComboboxEditor;
import com.ebc.definitions.patient.editor.PatientAddEditor;
import com.ebc.definitions.patient.model.Patient;

public class PatientPanel extends JPanel{
	private static final long serialVersionUID = 1L;
	private final JButton btnAdd = new JButton("Add");
	private final JButton btnDelete = new JButton("Delete");
	private final JButton btnEdit = new JButton("Edit");
	private final JTable table = new JTable();
	private final PatientTableModel tableModel = new PatientTableModel();
	private final JScrollPane scrollPane = new JScrollPane();
	private final JButton btnExit = new JButton("Exit");
	private JFrame frame;
	int selectedRowNumber = -1;
	private ArrayList<Patient> patients = new ArrayList<Patient>();
	private final JButton btnUpdate = new JButton("Update");
	private final JTextField txtSearch = new JTextField();
	public PatientPanel() {
		txtSearch.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				String searchKey = txtSearch.getText();
				TableRowSorter<AbstractTableModel> tableRowSorter = new TableRowSorter<AbstractTableModel>(
						tableModel);
				table.setRowSorter(tableRowSorter);
				tableRowSorter.setRowFilter(RowFilter.regexFilter(searchKey,2,3)
						);
				
			}
		});
		txtSearch.setFont(new Font("Tahoma", Font.PLAIN, 17));

		txtSearch.setColumns(10);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 10, 69, 18, 82, 58, 0, 0, 0,
				0, 0, 10, 0 };
		gridBagLayout.rowHeights = new int[] { 10, 0, 0, 10, 20, 10, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 1.0, 0.0, 0.0, 0.0,
				0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 1.0, 0.0,
				Double.MIN_VALUE };
		setLayout(gridBagLayout);

		GridBagConstraints gbc_btnImport = new GridBagConstraints();
		gbc_btnImport.insets = new Insets(0, 0, 5, 5);
		gbc_btnImport.gridx = 4;
		gbc_btnImport.gridy = 1;

		GridBagConstraints gbc_btnExport = new GridBagConstraints();
		gbc_btnExport.insets = new Insets(0, 0, 5, 5);
		gbc_btnExport.gridx = 5;
		gbc_btnExport.gridy = 1;
		
		GridBagConstraints gbc_btnUpdate = new GridBagConstraints();
		gbc_btnUpdate.insets = new Insets(0, 0, 5, 5);
		gbc_btnUpdate.gridx = 5;
		gbc_btnUpdate.gridy = 1;
		btnUpdate.setFont(new Font("Tahoma", Font.PLAIN, 15));
		add(btnUpdate, gbc_btnUpdate);
		btnUpdate.setActionCommand("UPDATE");

		GridBagConstraints gbc_btnAdd = new GridBagConstraints();
		gbc_btnAdd.insets = new Insets(0, 0, 5, 5);
		gbc_btnAdd.gridx = 6;
		gbc_btnAdd.gridy = 1;
		btnAdd.setFont(new Font("Tahoma", Font.PLAIN, 15));
		add(btnAdd, gbc_btnAdd);

		GridBagConstraints gbc_btnDelete = new GridBagConstraints();
		gbc_btnDelete.insets = new Insets(0, 0, 5, 5);
		gbc_btnDelete.gridx = 7;
		gbc_btnDelete.gridy = 1;
		btnDelete.setFont(new Font("Tahoma", Font.PLAIN, 15));
		add(btnDelete, gbc_btnDelete);

		GridBagConstraints gbc_btnEdit = new GridBagConstraints();
		gbc_btnEdit.insets = new Insets(0, 0, 5, 5);
		gbc_btnEdit.gridx = 8;
		gbc_btnEdit.gridy = 1;
		btnEdit.setFont(new Font("Tahoma", Font.PLAIN, 15));
		add(btnEdit, gbc_btnEdit);

		GridBagConstraints gbc_btnExit = new GridBagConstraints();
		gbc_btnExit.insets = new Insets(0, 0, 5, 5);
		gbc_btnExit.gridx = 9;
		gbc_btnExit.gridy = 1;
		btnExit.setFont(new Font("Tahoma", Font.PLAIN, 15));
		add(btnExit, gbc_btnExit);

		GridBagConstraints gbc_txtSearch = new GridBagConstraints();
		gbc_txtSearch.gridwidth = 3;
		gbc_txtSearch.insets = new Insets(0, 0, 5, 5);
		gbc_txtSearch.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtSearch.gridx = 7;
		gbc_txtSearch.gridy = 2;
		add(txtSearch, gbc_txtSearch);

		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridwidth = 9;
		gbc_scrollPane.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 1;
		gbc_scrollPane.gridy = 4;
		add(scrollPane, gbc_scrollPane);
		scrollPane.setViewportView(table);
		table.setModel(tableModel);
		table.getTableHeader().setPreferredSize(new Dimension(50, 40));
	    table.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 18));
		table.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		
		GenerelEvent event = new GenerelEvent(this);
		btnAdd.setActionCommand("ADD");
		btnEdit.setActionCommand("EDIT");
		btnDelete.setActionCommand("DELETE");
		btnExit.setActionCommand("EXIT");
		btnAdd.addActionListener(event);
		btnEdit.addActionListener(event);
		btnDelete.addActionListener(event);
		btnExit.addActionListener(event);
		btnUpdate.addActionListener(event);

		fullInTable();
		formingTable();

	}

	void fullInTable() {
		SimpleExpression rest = Restrictions.eq("status",1);
		ArrayList<AqBaseEntity> basenetities = DBHibernate.getAll(
				Patient.class, 
				new ArrayList<String> (Arrays.asList("patientid","name")),
				rest
				);

		tableModel.setPatients((ArrayList<Patient>) basenetities.clone());
		tableModel.fireTableDataChanged();
	}

	// formating table (colour, resizing)
	void formingTable() {
		// insert image
		table.setRowHeight(70);
		table.getColumnModel().getColumn(0).setPreferredWidth(0);
		table.getColumnModel().getColumn(1).setPreferredWidth(1);
		table.getColumnModel().getColumn(6).setPreferredWidth(1);
		table.setPreferredScrollableViewportSize(table.getPreferredSize());

		// to color and form checkbox
		table.setDefaultRenderer(table.getColumnClass(0), new BooleanRenderer());

		// color row
		table.getColumnModel().getColumn(4).setCellEditor(new MyComboboxEditor());
		for (int i = 1; i < tableModel.getColumnCount(); i++) {
			table.setDefaultRenderer(table.getColumnClass(i),
					new WineCellRenderer());
		}
	}
	public <T> ArrayList<AqBaseEntity> getAll(Class<T> class1){
		ArrayList<AqBaseEntity> personelList = null;
		try {
			Criteria main = DBHibernate.getSession().createCriteria(class1);
			main.addOrder(Order.asc("patientid"));
			return  (ArrayList<AqBaseEntity>) main.list();
		} catch (Exception e) {
			System.out.println("Problem getAll patient : " + e);

			return new ArrayList<>();
		}
	}
	
	// to form checkbox's shape
	public static class BooleanRenderer extends JCheckBox implements
			TableCellRenderer {
		public BooleanRenderer() {
			super();
			setHorizontalAlignment(JLabel.CENTER);
			setBorderPainted(true);
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
	

	
	class GenerelEvent implements ActionListener {
		private JPanel panel;
		public GenerelEvent(JPanel panel) {
			super();
			this.panel = panel;
		}
		public void actionPerformed(ActionEvent e) {
			Patient patient = null;
			if (e.getActionCommand().equals("EXIT")) {
				System.out.println("Exiittt..");
				System.exit(0);
			}
			if (e.getActionCommand().equals("ADD")) {
				patient = openEditor(patient);
				try {
					patient.setCreateAt(new Date());
					patient.setPatientno(assignPatientNo());
					DBHibernate.save(patient);
				} catch (SQLException e1) {
					System.out.println("problem save patient : "+e);
				}
				
				if (patient != null) {
					tableModel.getPatients().add(patient);
					tableModel.fireTableDataChanged();
				}
			}
			if (e.getActionCommand().equals("EDIT")) {
				if (table.getSelectedRow() == -1) {
					System.out.println("Not Selected....");
				} else {
					patient = tableModel.getPatients().get(table.getSelectedRow());
					patient = openEditor(patient);
					DBHibernate.update(patient);
					tableModel.getPatients().set(table.getSelectedRow(), patient);
					tableModel.fireTableDataChanged();
				}
			}
			if (e.getActionCommand().equals("UPDATE")) {
				for (Patient p: tableModel.getPatients()) {
					if(p.isDirty()) DBHibernate.update(p);
				}
				JOptionPane.showMessageDialog(null,"updating dirty patient is ok");
				fullInTable();
				formingTable();
			}
			if (e.getActionCommand().equals("DELETE")) {
				int warning = JOptionPane.showConfirmDialog(null,
						"Silemk �stedi�inizden Emin misiniz ? ", "UYARI",
						JOptionPane.YES_NO_OPTION);
				if (warning == 0) {
					for (Patient p : tableModel.getPatients()) {
						if(p.isSelected()){
							p.setStatus(0);
							DBHibernate.update(p);
						}
					}
					fullInTable();
					JOptionPane.showMessageDialog(null, "Deleting Patients is Ok");
				} else {
					JOptionPane.showMessageDialog(null, "Deleting is given up");
					System.out.println("Delete is given up");
				}
			}
		}

		private String assignPatientNo() {

			SimpleExpression rest = Restrictions.eq("status",1);
			Patient p = (Patient) DBHibernate.findLastOne(Patient.class,new ArrayList<String> (Arrays.asList("patientid")),rest);
			String patientno = p.getPatientno();
			int year = new Date().getYear()+1900;
			String[] parts = patientno.split("-");
			if(parts[0].equals(String.valueOf(year))){
				parts[1] = String.valueOf(1+Integer.parseInt(parts[1]));
			}else{
				parts[0]=String.valueOf(new Date().getYear()+1900);
				parts[1]="1";
			}
			System.out.println(parts[0]+" "+parts[1]);
			return parts[0]+"-"+parts[1];
		}
		private Patient openEditor(Patient patient) {
			JDialog dialog = new JDialog( JOptionPane.getFrameForComponent(panel), "Create patient", true);
			PatientAddEditor editor = new PatientAddEditor(patient);
			editor.setDialog(dialog);
			dialog.getContentPane().add(editor);
			dialog.setLocationByPlatform(true);
			dialog.setSize(600, 300);
			dialog.setVisible(true);
			patient = editor.getPatient();
			return patient;
		}
	}
	public static void main(String[] args) {
		 JFrame frame = new JFrame();
		 PatientPanel panel = new PatientPanel();
		 frame.getContentPane().add(panel);
		 frame.setSize(1000,700);
		 frame.setLocationRelativeTo(null);
		 frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 frame.setVisible(true);
	}
}