package com.ebc.admission.appointment.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.hibernate.SQLQuery;
import org.hibernate.Session;

import com.ebc.admission.appointment.entity.AppointmentDto;
import com.ebc.core.DBHibernate;
import com.ebc.definitions.admission.entity.AdmissionDTO;
import com.ebc.definitions.organization.entity.OrganizationDTO;
import com.ebc.definitions.patient.model.Patient;
import com.ebc.definitions.staff.entity.Staff;

public class AppointmentService {

	private static SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
	static Session session;
	
	public static Session getSession(){
		return session = DBHibernate.getSession();
	}


	public static ArrayList<AppointmentDto> getAllAppointment(Date date) {
		String  format =sdf.format(date);
		
		StringBuilder sql = new StringBuilder("SELECT t1.*, t2.*,t3.*,t4.* FROM AQAPPOINTMENT t1 ");
		sql.append("LEFT JOIN AQORGANIZATION t2 ON t1.ORGANIZATIONID=t2.ORGANIZATIONID  ");
		sql.append("JOIN AQSTAFF t3 ON t3.PRSID=t1.STAFFID ");
		sql.append("JOIN AQPATIENT t4 ON t4.PATIENTID=t1.PATIENTID WHERE to_char(t1.createdAt, 'dd-MM-yyyy') =:requestedDate ");
		sql.append("order by t1.createdAt desc");
	 
		SQLQuery query = getSession().createSQLQuery(sql.toString());
		query.addEntity("t1", AppointmentDto.class);
		query.addEntity("t2", OrganizationDTO.class);
		query.addEntity("t3", Staff.class);
		query.addEntity("t4", Patient.class);
		query.setParameter("requestedDate", format); 
		ArrayList<Object[]> results = (ArrayList<Object[]>) query.list();
		ArrayList<AppointmentDto> appointmentDtos = new ArrayList();

		for (Object[] objects : results) {
			AppointmentDto appointmentDto = (AppointmentDto) objects[0];
			OrganizationDTO organizationDTO = (OrganizationDTO) objects[1];
			Staff staff = (Staff) objects[2];
			Patient patient = (Patient) objects[3];
			appointmentDto.setOrganizationDTO(organizationDTO);
			appointmentDto.setStaff(staff);
			appointmentDto.setPatient(patient);
			appointmentDtos.add(appointmentDto);
		}

		return appointmentDtos;
	}
	
	public static ArrayList<AppointmentDto> getAllAppointmentbyAdmissionId(Date date) {
		String  format =sdf.format(date);
		
		StringBuilder sql = new StringBuilder("SELECT t1.*, t2.*,t3.*,t4.* FROM AQAPPOINTMENT t1 ");
		sql.append("LEFT JOIN AQORGANIZATION t2 ON t1.ORGANIZATIONID=t2.ORGANIZATIONID  ");
		sql.append("JOIN AQSTAFF t3 ON t3.PRSID=t1.STAFFID ");
		sql.append("JOIN AQPATIENT t4 ON t4.PATIENTID=t1.PATIENTID WHERE to_char(t1.createdAt, 'dd-MM-yyyy') =:requestedDate ");
		sql.append("AND ADMISSIONID>0");
		sql.append("order by t1.createdAt desc");
	 

		SQLQuery query = getSession().createSQLQuery(sql.toString());
		query.addEntity("t1", AppointmentDto.class);
		query.addEntity("t2", OrganizationDTO.class);
		query.addEntity("t3", Staff.class);
		query.addEntity("t4", Patient.class);
		query.setParameter("requestedDate", format);
		// query.setParameter("admissionId", admissionId);
		 
		ArrayList<Object[]> results = (ArrayList<Object[]>) query.list();
		ArrayList<AppointmentDto> appointmentDtos = new ArrayList();

		for (Object[] objects : results) {
			AppointmentDto appointmentDto = (AppointmentDto) objects[0];
			OrganizationDTO organizationDTO = (OrganizationDTO) objects[1];
			Staff staff = (Staff) objects[2];
			Patient patient = (Patient) objects[3];
			appointmentDto.setOrganizationDTO(organizationDTO);
			appointmentDto.setStaff(staff);
			appointmentDto.setPatient(patient);
			appointmentDtos.add(appointmentDto);
		}

		return appointmentDtos;
	}

	public static ArrayList<AdmissionDTO> getAllAdmissionByPatientid(int patientId) {
		
		StringBuilder sql = new StringBuilder("  SELECT t1.*, t2.*,t3.*,t4.* FROM AQADMISSION t1 ");
		sql.append("LEFT JOIN AQORGANIZATION t2 ON t1.ORGANIZATIONID=t2.ORGANIZATIONID ");
		sql.append("JOIN AQSTAFF t3 ON t3.PRSID=t1.STAFFID ");
		sql.append("JOIN AQPATIENT t4 ON t4.PATIENTID=t1.PATIENTID ");
		sql.append("WHERE t1.PATIENTID =:patientId AND t1.ADMISSIONSTATUS =:ADMISSIONSTATUS ORDER BY t1.ADMISSIONID DESC");

		SQLQuery query = getSession().createSQLQuery(sql.toString());
		query.addEntity("t1", AdmissionDTO.class);//0
		query.addEntity("t2", OrganizationDTO.class);//1
		query.addEntity("t3", Staff.class);//2
		query.addEntity("t4", Patient.class);//3
		query.setParameter("patientId", patientId);
		query.setParameter("ADMISSIONSTATUS", 0);
		ArrayList<Object[]> results = (ArrayList<Object[]>) query.list();
		ArrayList<AdmissionDTO> admissionDTOs = new ArrayList();

		for (Object[] objects : results) {
			AdmissionDTO admissionDTO = (AdmissionDTO) objects[0];
			OrganizationDTO organizationDTO = (OrganizationDTO) objects[1];
			Staff staff = (Staff) objects[2];
			Patient patient = (Patient) objects[3];
			admissionDTO.setOrganizationDTO(organizationDTO);
			admissionDTO.setStaff(staff);
			admissionDTO.setPatient(patient);
			admissionDTOs.add(admissionDTO);
		}

		return admissionDTOs;
	}
	
}
