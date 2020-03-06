package com.ebc.outpatient.anamnez.panel;

import javax.swing.JPanel;

import java.awt.GridBagLayout;

import javax.swing.JTextArea;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class AnamnezEditor extends JPanel {

	private String anamnezDetail;
	private JDialog dialog;

	public AnamnezEditor() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 15, 0, 0, 0, 0, 0, 0, 0, 15, 0 };
		gridBagLayout.rowHeights = new int[] { 15, 0, 0, 0, 15, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0,
				1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 1.0, 0.0, 0.0,
				Double.MIN_VALUE };
		setLayout(gridBagLayout);

		JLabel lblNewLabel = new JLabel("ANAMNEZ DETAIL");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 1;
		gbc_lblNewLabel.gridy = 1;
		add(lblNewLabel, gbc_lblNewLabel);

		JTextArea anamnezTextArea = new JTextArea();
		GridBagConstraints gbc_anamnezTextArea = new GridBagConstraints();
		gbc_anamnezTextArea.gridheight = 2;
		gbc_anamnezTextArea.gridwidth = 5;
		gbc_anamnezTextArea.insets = new Insets(0, 0, 5, 5);
		gbc_anamnezTextArea.fill = GridBagConstraints.BOTH;
		gbc_anamnezTextArea.gridx = 3;
		gbc_anamnezTextArea.gridy = 1;
		add(anamnezTextArea, gbc_anamnezTextArea);

		JButton btnSaveReason = new JButton("SAVE");
		GridBagConstraints gbc_btnSaveReason = new GridBagConstraints();
		gbc_btnSaveReason.insets = new Insets(0, 0, 5, 5);
		gbc_btnSaveReason.gridx = 6;
		gbc_btnSaveReason.gridy = 3;
		add(btnSaveReason, gbc_btnSaveReason);

		JButton btnCancelReason = new JButton("CANCEL");
		GridBagConstraints gbc_btnCancelReason = new GridBagConstraints();
		gbc_btnCancelReason.insets = new Insets(0, 0, 5, 5);
		gbc_btnCancelReason.gridx = 7;
		gbc_btnCancelReason.gridy = 3;
		add(btnCancelReason, gbc_btnCancelReason);

		btnSaveReason.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (!anamnezTextArea.getText().isEmpty())
					anamnezDetail = anamnezTextArea.getText();
				dialog.dispose();
			}
		});

		btnCancelReason.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
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

	public String getAnamnezDetail() {
		return anamnezDetail;
	}

	public void setAnamnezDetail(String anamnezDetail) {
		this.anamnezDetail = anamnezDetail;
	}

}
