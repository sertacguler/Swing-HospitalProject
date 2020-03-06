package com.ebc.definitions;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.ebc.definitions.entity.DefinitionDTO;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DefinitionsEditor extends JPanel {
	private JDialog dialog;
	private DefinitionDTO definition;
	private JTextField txtDefinitionName;

	public DefinitionDTO getDefinition() {
		return definition;
	}

	public void setDefinition(DefinitionDTO definition) {
		this.definition = definition;
	}

	public JDialog getDialog() {
		return dialog;
	}

	public void setDialog(JDialog dialog) {
		this.dialog = dialog;
	}

	public DefinitionsEditor(DefinitionDTO def ) {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 5, 5, 0, 0, 0, 0, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 15, 0, 0, 5, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 0.0, 1.0, 1.0, 1.0,
				0.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0,
				Double.MIN_VALUE };
		setLayout(gridBagLayout);

		JLabel lblNewLabel = new JLabel("Discharge Type Name : ");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 1;
		gbc_lblNewLabel.gridy = 2;
		add(lblNewLabel, gbc_lblNewLabel);

		txtDefinitionName = new JTextField();
		GridBagConstraints gbc_txtCategoriesName = new GridBagConstraints();
		gbc_txtCategoriesName.insets = new Insets(0, 0, 5, 5);
		gbc_txtCategoriesName.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtCategoriesName.gridx = 2;
		gbc_txtCategoriesName.gridy = 2;
		add(txtDefinitionName, gbc_txtCategoriesName);
		txtDefinitionName.setColumns(10);

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
				// TODO Auto-generated method stub
				dialog.dispose();
				
			}
		});
		btnSave.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(definition == null) {
					definition = new DefinitionDTO();
				}
				definition.setDefinitionName(txtDefinitionName.getText());
				dialog.dispose();
			}
		});
		
		definition = def;
		
		if(definition!=null){ txtDefinitionName.setText(definition.getDefinitionName());
		}
	}
	

}
