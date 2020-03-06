package com.ebc.clientrunner;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.SimpleExpression;

import com.ebc.core.DBHibernate;
import com.ebc.definitions.session.Session;
import com.ebc.definitions.session.SessionDTO;
import com.ebc.definitions.staff.entity.MD5PasswordGenerator;
import com.ebc.definitions.staff.entity.Staff;

import java.awt.Color;

public class App extends JFrame {

	private static final long serialVersionUID = 1L;
	private JTextField txtusername;
	private JPasswordField txtpassword;
	private JButton btnlogin = new JButton("Login");
	private JLabel lpassword = new JLabel("Password");
	private JButton btnSifresiz;
	private ArrayList<Staff> staffs = new ArrayList<Staff>();
	private EnterKeyListener ek;
	private SessionDTO sessionDto = new SessionDTO();
	static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	private static App lapp;
	private static boolean check = false;

	public static void main(String[] args) {

		lapp = new App();
		lapp.setTitle("User Login");
		lapp.setSize(500, 500);
		lapp.setLocationRelativeTo(null);
		lapp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		lapp.setVisible(true);
	}

	public ArrayList<Staff> getStaffs() {
		return staffs;
	}

	public void setStaffs(ArrayList<Staff> staffs) {
		this.staffs = staffs;
	}

	public App() {
		getContentPane().setBackground(Color.LIGHT_GRAY);

		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 15, 0, 0, 0, 71, 89, 85, 0,
				15, 0, 0 };
		gridBagLayout.rowHeights = new int[] { 15, 67, 58, 15, 0, 0, 0, 0, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 1.0, 0.0, 0.0, 0.0,
				0.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 1.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				0.0, 1.0, 1.0, Double.MIN_VALUE };
		getContentPane().setLayout(gridBagLayout);

		JLabel lusername = new JLabel("Username");
		lusername.setBackground(Color.RED);
		GridBagConstraints gbc_lusername = new GridBagConstraints();
		gbc_lusername.anchor = GridBagConstraints.WEST;
		gbc_lusername.insets = new Insets(0, 0, 5, 5);
		gbc_lusername.gridx = 3;
		gbc_lusername.gridy = 1;
		getContentPane().add(lusername, gbc_lusername);

		txtusername = new JTextField();
		txtusername.setBackground(Color.WHITE);
		GridBagConstraints gbc_txtusername = new GridBagConstraints();
		gbc_txtusername.gridwidth = 2;
		gbc_txtusername.insets = new Insets(0, 0, 5, 5);
		gbc_txtusername.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtusername.gridx = 5;
		gbc_txtusername.gridy = 1;
		getContentPane().add(txtusername, gbc_txtusername);
		txtusername.setColumns(10);

		GridBagConstraints gbc_lpassword = new GridBagConstraints();
		gbc_lpassword.anchor = GridBagConstraints.WEST;
		gbc_lpassword.insets = new Insets(0, 0, 5, 5);
		gbc_lpassword.gridx = 3;
		gbc_lpassword.gridy = 2;
		getContentPane().add(lpassword, gbc_lpassword);

		txtpassword = new JPasswordField();
		txtpassword.setBackground(Color.WHITE);
		GridBagConstraints gbc_txtpassword = new GridBagConstraints();
		gbc_txtpassword.gridwidth = 2;
		gbc_txtpassword.insets = new Insets(0, 0, 5, 5);
		gbc_txtpassword.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtpassword.gridx = 5;
		gbc_txtpassword.gridy = 2;
		getContentPane().add(txtpassword, gbc_txtpassword);
		txtpassword.setColumns(10);

		GridBagConstraints gbc_btnlogin = new GridBagConstraints();
		gbc_btnlogin.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnlogin.insets = new Insets(0, 0, 5, 5);
		gbc_btnlogin.gridx = 5;
		gbc_btnlogin.gridy = 3;
		btnlogin.setBackground(Color.GREEN);
		getContentPane().add(btnlogin, gbc_btnlogin);

		btnSifresiz = new JButton("Pass");
		GridBagConstraints gbc_btnSifresiz = new GridBagConstraints();
		gbc_btnSifresiz.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnSifresiz.insets = new Insets(0, 0, 5, 5);
		gbc_btnSifresiz.gridx = 6;
		gbc_btnSifresiz.gridy = 3;
		getContentPane().add(btnSifresiz, gbc_btnSifresiz);

		getStaff();

		btnSifresiz.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				sessionDto.setLogindate(new Date());
				sessionDto.setStaffid(4);
				sessionDto.setStaffdto(staffs.get(3));
				Session.setSession(sessionDto);
				dispose();
				initMainPanel();
			}
		});

		ek = new EnterKeyListener();
		txtpassword.addKeyListener(ek);

		btnlogin.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				if (login(check)) {

					dispose();
					initMainPanel();
				} else {
					JOptionPane.showMessageDialog(btnlogin,
							"Username or Password is invalid!", "Warning",
							JOptionPane.WARNING_MESSAGE);
				}

			}
		});

	}

	public void getStaff() {

		SimpleExpression rest = Restrictions.eq("prsStatus", true);
		setStaffs(DBHibernate.getAll(Staff.class, new ArrayList(), rest));

	}

	@SuppressWarnings("deprecation")
	private boolean login(boolean check) {

		for (Staff staff : staffs) {

			if (staff.getPrsName().equals(txtusername.getText())
					&& staff.getPrsPassword().equals(
							MD5PasswordGenerator.passwordGenerator(txtpassword
									.getText())) && staff.isPrsState()) {
				sessionDto.setLogindate(new Date());
				sessionDto.setStaffid(staff.getPrsId());
				sessionDto.setStaffdto(staff);
				Session.setSession(sessionDto);
				check = true;
			}

		}
		return check;
	}

	public void initMainPanel() {

		DBHibernate.getSession();
		AppMain main = new AppMain();
		main.setLocationRelativeTo(null);
		main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		main.setVisible(true);
	}

	public class EnterKeyListener implements KeyListener {

		@Override
		public void keyPressed(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_ENTER) {
				if (login(check)) {

					dispose();
					initMainPanel();

				} else {
					JOptionPane.showMessageDialog(btnlogin,
							"Username or Password is invalid!", "Warning",
							JOptionPane.WARNING_MESSAGE);
				}
			}
		}

		@Override
		public void keyReleased(KeyEvent e) {

		}

		@Override
		public void keyTyped(KeyEvent e) {

		}

	}

}
