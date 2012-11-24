package org.map.hibernate.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.map.hibernate.HibernateUtil;
import org.map.hibernate.ddo.CodeMaster;
import org.map.hibernate.ddo.UserMaster;

public class UserData {

	public static int getNextUserCode() throws HibernateException {

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();

		Object result = session.createCriteria(UserMaster.class)
				.setProjection(Projections.max("userCode")).uniqueResult();

		int userCode = 1001;
		if(result != null)
			userCode = (int) result + 1;
		
		transaction.commit();
		session.close();

		return userCode;
	}
	
	public static boolean validateUser(UserMaster user) {

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();

		int rowCount = session.createCriteria(UserMaster.class)
				.add(Restrictions.eq("userName", user.getUserName()))
				.add(Restrictions.eq("password", user.getPassword())).list()
				.size();

		transaction.commit();
		session.close();

		return ((rowCount > 0) ? true : false);
	}

	public static UserMaster getUserDetails(String userName) {

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();

		UserMaster um = (UserMaster) session.createCriteria(UserMaster.class)
		.add(Restrictions.eq("userName", userName)).uniqueResult();
		
		transaction.commit();
		session.close();
		
		return um;
	}

	public static UserMaster getUserDetails(int userCode) {

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();

		UserMaster um = (UserMaster) session.get(UserMaster.class, userCode);

		transaction.commit();
		session.close();
		return um;
	}
	
	public static List<UserMaster> getUserList() {

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();

		List<UserMaster> users = session.createCriteria(UserMaster.class)
				.list();

		transaction.commit();
		session.close();
		return users;
	}

	public static void insertUser(UserMaster user) {

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();

		session.save(user);

		transaction.commit();
		session.close();
	}

	public static void updateUser(UserMaster user) {

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();

		session.update(user);

		transaction.commit();
		session.close();
	}
	
	public static void deleteUser(UserMaster user) {

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();

		session.delete(user);

		transaction.commit();
		session.close();
	}
}
