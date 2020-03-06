package com.ebc.definitions.organization.panel;

import javax.swing.JPanel;

import java.awt.GridBagLayout;

import javax.swing.JLabel;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JDialog;
import javax.swing.JTextField;

import com.ebc.definitions.organization.entity.OrganizationDTO;

import javax.swing.JButton;
import javax.swing.JRadioButton;

public class OrganizationEditor extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField nameTextField;
	private JTextField codeTextField;

	private JDialog dialog;
	private OrganizationDTO orgDto;

	public OrganizationEditor(OrganizationDTO orgDtoDetail) {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 15, 0, 10, 0, 0, 0, 0, 0, 0,
				15, 0 };
		gridBagLayout.rowHeights = new int[] { 15, 0, 5, 0, 5, 0, 0, 0, 0, 15,
				0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0,
				1.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		setLayout(gridBagLayout);

		JLabel lblName = new JLabel("Organization Name");
		GridBagConstraints gbc_lblName = new GridBagConstraints();
		gbc_lblName.anchor = GridBagConstraints.WEST;
		gbc_lblName.insets = new Insets(0, 0, 5, 5);
		gbc_lblName.gridx = 1;
		gbc_lblName.gridy = 1;
		add(lblName, gbc_lblName);

		nameTextField = new JTextField();
		GridBagConstraints gbc_nameTextField = new GridBagConstraints();
		gbc_nameTextField.gridwidth = 6;
		gbc_nameTextField.insets = new Insets(0, 0, 5, 5);
		gbc_nameTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_nameTextField.gridx = 3;
		gbc_nameTextField.gridy = 1;
		add(nameTextField, gbc_nameTextField);
		nameTextField.setColumns(10);

		JLabel lblCode = new JLabel("Organiation Code");
		GridBagConstraints gbc_lblCode = new GridBagConstraints();
		gbc_lblCode.anchor = GridBagConstraints.WEST;
		gbc_lblCode.insets = new Insets(0, 0, 5, 5);
		gbc_lblCode.gridx = 1;
		gbc_lblCode.gridy = 3;
		add(lblCode, gbc_lblCode);

		codeTextField = new JTextField();
		GridBagConstraints gbc_codeTextField = new GridBagConstraints();
		gbc_codeTextField.gridwidth = 6;
		gbc_codeTextField.insets = new Insets(0, 0, 5, 5);
		gbc_codeTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_codeTextField.gridx = 3;
		gbc_codeTextField.gridy = 3;
		add(codeTextField, gbc_codeTextField);
		codeTextField.setColumns(10);

		JLabel lblNewLabel = new JLabel("Status");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 1;
		gbc_lblNewLabel.gridy = 5;
		add(lblNewLabel, gbc_lblNewLabel);

		ButtonGroup gButtonGroup = new ButtonGroup();

		JRadioButton rdbtnActive = new JRadioButton("Aktif");
		GridBagConstraints gbc_rdbtnActive = new GridBagConstraints();
		gbc_rdbtnActive.insets = new Insets(0, 0, 5, 5);
		gbc_rdbtnActive.gridx = 3;
		gbc_rdbtnActive.gridy = 5;
		add(rdbtnActive, gbc_rdbtnActive);

		JRadioButton rdbtnPassive = new JRadioButton("Pasif");
		GridBagConstraints gbc_rdbtnPassive = new GridBagConstraints();
		gbc_rdbtnPassive.insets = new Insets(0, 0, 5, 5);
		gbc_rdbtnPassive.gridx = 4;
		gbc_rdbtnPassive.gridy = 5;
		add(rdbtnPassive, gbc_rdbtnPassive);

		gButtonGroup.add(rdbtnActive);
		gButtonGroup.add(rdbtnPassive);

		JButton btnSave = new JButton("SAVE");
		GridBagConstraints gbc_btnSave = new GridBagConstraints();
		gbc_btnSave.insets = new Insets(0, 0, 5, 5);
		gbc_btnSave.gridx = 6;
		gbc_btnSave.gridy = 7;
		add(btnSave, gbc_btnSave);

		JButton btnCancel = new JButton("CANCEL");
		GridBagConstraints gbc_btnCancel = new GridBagConstraints();
		gbc_btnCancel.insets = new Insets(0, 0, 5, 5);
		gbc_btnCancel.gridx = 7;
		gbc_btnCancel.gridy = 7;
		add(btnCancel, gbc_btnCancel);

		btnSave.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (orgDto == null) {
					orgDto = new OrganizationDTO();
				}
				orgDto.setOrganizationName(nameTextField.getText());
				orgDto.setOrganizationCode(codeTextField.getText());
				if (rdbtnActive.isSelected()) {
					orgDto.setStatus(true);
				} else {
					orgDto.setStatus(false);
				}
				dialog.dispose();
			}
		});

		btnCancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dialog.dispose();
			}
		});

		orgDto = orgDtoDetail;

		if (orgDtoDetail != null) {
			nameTextField.setText(orgDtoDetail.getOrganizationName());
			codeTextField.setText(orgDtoDetail.getOrganizationCode());
			if (orgDtoDetail.getStatus() == true) {
				rdbtnActive.setSelected(true);
				rdbtnPassive.setSelected(false);
			} else {
				rdbtnActive.setSelected(false);
				rdbtnPassive.setSelected(true);
			}
		}

	}

	public JDialog getDialog() {
		return dialog;
	}

	public void setDialog(JDialog dialog) {
		this.dialog = dialog;
	}

	public OrganizationDTO getOrgDto() {
		return orgDto;
	}

	public void setOrgDto(OrganizationDTO orgDto) {
		this.orgDto = orgDto;
	}

}
