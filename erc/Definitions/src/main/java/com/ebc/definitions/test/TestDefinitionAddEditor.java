package com.ebc.definitions.test;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.JLabel;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.ArrayList;

import javax.swing.JCheckBox;
import javax.swing.table.TableModel;
import javax.swing.text.NumberFormatter;
import javax.xml.soap.Text;

import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.SimpleExpression;

import com.ebc.core.AqBaseEntity;
import com.ebc.core.DBHibernate;
import com.ebc.definitions.combobox.ComboboxModel;
import com.ebc.definitions.entity.TestCategoriesDTO;
import com.ebc.definitions.entity.TestDefinitionDTO;
import com.ebc.definitions.organization.entity.OrganizationDTO;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Color;

public class TestDefinitionAddEditor extends JPanel {
	private JTextField txtTestName;
	private JTextField txtTestCode;
	private TestDefinitionDTO tstDto;
	JComboBox cbCategories = new JComboBox();
	private JDialog dialog;
	private TableModel tblModel;
	private ArrayList<OrganizationDTO> orgDTO = null;
	private ArrayList<AqBaseEntity> testDtoCbOrganization = new ArrayList<AqBaseEntity>();
	SimpleExpression rest = Restrictions.eq("status", true);
	private ArrayList<TestCategoriesDTO> tstCatDTO = null;
	private JFormattedTextField txtMinValue;
	private JFormattedTextField txtMaxValue;
	private ComboboxModel comboboxModel = new ComboboxModel();



	public ArrayList<AqBaseEntity> getTestDtoCbOrganization() {
		return testDtoCbOrganization;
	}

	public void setTestDtoCbOrganization(
			ArrayList<AqBaseEntity> testDtoCbOrganization) {
		this.testDtoCbOrganization = testDtoCbOrganization;
	}

	public ArrayList<OrganizationDTO> getOrgDTO() {
		return orgDTO;
	}

	public void setOrgDTO(ArrayList<OrganizationDTO> orgDTO) {
		this.orgDTO = orgDTO;
	}

	public JTextField getTxtTestName() {
		return txtTestName;
	}

	public void setTxtTestName(JTextField txtTestName) {
		this.txtTestName = txtTestName;
	}

	public JTextField getTxtTestCode() {
		return txtTestCode;
	}

	public void setTxtTestCode(JTextField txtTestCode) {
		this.txtTestCode = txtTestCode;
	}
	

