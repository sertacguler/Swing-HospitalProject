package com.ebc.definitions;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import com.ebc.definitions.entity.DefinitionDTO;

public class DefinitionsTableModel extends AbstractTableModel {
	private ArrayList<DefinitionDTO> definition = new ArrayList<>();
	public String[] columns = {"Discharge Type Name"};
	
	
	
	
	
	
	public ArrayList<DefinitionDTO> getDefinition() {
		return definition;
	}




	public void setDefinition(ArrayList<DefinitionDTO> definition) {
		this.definition = definition;
	}




	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return 1;
	}

	public ArrayList<DefinitionDTO> getAllDatas() {
		return definition;
	}
	
	
	
	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return definition==null ? 0 : definition.size() ;
	}
	
	
	
	@Override
	public String getColumnName(int column) {
		// TODO Auto-generated method stub
		return columns[column];
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		DefinitionDTO dto = definition.get(rowIndex);
		
		
		if(columnIndex==0) {
			return dto.getDefinitionName();
		}
		return null;
	}
	
	
}
