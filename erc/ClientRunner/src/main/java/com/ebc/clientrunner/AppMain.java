package com.ebc.clientrunner;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.SQLException;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import com.ebc.admission.appointment.panel.AppointmentPanel;
import com.ebc.admission.serviceType.panel.ServiceTypePanel;
import com.ebc.core.DBHibernate;
import com.ebc.definitions.DefinitionsPanel;
import com.ebc.definitions.admission.panel.AdmissionPanel;
import com.ebc.definitions.organization.panel.OrganizationPanel;
import com.ebc.definitions.patient.panel.PatientPanel;
import com.ebc.definitions.service.panel.ServicePanel;
import com.ebc.definitions.session.Session;
import com.ebc.definitions.staff.panel.StaffMainPanel;
import com.ebc.definitions.test.TestDefinitionAppMain;
import com.ebc.definitions.test.table.TestTablePanel;
import com.ebc.deginitions.test.categories.TestCategoriesPanel;
import com.ebc.outpatient.doctor.DoctorMainPanel;

public class AppMain extends JFrame {

	private static final long serialVersionUID = 1L;

	private JMenuBar menuBar = new JMenuBar();
	private JMenu mnPatient;
	private JMenuItem mntmPatients;
	private JMenu mnPersonel;
	private JMenuItem mntmPersonels;
	private JMenu mnTest;
	private JMenuItem mntmTestCategories;
	private JMenuItem mntmTest;
	private JMenu mnOrganization;
	private JMenuItem mntmOrganization;
	private JMenu mnAdmission;
	private JMenuItem mntmAdmission;
	private JMenu mnAppointment;
	private JMenuItem mntmAppointment;
	private JMenuItem mntmTestTable;
	private JMenu mnDoctor;
	private JMenuItem mntmDoctor;
	private JMenu mnServices;
	private JMenuItem mntmServiceType;
	private JMenu mnDefinition;
	private JMenuItem mntmDefinition;
	private JMenuItem mntmServices;

	public AppMain() {
		getContentPane().setFont(new Font("Dialog", Font.PLAIN, 20));

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setSize(screenSize.width, screenSize.height);

		menuBar.setFont(new Font("Dialog", Font.PLAIN, 20));

		setJMenuBar(menuBar);

		mnPatient = new JMenu("Patient");
		mnPatient.setFont(new Font("Dialog", Font.PLAIN, 20));
		menuBar.add(mnPatient);

		mntmPatients = new JMenuItem("Patients");
		mntmPatients.setFont(new Font("Dialog", Font.PLAIN, 20));
		mnPatient.add(mntmPatients);

		mnPersonel = new JMenu("Personel");
		mnPersonel.setFont(new Font("Dialog", Font.PLAIN, 20));
		menuBar.add(mnPersonel);

		mntmPersonels = new JMenuItem("Personels");
		mntmPersonels.setFont(new Font("Dialog", Font.PLAIN, 20));
		mnPersonel.add(mntmPersonels);

		mnTest = new JMenu("Test");
		mnTest.setFont(new Font("Dialog", Font.PLAIN, 20));
		menuBar.add(mnTest);

		mntmTest = new JMenuItem("Test Definitions");
		mntmTest.setFont(new Font("Dialog", Font.PLAIN, 20));
		mnTest.add(mntmTest);

		mntmTestTable = new JMenuItem("Test Table");
		mntmTestTable.setFont(new Font("Dialog", Font.PLAIN, 20));
		mnTest.add(mntmTestTable);

		mntmTestCategories = new JMenuItem("Test Categories");
		mntmTestCategories.setFont(new Font("Dialog", Font.PLAIN, 20));
		mnTest.add(mntmTestCategories);

		mnOrganization = new JMenu("Organization");
		mnOrganization.setFont(new Font("Dialog", Font.PLAIN, 20));
		menuBar.add(mnOrganization);

		mntmOrganization = new JMenuItem("Organization");
		mntmOrganization.setFont(new Font("Dialog", Font.PLAIN, 20));
		mnOrganization.add(mntmOrganization);

		mnAdmission = new JMenu("Admission");
		mnAdmission.setFont(new Font("Dialog", Font.PLAIN, 20));
		menuBar.add(mnAdmission);

		mntmAdmission = new JMenuItem("Admission");
		mntmAdmission.setFont(new Font("Dialog", Font.PLAIN, 20));
		mnAdmission.add(mntmAdmission);

		mnAppointment = new JMenu("Appointment");
		mnAppointment.setFont(new Font("Dialog", Font.PLAIN, 20));
		menuBar.add(mnAppointment);

		mntmAppointment = new JMenuItem("Appointment");
		mntmAppointment.setFont(new Font("Dialog", Font.PLAIN, 20));
		mnAppointment.add(mntmAppointment);

		mnDoctor = new JMenu("Doctor");
		mnDoctor.setFont(new Font("Dialog", Font.PLAIN, 20));
		menuBar.add(mnDoctor);

		mntmDoctor = new JMenuItem("Doctor");
		mntmDoctor.setFont(new Font("Dialog", Font.PLAIN, 20));
		mnDoctor.add(mntmDoctor);

		mnServices = new JMenu("Services");
		mnServices.setFont(new Font("Dialog", Font.PLAIN, 20));
		menuBar.add(mnServices);

		mnDefinition = new JMenu("Definition");
		mnDefinition.setFont(new Font("Dialog", Font.PLAIN, 20));
		menuBar.add(mnDefinition);

		mntmDefinition = new JMenuItem("Discharge Type");
		mntmDefinition.setFont(new Font("Dialog", Font.PLAIN, 20));
		mnDefinition.add(mntmDefinition);

		mntmServiceType = new JMenuItem("Service Type");
		mntmServiceType.setFont(new Font("Dialog", Font.PLAIN, 20));
		mnServices.add(mntmServiceType);

		mntmServices = new JMenuItem("Services");
		mntmServices.setFont(new Font("Dialog", Font.PLAIN, 20));
		mnServices.add(mntmServices);

		clickMenuItem();

		addWindowListener(new WindowListener() {

			@Override
			public void windowOpened(WindowEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void windowIconified(WindowEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void windowDeiconified(WindowEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void windowDeactivated(WindowEvent e) {
				// TODO Auto-generated method stub
			}

			@Override
			public void windowClosing(WindowEvent e) {

				if (Session.getSession().getLogindate() != null) {
					Session.getSession().setLogoutdate(new Date());
					try {
						DBHibernate.save(Session.getSession());
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}

			}

			@Override
			public void windowClosed(WindowEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void windowActivated(WindowEvent e) {
				// TODO Auto-generated method stub

			}
		});

	}

	public void clickMenuItem() {

		mntmServiceType.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				System.out.println("Service type is loading");
				ServiceTypePanel panel = new ServiceTypePanel();
				getContentPane().removeAll();
				getContentPane().add(panel);
				setBounds(0, 0, App.screenSize.width, App.screenSize.height);
				setLocationRelativeTo(null);
				setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				setVisible(true);

			}
		});

		mntmServices.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				System.out.println("Service is loading");
				ServicePanel panel = new ServicePanel();
				getContentPane().removeAll();
				getContentPane().add(panel);
				setBounds(0, 0, App.screenSize.width, App.screenSize.height);
				setLocationRelativeTo(null);
				setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				setVisible(true);

			}
		});

