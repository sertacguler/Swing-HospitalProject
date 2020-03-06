package com.ebc.definitions.combobox;

import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;

import com.ebc.core.AqBaseEntity;

public class ComboboxModel extends DefaultComboBoxModel<AqBaseEntity> {

	private static final long serialVersionUID = 1L;
	private ArrayList<AqBaseEntity> values = new ArrayList<AqBaseEntity>();

	public ArrayList<AqBaseEntity> getValues() {
		return values;
	}

	public void setValues(ArrayList<AqBaseEntity> values) {
		this.values = values;
	}

	public void initialize() {
		removeAllElements();
		for (AqBaseEntity item : values) {

			addElement(item);
		}
	}

	@Override
	public String toString() {
		return super.toString();
	}

	public void setSelectedItemByIndex(int index) {
		setSelectedItem(values.get(index));
	}

	public void setSelectedItemByIndex(Long index) {
		setSelectedItem(values.get(Math.toIntExact(index)));
	}

	public void setSelectedItemByIndex(String fromValueType) {
		int index = Integer.parseInt(fromValueType);
		setSelectedItem(values.get(index));
	}

	public void setSelectedItemByKeyValue(String keyValue) {
		if (keyValue != null) {
			List<AqBaseEntity> allItems = getValues();
			for (AqBaseEntity acKeyValue : allItems) {
				if (acKeyValue.equals(keyValue)) {
					setSelectedItem(acKeyValue);
					break;
				}
			}
		}
	}
}
