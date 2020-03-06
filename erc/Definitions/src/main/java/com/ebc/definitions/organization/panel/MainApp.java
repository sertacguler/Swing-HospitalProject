package com.ebc.definitions.organization.panel;

import javax.swing.JFrame;

public class MainApp extends JFrame {
	public MainApp() {
	}
	public static void main(String[] args) {

		MainApp app = new MainApp();
		OrganizationPanel panel = new OrganizationPanel();

		app.getContentPane().add(panel);
		app.setTitle("Organization");
		app.setSize(600, 400);
		app.setLocationRelativeTo(null);
		app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		app.setVisible(true);
	}
}
