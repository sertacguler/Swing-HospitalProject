package com.ebc.definitions.staff.panel;

import javax.swing.JPanel;

import java.awt.GridBagLayout;

import javax.swing.JButton;

import com.ebc.core.DBHibernate;
import com.ebc.definitions.staff.entity.Staff;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JDialog;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.SimpleExpression;

public class StaffMainPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	JButton btnAdd = new JButton("ADD");
	JButton btnEdit = new JButton("EDIT");
	JButton btnDelete = new JButton("DELETE");
	private final JScrollPane scrollPane = new JScrollPane();
	private final JTable table = new JTable();
	private StaffTableModel tablemodel = new StaffTableModel();
	private JPopupMenu popupmenu = new JPopupMenu();

	public StaffMainPanel() {

		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 15, 0, 0, 0, 0, 15, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 1.0, 0.0, 0.0, 0.0,
				0.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 1.0, 0.0,
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

		GridBagConstraints gbc_btnDelete = new GridBagConstraints();
		gbc_btnDelete.insets = new Insets(0, 0, 5, 5);
		gbc_btnDelete.gridx = 4;
		gbc_btnDelete.gridy = 1;
		add(btnDelete, gbc_btnDelete);

		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridheight = 2;
		gbc_scrollPane.gridwidth = 4;
		gbc_scrollPane.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 1;
		gbc_scrollPane.gridy = 3;
		add(scrollPane, gbc_scrollPane);

		GListener eventListener = new GListener(this);
		btnAdd.setActionCommand("ADD");
		btnEdit.setActionCommand("EDIT");
		btnDelete.setActionCommand("DELETE");

		btnAdd.addActionListener(eventListener);
		btnEdit.addActionListener(eventListener);
		btnDelete.addActionListener(eventListener);

		table.setRowHeight(85);

		getDatas();

		popupmenuInit();

		table.setModel(tablemodel);

		tablesetColor();

		scrollPane.setViewportView(table);

		table.addMouseListener(new MouseAdapter() {

			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					table.setComponentPopupMenu(popupmenu);
					showMenu(e);
				}
			}
		});

		table.setPreferredScrollableViewportSize(table.getPreferredSize());

		tablemodel.fireTableDataChanged();

	}

	private void popupmenuInit() {

		JMenuItem editpassword = new JMenuItem("Edit Password");
		JMenuItem disablestaff = new JMenuItem("Disable Staff");
		JMenuItem enablestaff = new JMenuItem("Enable Staff");

		editpassword.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				passEditor();
			}
		});

		disablestaff.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				disableS();
			}
		});

		enablestaff.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				enableS();
			}
		});

		popupmenu.add(editpassword);
		popupmenu.add(disablestaff);
		popupmenu.add(enablestaff);
	}

	private void disableS() {

		Staff staff;
		staff = tablemodel.getStaffs().get(table.getSelectedRow());
		staff.setPrsState(false);
		DBHibernate.update(staff);

	}

	private void enableS() {
		Staff staff;
		staff = tablemodel.getStaffs().get(table.getSelectedRow());
		staff.setPrsState(true);
		DBHibernate.update(staff);
	}

	private void passEditor() {
		Staff staf = tablemodel.getStaffs().get(table.getSelectedRow());
		JDialog dialog = new JDialog(JOptionPane.getFrameForComponent(table),
				"Change Password", true);
		PasswordEditor passwordEditor = new PasswordEditor(staf);
		passwordEditor.setPasswordDialog(dialog);
		dialog.getContentPane().add(passwordEditor);
		dialog.setLocationByPlatform(true);
		dialog.setLocationRelativeTo(null);
		dialog.setSize(350, 350);
		dialog.setVisible(true);
	}

	private void showMenu(MouseEvent e) {

		if (table.getSelectedRow() == -1) {
		} else if (table.getSelectedRow() == 2) {

		} else {
			popupmenu.show(e.getComponent(), e.getX(), e.getY());
		}

	}

	private void tablesetColor() {
		for (int i = 1; i < tablemodel.getColumnCount(); i++) {
			table.setDefaultRenderer(table.getColumnClass(i),
					new RowSetColorCellRender());

		}
	}

	public void getDatas() {

		SimpleExpression rest = Restrictions.eq("prsStatus", true);
		tablemodel.setStaffs(DBHibernate.getAll(Staff.class, new ArrayList(),
				rest));

	}

	public class GListener implements ActionListener {

		private JPanel panel;

		public GListener(JPanel panel) {
			this.panel = panel;
		}

		@Override
		public void actionPerformed(ActionEvent e) {

			String command = e.getActionCommand();

			Staff stf = null;
			int control = 0;
			for (Staff staffselected : tablemodel.getStaffs()) {
				if (staffselected.isSelected())
					control++;
			}

			if (command.equals("ADD")) {

				stf = openEditor(stf);
				if (stf != null) {

					try {
						DBHibernate.save(stf);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					tablemodel.getStaffs().add(stf);
					tablemodel.fireTableDataChanged();

				}

			} else if (command.equals("DELETE")) {

				if (control == 0) {
					JOptionPane.showMessageDialog(btnDelete,
							"You didn`t select record!", "Warning",
							JOptionPane.WARNING_MESSAGE);
				} else {
					int input = JOptionPane.showConfirmDialog(panel,
							"Are You Sure? ", "Delete Record",
							JOptionPane.YES_NO_OPTION);

					if (input == 0) {
						for (Staff staffselected : tablemodel.getStaffs()) {
							if (staffselected.isSelected()) {

								staffselected.setPrsStatus(false);
								DBHibernate.update(staffselected);
							}
						}
						getDatas();
						tablemodel.fireTableDataChanged();
					}

				}

			} else if (command.equals("EDIT")) {

				if (control != 1) {
					JOptionPane.showMessageDialog(btnEdit,
							"You didn`t select record!", "Warning",
							JOptionPane.WARNING_MESSAGE);
				} else if (control == 1) {
					for (Staff staffselected : tablemodel.getStaffs()) {
						if (staffselected.isSelected()) {
							staffselected = openEditor(staffselected);

							DBHibernate.update(staffselected);
							getDatas();
							tablemodel.fireTableDataChanged();
						}
					}
				}

			}

		}
	}

	private Staff openEditor(Staff staf) {
		JDialog dialog = new JDialog(JOptionPane.getFrameForComponent(table),
				"Create Staff", true);
		StaffEditor staffEditor = new StaffEditor(staf);
		staffEditor.setjDialog(dialog);
		dialog.getContentPane().add(staffEditor);
		dialog.setLocationByPlatform(true);
		dialog.setSize(900, 700);
		dialog.setVisible(true);
		staf = staffEditor.getStaff();
		return staf;
	}

	class RowSetColorCellRender extends DefaultTableCellRenderer {

		private static final long serialVersionUID = 1L;

		public Component getTableCellRendererComponent(JTable table,
				Object value, boolean isSelected, boolean hasFocus, int row,
				int column) {
			StaffTableModel wtm = (StaffTableModel) table.getModel();
			Staff staf = (Staff) wtm.getValueAtRow(row);

			if (staf.isPrsState()) {
				setBackground(Color.white);
			} else
				setBackground(Color.red);

			return super.getTableCellRendererComponent(table, value,
					isSelected, hasFocus, row, column);
		}
	}

}
