package com.ebc.definitions.admission.panel;

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

public class AdmissionCancelEditor extends JPanel {

	private String reason;
	private JDialog dialog;

	public AdmissionCancelEditor() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 15, 0, 0, 0, 0, 0, 0, 0, 15, 0 };
		gridBagLayout.rowHeights = new int[] { 15, 0, 0, 0, 15, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 1.0,
				0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 1.0, 0.0, 0.0,
				Double.MIN_VALUE };
		setLayout(gridBagLayout);

		JLabel lblNewLabel = new JLabel("REASON");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 1;
		gbc_lblNewLabel.gridy = 1;
		add(lblNewLabel, gbc_lblNewLabel);

		JTextArea reasonTextArea = new JTextArea();
		GridBagConstraints gbc_reasonTextArea = new GridBagConstraints();
		gbc_reasonTextArea.gridheight = 2;
		gbc_reasonTextArea.gridwidth = 4;
		gbc_reasonTextArea.insets = new Insets(0, 0, 5, 5);
		gbc_reasonTextArea.fill = GridBagConstraints.BOTH;
		gbc_reasonTextArea.gridx = 3;
		gbc_reasonTextArea.gridy = 1;
		add(reasonTextArea, gbc_reasonTextArea);

		JButton btnSaveReason = new JButton("SAVE");
		GridBagConstraints gbc_btnSaveReason = new GridBagConstraints();
		gbc_btnSaveReason.insets = new Insets(0, 0, 5, 5);
		gbc_btnSaveReason.gridx = 5;
		gbc_btnSaveReason.gridy = 3;
		add(btnSaveReason, gbc_btnSaveReason);

		JButton btnCancelReason = new JButton("CANCEL");
		GridBagConstraints gbc_btnCancelReason = new GridBagConstraints();
		gbc_btnCancelReason.insets = new Insets(0, 0, 5, 5);
		gbc_btnCancelReason.gridx = 6;
		gbc_btnCancelReason.gridy = 3;
		add(btnCancelReason, gbc_btnCancelReason);

		btnSaveReason.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (!reasonTextArea.getText().isEmpty())
					reason = reasonTextArea.getText();
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

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

}
