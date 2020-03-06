package com.ebc.deginitions.test.categories;

import javax.swing.JPanel;

import java.awt.GridBagLayout;

import javax.swing.JLabel;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JDialog;
import javax.swing.JTextField;
import javax.swing.JButton;

import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.SimpleExpression;

import com.ebc.core.DBHibernate;
import com.ebc.definitions.entity.TestCategoriesDTO;
import com.ebc.definitions.organization.entity.OrganizationDTO;

public class TestCategoriesEditor extends JPanel {
	private JTextField txtCategoriesName;
	private TestCategoriesDTO tstCat;
	private JDialog dialog;

	public TestCategoriesDTO getTstCat() {
		return tstCat;
	}

	public void setTstCat(TestCategoriesDTO tstCat) {
		this.tstCat = tstCat;
	}

	public JDialog getDialog() {
		return dialog;
	}

	public void setDialog(JDialog dialog) {
		this.dialog = dialog;
	}

	public JTextField getTextField() {
		return txtCategoriesName;
	}

	public void setTextField(JTextField textField) {
		this.txtCategoriesName = textField;
	}

	public TestCategoriesEditor(TestCategoriesDTO testCat) {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 0, 0, 0, 0, 0, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 0, 0, 0, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 0.0, 1.0, 1.0, 1.0,
				0.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0,
				Double.MIN_VALUE };
		setLayout(gridBagLayout);

		JLabel lblNewLabel = new JLabel("Test Categories Name : ");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 1;
		gbc_lblNewLabel.gridy = 2;
		add(lblNewLabel, gbc_lblNewLabel);

		txtCategoriesName = new JTextField();
		GridBagConstraints gbc_txtCategoriesName = new GridBagConstraints();
		gbc_txtCategoriesName.insets = new Insets(0, 0, 5, 5);
		gbc_txtCategoriesName.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtCategoriesName.gridx = 2;
		gbc_txtCategoriesName.gridy = 2;
		add(txtCategoriesName, gbc_txtCategoriesName);
		txtCategoriesName.setColumns(10);

		JButton btnSave = new JButton("SAVE");
		GridBagConstraints gbc_btnSave = new GridBagConstraints();
		gbc_btnSave.anchor = GridBagConstraints.EAST;
		gbc_btnSave.insets = new Insets(0, 0, 0, 5);
		gbc_btnSave.gridx = 3;
		gbc_btnSave.gridy = 4;
		add(btnSave, gbc_btnSave);

		JButton btnCancel = new JButton("CANCEL");
		GridBagConstraints gbc_btnCancel = new GridBagConstraints();
		gbc_btnCancel.anchor = GridBagConstraints.WEST;
		gbc_btnCancel.insets = new Insets(0, 0, 0, 5);
		gbc_btnCancel.gridx = 4;
		gbc_btnCancel.gridy = 4;
		add(btnCancel, gbc_btnCancel);

		btnCancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				dialog.dispose();

			}
		});
		btnSave.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				if (tstCat == null) {
					tstCat = new TestCategoriesDTO();
				}
				tstCat.setTestCategoriesName(txtCategoriesName.getText());
				dialog.dispose();

			}
		});
		tstCat = testCat;

		if (testCat != null) {
			txtCategoriesName.setText(tstCat.getTestCategoriesName());
		}
	}

}
