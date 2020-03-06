package com.ebc.definitions.components;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;

public class PatientSearchButton extends JButton {
	private JDialog dialog;
	public PatientSearchButton() {
		PatientSearchActionListener actionListener = new PatientSearchActionListener();
		addActionListener(actionListener);
	}

	
	
	class PatientSearchActionListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			
			PatientSearchPanel panel = new PatientSearchPanel();
			
			
			//dialog ekraný açýlacak
			//Search param
			
		}
		
	}
	
	

}
