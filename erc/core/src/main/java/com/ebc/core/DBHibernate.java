package com.ebc.core;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ArrayList;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Criteria;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.SimpleExpression;



public class DBHibernate {

	private static Session session = null;

	public static Session getSession() {

		if (session != null)
			return session;
		SessionFactory sessionFactory = new Configuration().configure()
				.buildSessionFactory();

		session = sessionFactory.openSession();

		return session;

	}
	
	public static <T> ArrayList<AqBaseEntity> getAll(Class<T> class1, ArrayList<String> columnList, SimpleExpression expression){
		
		try {
			Criteria main = getSession().createCriteria(class1);
			if(expression!=null){
				main.add(expression);
			}
			for (String column : columnList) {
				main.addOrder(Order.asc(column));
			}
			return  (ArrayList<AqBaseEntity>) main.list();
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<>();
		}
	}
	
	public static <T> AqBaseEntity findById(Class<T> class1, int id){
		session = getSession();
		session.beginTransaction();
		AqBaseEntity aqBaseEntity = (AqBaseEntity) session.get(class1, id);
		session.getTransaction().commit();
		return aqBaseEntity;
	}
	
	public static <T> AqBaseEntity findLastOne(Class<T> class1, ArrayList<String> columnList, SimpleExpression expression){
		try {
			Criteria main = getSession().createCriteria(class1);
			if(expression!=null){
				main.add(expression);
			}
			for (String column : columnList) {
				main.addOrder(Order.desc(column));
			}
			main.setFirstResult(0);
			main.setMaxResults(1);
			ArrayList<AqBaseEntity> aqBaseEntities = (ArrayList<AqBaseEntity>) main.list();
			return aqBaseEntities.get(0);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}
	public static AqBaseEntity save(AqBaseEntity entity) throws SQLException {

		session = getSession();
		session.beginTransaction();
		session.save(entity);
		session.getTransaction().commit();
		return entity;
	}

	public static void delete(AqBaseEntity entity) {

		session = getSession();
		session.beginTransaction();
		session.delete(entity);
		session.getTransaction().commit();

	}

	public static void update(AqBaseEntity entity) {

		session = getSession();
		session.beginTransaction();
		session.update(entity);
		session.getTransaction().commit();

	}
	
 
	public static void closeSession() {
		getSession().close();
	}

	
}
