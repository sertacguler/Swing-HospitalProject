package com.ebc.outpatient.doctor;

import com.ebc.definitions.admission.entity.AdmissionDTO;
import com.ebc.definitions.patient.model.Patient;
import com.ebc.definitions.staff.entity.Staff;

public class OutpatientInfoDTO {

	private Patient patientDTO;
	private AdmissionDTO admissionDTO;
	private Staff staff;
	private boolean protocolCreated;
	
	public boolean isProtocolCreated() {
		return protocolCreated;
	}

	public void setProtocolCreated(boolean protocolCreated) {
		this.protocolCreated = protocolCreated;
	}

	public Staff getStaff() {
		return staff;
	}

	public void setStaff(Staff staff) {
		this.staff = staff;
	}

	public Patient getPatientDTO() {
		return patientDTO;
	}

	public void setPatientDTO(Patient patientDTO) {
		this.patientDTO = patientDTO;
	}

	public AdmissionDTO getAdmissionDTO() {
		return admissionDTO;
	}

	public void setAdmissionDTO(AdmissionDTO admissionDTO) {
		this.admissionDTO = admissionDTO;
	}

}
