package com.ebc.definitions.entity;

import com.ebc.core.AqBaseEntity;

public class DefinitionDTO extends AqBaseEntity {
		private long dischargeTypeId;
		private String definitionName;
		private boolean definitionStatus;

		public DefinitionDTO() {
		}
		
		public long getDischargeTypeId() {
			return dischargeTypeId;
		}
		public void setDischargeTypeId(long dischargeTypeId) {
			this.dischargeTypeId = dischargeTypeId;
		}
		public String getDefinitionName() {
			return definitionName;
		}
		public void setDefinitionName(String definitionName) {
			this.definitionName = definitionName;
		}
		public boolean isDefinitionStatus() {
			return definitionStatus;
		}
		public void setDefinitionStatus(boolean definitionStatus) {
			this.definitionStatus = definitionStatus;
		}
		
		
}
