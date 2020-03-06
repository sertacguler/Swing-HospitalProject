package com.ebc.definitions.staff.panel;

import javax.swing.JPanel;

import java.awt.GridBagLayout;

import javax.swing.JLabel;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JButton;

import com.ebc.core.DBHibernate;
import com.ebc.definitions.staff.entity.MD5PasswordGenerator;
import com.ebc.definitions.staff.entity.Staff;

public class PasswordEditor extends JPanel {

	private static final long serialVersionUID = 1L;
	private JPasswordField txtpassword;
	private JPasswordField txtpasswordt;
	private JLabel lPassword = new JLabel("Password");
	private JLabel lPasswordt = new JLabel("Re-Enter Password");
	private JButton btnConfirm = new JButton("Confirm");
	private Staff staff;
	private JDialog passwordDialog;

	public JDialog getPasswordDialog() {
		return passwordDialog;
	}

	public void setPasswordDialog(JDialog passwordDialog) {
		this.passwordDialog = passwordDialog;
	}

	public Staff getStaff() {
		return staff;
	}

	public void setStaff(Staff staff) {
		this.staff = staff;
	}

	public PasswordEditor(Staff staffdetail) {

		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 15, 0, 89, 0, 0, 0, 0 };
		gridBagLayout.rowHeights = new int[] { 15, 0, 0, 0, 40, 42, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0,
				0.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				0.0, Double.MIN_VALUE };
		setLayout(gridBagLayout);

		GridBagConstraints gbc_lPassword = new GridBagConstraints();
		gbc_lPassword.anchor = GridBagConstraints.WEST;
		gbc_lPassword.insets = new Insets(0, 0, 5, 5);
		gbc_lPassword.gridx = 2;
		gbc_lPassword.gridy = 4;
		add(lPassword, gbc_lPassword);

		txtpassword = new JPasswordField();
		GridBagConstraints gbc_txtpassword = new GridBagConstraints();
		gbc_txtpassword.insets = new Insets(0, 0, 5, 5);
		gbc_txtpassword.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtpassword.gridx = 4;
		gbc_txtpassword.gridy = 4;
		add(txtpassword, gbc_txtpassword);
		txtpassword.setColumns(10);

		GridBagConstraints gbc_lPasswordt = new GridBagConstraints();
		gbc_lPasswordt.anchor = GridBagConstraints.EAST;
		gbc_lPasswordt.insets = new Insets(0, 0, 5, 5);
		gbc_lPasswordt.gridx = 2;
		gbc_lPasswordt.gridy = 5;
		add(lPasswordt, gbc_lPasswordt);

		txtpasswordt = new JPasswordField();
		GridBagConstraints gbc_txtpasswordt = new GridBagConstraints();
		gbc_txtpasswordt.insets = new Insets(0, 0, 5, 5);
		gbc_txtpasswordt.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtpasswordt.gridx = 4;
		gbc_txtpasswordt.gridy = 5;
		add(txtpasswordt, gbc_txtpasswordt);
		txtpasswordt.setColumns(10);

		GridBagConstraints gbc_btnConfirm = new GridBagConstraints();
		gbc_btnConfirm.anchor = GridBagConstraints.EAST;
		gbc_btnConfirm.insets = new Insets(0, 0, 0, 5);
		gbc_btnConfirm.gridx = 4;
		gbc_btnConfirm.gridy = 6;
		add(btnConfirm, gbc_btnConfirm);

		btnConfirm.addActionListener(new ActionListener() {

			@SuppressWarnings("deprecation")
			@Override
			public void actionPerformed(ActionEvent e) {

				if (txtpassword.getText().equals(txtpasswordt.getText())) {

					String cevir1 = MD5PasswordGenerator
							.passwordGenerator(txtpassword.getText());
					staffdetail.setPrsPassword(cevir1);
					DBHibernate.update(staffdetail);
					System.out.println("Password is changed.");
					passwordDialog.dispose();

				} else {

					JOptionPane.showMessageDialog(btnConfirm,
							"Password did`t match!\n" + "Please Try Again",
							"Warning", JOptionPane.WARNING_MESSAGE);
				}

			}
		});
	}

}