	public TestDefinitionAddEditor(TestDefinitionDTO testDTO) {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 5, 0, 10, 0, 0, 0, 0, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
				0, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 1.0,
				0.0, 0.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		setLayout(gridBagLayout);

		JLabel lblTestCategories = new JLabel("Test Categories :");
		GridBagConstraints gbc_lblTestCategories = new GridBagConstraints();
		gbc_lblTestCategories.anchor = GridBagConstraints.WEST;
		gbc_lblTestCategories.insets = new Insets(0, 0, 5, 5);
		gbc_lblTestCategories.gridx = 1;
		gbc_lblTestCategories.gridy = 1;
		add(lblTestCategories, gbc_lblTestCategories);

		
		GridBagConstraints gbc_cbCategories = new GridBagConstraints();
		gbc_cbCategories.gridwidth = 2;
		gbc_cbCategories.insets = new Insets(0, 0, 5, 5);
		gbc_cbCategories.fill = GridBagConstraints.HORIZONTAL;
		gbc_cbCategories.gridx = 3;
		gbc_cbCategories.gridy = 1;
		add(cbCategories, gbc_cbCategories);
		
		
	
		

		JLabel lblOrganization = new JLabel("Organization");
		GridBagConstraints gbc_lblOrganization = new GridBagConstraints();
		gbc_lblOrganization.anchor = GridBagConstraints.WEST;
		gbc_lblOrganization.insets = new Insets(0, 0, 5, 5);
		gbc_lblOrganization.gridx = 1;
		gbc_lblOrganization.gridy = 2;
		add(lblOrganization, gbc_lblOrganization);

		JComboBox cbOrganization = new JComboBox();

		GridBagConstraints gbc_cbOrganization = new GridBagConstraints();
		gbc_cbOrganization.gridwidth = 2;
		gbc_cbOrganization.insets = new Insets(0, 0, 5, 5);
		gbc_cbOrganization.fill = GridBagConstraints.HORIZONTAL;
		gbc_cbOrganization.gridx = 3;
		gbc_cbOrganization.gridy = 2;
		add(cbOrganization, gbc_cbOrganization);

		JLabel lblTestCode = new JLabel("Test Code :");
		GridBagConstraints gbc_lblTestCode = new GridBagConstraints();
		gbc_lblTestCode.anchor = GridBagConstraints.WEST;
		gbc_lblTestCode.insets = new Insets(0, 0, 5, 5);
		gbc_lblTestCode.gridx = 1;
		gbc_lblTestCode.gridy = 3;
		add(lblTestCode, gbc_lblTestCode);

		txtTestCode = new JTextField();
		GridBagConstraints gbc_txtTestCode = new GridBagConstraints();
		gbc_txtTestCode.gridwidth = 2;
		gbc_txtTestCode.insets = new Insets(0, 0, 5, 5);
		gbc_txtTestCode.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtTestCode.gridx = 3;
		gbc_txtTestCode.gridy = 3;
		add(txtTestCode, gbc_txtTestCode);
		txtTestCode.setColumns(10);

		JLabel lblTestName = new JLabel("Test Name :");
		GridBagConstraints gbc_lblTestName = new GridBagConstraints();
		gbc_lblTestName.anchor = GridBagConstraints.WEST;
		gbc_lblTestName.insets = new Insets(0, 0, 5, 5);
		gbc_lblTestName.gridx = 1;
		gbc_lblTestName.gridy = 4;
		add(lblTestName, gbc_lblTestName);

		txtTestName = new JTextField();
		GridBagConstraints gbc_txtTestName = new GridBagConstraints();
		gbc_txtTestName.gridwidth = 2;
		gbc_txtTestName.insets = new Insets(0, 0, 5, 5);
		gbc_txtTestName.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtTestName.gridx = 3;
		gbc_txtTestName.gridy = 4;
		add(txtTestName, gbc_txtTestName);
		txtTestName.setColumns(10);

		JLabel lblMinValue = new JLabel("Min Value :");
		GridBagConstraints gbc_lblMinValue = new GridBagConstraints();
		gbc_lblMinValue.anchor = GridBagConstraints.WEST;
		gbc_lblMinValue.insets = new Insets(0, 0, 5, 5);
		gbc_lblMinValue.gridx = 1;
		gbc_lblMinValue.gridy = 5;
		add(lblMinValue, gbc_lblMinValue);

		NumberFormat longFormat = NumberFormat.getIntegerInstance();

		txtMinValue = new JFormattedTextField();
		txtMinValue.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!((c >= '0') && (c <= '9') || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {
					getToolkit().beep();
					e.consume();
				}
			}
		});

		txtMaxValue = new JFormattedTextField();

		txtMaxValue.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char a = e.getKeyChar();
				if (!((a >= '0') && (a <= '9') || (a == KeyEvent.VK_BACK_SPACE) || (a == KeyEvent.VK_DELETE))) {
					getToolkit().beep();
					e.consume();
				}
			}
		});

		/*
		 * txtMinValue.addKeyListener(new KeyAdapter() {
		 * 
		 * @Override public void keyReleased(KeyEvent e) {
		 * 
		 * String val = txtMinValue.getText(); int i = val.length();
		 * if(e.getKeyChar()>='0' && e.getKeyChar()<='9') {
		 * txtMinValue.setEditable(true); } else { JOptionPane.showMessageDialog
		 * (null, "Please, enter numbers only", "", JOptionPane.ERROR_MESSAGE);
		 * //LOGGER.error("Error message: " + e.getMessage()); }
		 * 
		 * 
		 * //JOptionPane.showMessageDialog(this, "Please, enter numbers only");
		 * }
		 * 
		 * });
		 */

		GridBagConstraints gbc_txtMinValue = new GridBagConstraints();
		gbc_txtMinValue.insets = new Insets(0, 0, 5, 5);
		gbc_txtMinValue.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtMinValue.gridx = 3;
		gbc_txtMinValue.gridy = 5;
		add(txtMinValue, gbc_txtMinValue);
		txtMinValue.setColumns(10);

		JLabel lblMaxValue = new JLabel("Max Value :");
		GridBagConstraints gbc_lblMaxValue = new GridBagConstraints();
		gbc_lblMaxValue.anchor = GridBagConstraints.WEST;
		gbc_lblMaxValue.insets = new Insets(0, 0, 5, 5);
		gbc_lblMaxValue.gridx = 1;
		gbc_lblMaxValue.gridy = 6;
		add(lblMaxValue, gbc_lblMaxValue);

		GridBagConstraints gbc_txtMaxValue = new GridBagConstraints();
		gbc_txtMaxValue.insets = new Insets(0, 0, 5, 5);
		gbc_txtMaxValue.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtMaxValue.gridx = 3;
		gbc_txtMaxValue.gridy = 6;
		add(txtMaxValue, gbc_txtMaxValue);
		txtMaxValue.setColumns(10);

		JLabel lblIsProfile = new JLabel("isProfile?");
		GridBagConstraints gbc_lblIsProfile = new GridBagConstraints();
		gbc_lblIsProfile.anchor = GridBagConstraints.WEST;
		gbc_lblIsProfile.insets = new Insets(0, 0, 5, 5);
		gbc_lblIsProfile.gridx = 1;
		gbc_lblIsProfile.gridy = 7;
		add(lblIsProfile, gbc_lblIsProfile);

		JCheckBox cbisProfile = new JCheckBox("");
		GridBagConstraints gbc_cbisProfile = new GridBagConstraints();
		gbc_cbisProfile.anchor = GridBagConstraints.WEST;
		gbc_cbisProfile.insets = new Insets(0, 0, 5, 5);
		gbc_cbisProfile.gridx = 3;
		gbc_cbisProfile.gridy = 7;
		add(cbisProfile, gbc_cbisProfile);

		JLabel lblIsActive = new JLabel("isActive?");
		GridBagConstraints gbc_lblIsActive = new GridBagConstraints();
		gbc_lblIsActive.anchor = GridBagConstraints.WEST;
		gbc_lblIsActive.insets = new Insets(0, 0, 5, 5);
		gbc_lblIsActive.gridx = 1;
		gbc_lblIsActive.gridy = 8;
		add(lblIsActive, gbc_lblIsActive);

		JCheckBox cbisActive = new JCheckBox("");
		GridBagConstraints gbc_cbisActive = new GridBagConstraints();
		gbc_cbisActive.anchor = GridBagConstraints.WEST;
		gbc_cbisActive.insets = new Insets(0, 0, 5, 5);
		gbc_cbisActive.gridx = 3;
		gbc_cbisActive.gridy = 8;
		add(cbisActive, gbc_cbisActive);

		JButton btnSave = new JButton("Save");
		GridBagConstraints gbc_btnSave = new GridBagConstraints();
		gbc_btnSave.anchor = GridBagConstraints.EAST;
		gbc_btnSave.insets = new Insets(0, 0, 5, 5);
		gbc_btnSave.gridx = 4;
		gbc_btnSave.gridy = 9;
		add(btnSave, gbc_btnSave);

		
		
		
		btnSave.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (tstDto == null) {
					tstDto = new TestDefinitionDTO();
				}

				tstDto.setTestName(txtTestName.getText());
				tstDto.setTestCode(txtTestCode.getText());

				if (cbisProfile.isSelected()) {
					tstDto.setProfile(true);
				} else {
					tstDto.setProfile(false);
				}

				if (cbisActive.isSelected()) {
					tstDto.setActive(true);
				} else {
					tstDto.setActive(false);

				}
				int min = Integer.parseInt(txtMinValue.getText());
				int max = Integer.parseInt(txtMaxValue.getText());

				if (min > max || max < min) {
					JOptionPane.showMessageDialog(null,
							"Please, enter consistent values", "",
							JOptionPane.ERROR_MESSAGE);

				}

				else {
					tstDto.setMinValue(min);
					tstDto.setMaxValue(max);

					tstDto.setOrganizationName(String.valueOf(cbOrganization
							.getSelectedItem()));
					
					

//					for (AqBaseEntity testCategoriesDTO : testDtoCbOrganization)
//						if (testCategoriesDTO. == cbCategories
//								.getSelected)
//							tstDto.setTestCategoriesId(testCategoriesDTO
//									.getTestCategoriesId());

					dialog.dispose();
				}

			}
		});

		JButton btnCancel = new JButton("Cancel");
		GridBagConstraints gbc_btnCancel = new GridBagConstraints();
		gbc_btnCancel.insets = new Insets(0, 0, 5, 5);
		gbc_btnCancel.gridx = 5;
		gbc_btnCancel.gridy = 9;
		add(btnCancel, gbc_btnCancel);

		btnCancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				dialog.dispose();
			}
		});

		orgDTO = DBHibernate.getAll(OrganizationDTO.class, new ArrayList(),
				rest);

		for (OrganizationDTO org : orgDTO) {
			cbOrganization.addItem(org.getOrganizationName());
		}

		getAll();

		
		
