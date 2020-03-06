package com.ebc.definitions.test.table;

import javax.swing.JPanel;

import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.JTable;

import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.SimpleExpression;

import com.ebc.core.DBHibernate;
import com.ebc.definitions.entity.TestCategoriesDTO;
import com.ebc.definitions.entity.TestDefinitionDTO;
import com.ebc.deginitions.test.categories.TestCategoriesTableModel;

import java.awt.Color;
import java.awt.SystemColor;
import java.util.ArrayList;

import javax.swing.JToolBar;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class TestTablePanel extends JPanel {
	private ArrayList<TestCategoriesDTO> tstCategoriesDTO = new ArrayList<TestCategoriesDTO>();
	private ArrayList<TestDefinitionDTO> testDefinitionList = new ArrayList<TestDefinitionDTO>();
	private TestCategoriesTableModel categoriesTableModel = new TestCategoriesTableModel();
	private TestTableModel testDefinitionTableModel = new TestTableModel();
	private JTable categoryTable = new JTable();;
	private JTable testDefinitionTable;

	public TestTablePanel() {
		setBackground(SystemColor.activeCaption);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 0.0, 1.0, 0.0, 0.0,
				1.0, 0.0, 0.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 1.0, 0.0,
				0.0, 0.0, Double.MIN_VALUE };
		setLayout(gridBagLayout);

		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridheight = 5;
		gbc_scrollPane.gridwidth = 3;
		gbc_scrollPane.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 1;
		gbc_scrollPane.gridy = 2;
		add(scrollPane, gbc_scrollPane);
		categoryTable.setBackground(new Color(255, 255, 255));

		categoryTable.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		categoryTable.setRowHeight(45);
		categoryTable.setForeground(new Color(0, 0, 51));
		scrollPane.setColumnHeaderView(categoryTable);
		scrollPane.setViewportView(categoryTable);
		SimpleExpression rest = Restrictions.eq("status", true);
		tstCategoriesDTO = DBHibernate.getAll(TestCategoriesDTO.class,
				new ArrayList(), rest);
		categoriesTableModel.setTstCategories(tstCategoriesDTO);
		categoriesTableModel.fireTableDataChanged();
		categoryTable.setModel(categoriesTableModel);

		JScrollPane scrollPane_1 = new JScrollPane();
		GridBagConstraints gbc_scrollPane_1 = new GridBagConstraints();
		gbc_scrollPane_1.gridwidth = 2;
		gbc_scrollPane_1.gridheight = 5;
		gbc_scrollPane_1.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane_1.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_1.gridx = 5;
		gbc_scrollPane_1.gridy = 2;
		add(scrollPane_1, gbc_scrollPane_1);

		testDefinitionTable = new JTable();
		testDefinitionTable.setRowHeight(45);
		testDefinitionTable.setBackground(new Color(255, 255, 255));
		testDefinitionTable
				.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		testDefinitionTable.setForeground(new Color(0, 0, 51));
		scrollPane_1.setColumnHeaderView(testDefinitionTable);
		scrollPane_1.setViewportView(testDefinitionTable);

		testDefinitionList = DBHibernate.getAll(TestDefinitionDTO.class,
				new ArrayList(), rest);
		// testDefinitionTableModel.setTstDef(testDefinitionList);
		// testDefinitionTableModel.fireTableDataChanged();

		testDefinitionTable.setModel(testDefinitionTableModel);

		categoryTable.getSelectionModel().addListSelectionListener(
				new ListSelectionListener() {

					@Override
					public void valueChanged(ListSelectionEvent e) {

						int selectedRow = categoryTable.getSelectedRow();

						if (selectedRow != -1) {
							TestCategoriesDTO testCategory = categoriesTableModel
									.getTstCategories().get(selectedRow);
							int categoryId = testCategory.getTestCategoriesId();

							ArrayList<TestDefinitionDTO> testList = new ArrayList<>();
							for (TestDefinitionDTO testDefinitionDTO : testDefinitionList) {
								if (categoryId == testDefinitionDTO
										.getTestCategoriesId()) {
									testList.add(testDefinitionDTO);
								}
							}

							testDefinitionTableModel.setTstDef(testList);
							testDefinitionTableModel.fireTableDataChanged();

						}

					}
				});
	}

}
