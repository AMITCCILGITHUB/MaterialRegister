package org.map.hibernate.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.map.hibernate.HibernateUtil;
import org.map.hibernate.ddo.CodeMaster;

public class CodeData {

	public static List<CodeMaster> getCodes() {

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();

		List<CodeMaster> codes = session.createCriteria(CodeMaster.class)
				.list();

		transaction.commit();
		session.close();

		return codes;
	}

	public static List<CodeMaster> getCodes(String codeName) {

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();

		List<CodeMaster> codes = session
				.createCriteria(CodeMaster.class)
				.add(Restrictions
						.like("codeName", codeName, MatchMode.ANYWHERE)).list();

		transaction.commit();
		session.close();

		return codes;
	}

	public static int getNextCodeNumber() throws HibernateException {

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();

		Object result = session.createCriteria(CodeMaster.class)
				.setProjection(Projections.max("codeNumber")).uniqueResult();

		int codeNumber = 1001;
		if (result != null)
			codeNumber = (int) result + 1;

		transaction.commit();
		session.close();

		return codeNumber;
	}

	public static CodeMaster getCode(String codeName) {

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();

		CodeMaster code = (CodeMaster) session.createCriteria(CodeMaster.class)
				.add(Restrictions.like("codeName", codeName)).uniqueResult();

		transaction.commit();
		session.close();

		return code;
	}

	public static void insertCode(CodeMaster code) {

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();

		code.setCodeNumber(getNextCodeNumber());
		session.save(code);

		transaction.commit();
		session.close();
	}

	public static void updateCode(CodeMaster code) {

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();

		session.update(code);

		transaction.commit();
		session.close();
	}

	public static void deleteCode(CodeMaster code) {

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();

		session.delete(code);

		transaction.commit();
		session.close();
	}

	public static List<String> getCodeNameList() {

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();

		List<String> codeNames = session
				.createCriteria(CodeMaster.class)
				.setProjection(
						Projections.distinct(Projections.property("codeName")))
				.list();

		transaction.commit();
		session.close();
		return codeNames;
	}

	public static String getCurrentYear() {

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();

		String currentYear = (String) session
				.createCriteria(CodeMaster.class)
				.add(Restrictions.like("codeName", "Default_Year"))
				.setProjection(
						Projections.distinct(Projections.property("codeValue")))
				.uniqueResult();

		transaction.commit();
		session.close();

		return currentYear.substring(2);
	}
}