//		for (TestCategoriesDTO t : tstCatDTO) {
//			cbCategories.addItem(t.getTestCategoriesName());
//		}

		tstDto = testDTO;

		if (testDTO == null) {
			cbCategories.setSelectedIndex(-1);
			cbOrganization.setSelectedIndex(-1);
		}

		else if (testDTO != null) { // edit kýsmýnda verileri getirir.
			txtTestName.setText(tstDto.getTestName());
			txtTestCode.setText(tstDto.getTestCode());
			txtMaxValue.setText(String.valueOf(tstDto.getMaxValue()));
			txtMinValue.setText(String.valueOf(tstDto.getMinValue()));
			
			cbOrganization.setSelectedItem(tstDto.getOrganizationName());
			cbCategories.setSelectedItem(tstDto.getTestCategoriesDTO());
			cbisProfile.setSelected(tstDto.isProfile());
			cbisActive.setSelected(tstDto.isActive());
		}

	}

	private void getAll() {

		this.testDtoCbOrganization = DBHibernate.getAll(TestCategoriesDTO.class,
				new ArrayList(), rest);
		comboboxModel.setValues(testDtoCbOrganization);
		comboboxModel.initialize();
		cbCategories.setModel(comboboxModel);

	}
	

	public JDialog getDialog() {
		return dialog;
	}

	public void setDialog(JDialog dialog) {
		this.dialog = dialog;
	}

	public TestDefinitionDTO getTstDto() {
		return tstDto;
	}

	public void setTstDto(TestDefinitionDTO tstDto) {
		this.tstDto = tstDto;
	}

}
