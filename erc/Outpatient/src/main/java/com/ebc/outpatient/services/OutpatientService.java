package com.ebc.outpatient.services;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.hibernate.SQLQuery;

import com.ebc.core.DBHibernate;
import com.ebc.definitions.admission.entity.AdmissionDTO;
import com.ebc.definitions.patient.model.Patient;
import com.ebc.definitions.staff.entity.Staff;
import com.ebc.outpatient.doctor.OutpatientInfoDTO;

public class OutpatientService {
	
	private static SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

	public static ArrayList<OutpatientInfoDTO> getPatientInfAndAmissionInf(Date date){
		
		String  format =sdf.format(date);
		StringBuilder sql = new StringBuilder(" SELECT  t2.*, t3.*, t4.* FROM AQAPPOINTMENT t1  ");
		sql.append(" LEFT JOIN AQADMISSION t2 ON t2.ADMISSIONID = t1.ADMISSIONID ");
		sql.append(" LEFT JOIN AQPATIENT t3 ON t2.PATIENTID =t3.PATIENTID ");
		sql.append(" LEFT JOIN AQSTAFF t4 ON t1.STAFFID = t4.PRSID ");
		sql.append(" WHERE t1.ADMISSIONID>0 AND to_char(t1.createdAt, 'dd-MM-yyyy') =:requestedDate");
		SQLQuery query = DBHibernate.getSession()
				.createSQLQuery(sql.toString());
		
		query.addEntity("t2", AdmissionDTO.class);//0
		query.addEntity("t3", Patient.class);//1
		query.addEntity("t4", Staff.class);//2
		query.setParameter("requestedDate", format);
		
		ArrayList<Object[]> results = (ArrayList<Object[]>) query.list();
		ArrayList<OutpatientInfoDTO> infoDTOs = new ArrayList<OutpatientInfoDTO>();
		
		for (Object[] objects : results) {
			OutpatientInfoDTO infoDTO = new OutpatientInfoDTO();
			AdmissionDTO admissionDTO = (AdmissionDTO) objects[0];
			Patient patient = (Patient) objects[1];
			Staff staff = (Staff) objects[2];
			
			infoDTO.setAdmissionDTO(admissionDTO);
			infoDTO.setPatientDTO(patient);
			infoDTO.setStaff(staff);
			infoDTO.setProtocolCreated(false);
			infoDTOs.add(infoDTO);
		}
		
		return infoDTOs;
	}


}