		mntmAppointment.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				AppointmentPanel panel = new AppointmentPanel();
				getContentPane().removeAll();
				getContentPane().add(panel);
				setBounds(0, 0, App.screenSize.width, App.screenSize.height);
				setLocationRelativeTo(null);
				setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				setVisible(true);

			}
		});
		mntmPatients.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				PatientPanel panel = new PatientPanel();
				getContentPane().removeAll();
				getContentPane().add(panel);
				setBounds(0, 0, App.screenSize.width, App.screenSize.height);
				setLocationRelativeTo(null);
				setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				setVisible(true);
			}
		});

		mntmPersonels.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				System.out.println("Hello Staffs are loading");
				StaffMainPanel panel = new StaffMainPanel();
				getContentPane().removeAll();
				getContentPane().add(panel);
				setBounds(0, 0, App.screenSize.width, App.screenSize.height);
				setLocationRelativeTo(null);
				setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				setVisible(true);

			}
		});

		mntmTest.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				System.out.println("Hello Patients are loading");
				TestDefinitionAppMain panel = new TestDefinitionAppMain();
				getContentPane().removeAll();
				getContentPane().add(panel);
				setBounds(0, 0, App.screenSize.width, App.screenSize.height);
				setLocationRelativeTo(null);
				setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				setVisible(true);

			}
		});

		mntmDefinition.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				DefinitionsPanel panel = new DefinitionsPanel();
				getContentPane().removeAll();
				getContentPane().add(panel);
				setBounds(0, 0, App.screenSize.width, App.screenSize.height);
				setLocationRelativeTo(null);
				setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				setVisible(true);

			}
		});

		mntmTestCategories.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				System.out.println("Hello Patients are loading");
				TestCategoriesPanel panel = new TestCategoriesPanel();
				getContentPane().removeAll();
				getContentPane().add(panel);
				setBounds(0, 0, App.screenSize.width, App.screenSize.height);
				setLocationRelativeTo(null);
				setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				setVisible(true);

			}
		});

		mntmOrganization.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				OrganizationPanel panel = new OrganizationPanel();
				getContentPane().removeAll();
				getContentPane().add(panel);
				setBounds(0, 0, App.screenSize.width, App.screenSize.height);
				setLocationRelativeTo(null);
				setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				setVisible(true);
			}
		});

		mntmAdmission.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				AdmissionPanel panel = new AdmissionPanel();
				getContentPane().removeAll();
				getContentPane().add(panel);
				setBounds(0, 0, App.screenSize.width, App.screenSize.height);
				setLocationRelativeTo(null);
				setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				setVisible(true);
			}
		});

		mntmTestTable.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				TestTablePanel panel = new TestTablePanel();
				getContentPane().removeAll();
				getContentPane().add(panel);
				setBounds(0, 0, App.screenSize.width, App.screenSize.height);
				setLocationRelativeTo(null);
				setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				setVisible(true);

			}
		});

		mntmDoctor.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				DoctorMainPanel panel = new DoctorMainPanel();
				getContentPane().removeAll();
				getContentPane().add(panel);
				setBounds(0, 0, App.screenSize.width, App.screenSize.height);
				setLocationRelativeTo(null);
				setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				setVisible(true);

			}
		});

	}

}
