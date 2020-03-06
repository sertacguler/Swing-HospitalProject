package com.ebc.admission.serviceType.panel;

import javax.swing.JDialog;
import javax.swing.JPanel;

import java.awt.GridBagLayout;

import javax.swing.JLabel;

import java.awt.GridBagConstraints;

import javax.swing.JTextField;

import java.awt.Insets;

import javax.swing.JButton;

import com.ebc.admission.serviceType.entity.ServiceTypeDto;
import com.ebc.core.DBHibernate;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class ServiceTypeEditor extends JPanel {
	private JTextField txtTypeName;
	private JDialog dialog;
	private ServiceTypeDto  dto = null;
	
	public ServiceTypeEditor(ServiceTypeDto serviceTypeDto) {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{20, 146, 23, 23, 0, 0, 10, 0};
		gridBagLayout.rowHeights = new int[]{10, 0, 10, 0, 10, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel lblServiceTypeName = new JLabel("Service Type Name ");
		lblServiceTypeName.setFont(new Font("Tahoma", Font.PLAIN, 17));
		GridBagConstraints gbc_lblServiceTypeName = new GridBagConstraints();
		gbc_lblServiceTypeName.insets = new Insets(0, 0, 5, 5);
		gbc_lblServiceTypeName.gridx = 1;
		gbc_lblServiceTypeName.gridy = 1;
		add(lblServiceTypeName, gbc_lblServiceTypeName);
		
		txtTypeName = new JTextField();
		txtTypeName.setFont(new Font("Tahoma", Font.PLAIN, 17));
		GridBagConstraints gbc_txtTypeName = new GridBagConstraints();
		gbc_txtTypeName.gridwidth = 3;
		gbc_txtTypeName.insets = new Insets(0, 0, 5, 5);
		gbc_txtTypeName.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtTypeName.gridx = 3;
		gbc_txtTypeName.gridy = 1;
		add(txtTypeName, gbc_txtTypeName);
		txtTypeName.setColumns(10);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setFont(new Font("Tahoma", Font.BOLD, 15));
		GridBagConstraints gbc_btnCancel = new GridBagConstraints();
		gbc_btnCancel.insets = new Insets(0, 0, 5, 5);
		gbc_btnCancel.gridx = 3;
		gbc_btnCancel.gridy = 3;
		add(btnCancel, gbc_btnCancel);
		
		JButton btnSave = new JButton("Save");
		btnSave.setFont(new Font("Tahoma", Font.BOLD, 15));
		GridBagConstraints gbc_btnSave = new GridBagConstraints();
		gbc_btnSave.insets = new Insets(0, 0, 5, 5);
		gbc_btnSave.gridx = 5;
		gbc_btnSave.gridy = 3;
		add(btnSave, gbc_btnSave);
		dto = serviceTypeDto;
		if(serviceTypeDto != null){
			txtTypeName.setText(serviceTypeDto.getName());
		}
		
		btnSave.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(serviceTypeDto == null){
					dto = new ServiceTypeDto();
				}

				dto.setName(txtTypeName.getText());
				dto.setStatus(true);
				dialog.dispose();
			}
		});
		btnCancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Clicked Cancel");
				dialog.dispose();
			}
		});
	}

	public JDialog getDialog() {
		return dialog;
	}
	public void setDialog(JDialog dialog) {
		this.dialog = dialog;
	}
	public ServiceTypeDto getDto() {
		return dto;
	}
	public void setDto(ServiceTypeDto dto) {
		this.dto = dto;
	}
}
