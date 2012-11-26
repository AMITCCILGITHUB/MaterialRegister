package org.map.hibernate.dao;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.map.hibernate.HibernateUtil;
import org.map.hibernate.OrderBySqlFormula;
import org.map.hibernate.ddo.MaterialMaster;
import org.map.hibernate.ddo.MaterialTests;

public class MaterialData {

	public static MaterialMaster getMaterialDetails(String ctNumber) {

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();

		MaterialMaster material = (MaterialMaster) session
				.createCriteria(MaterialMaster.class)
				.add(Restrictions.eq("ctNumber", ctNumber)).uniqueResult();

		transaction.commit();
		session.close();
		return material;
	}

	public static int getNextMaterialNumber() throws HibernateException {

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();

		int materialNumber = (int) session.createCriteria(MaterialMaster.class)
				.setProjection(Projections.max("materialCode")).uniqueResult() + 1;

		transaction.commit();
		session.close();
		return materialNumber;
	}

	public static String getNextCtNumber() {

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();

		String ctNumber = (String) session.getNamedQuery("nextCtNumberQuery")
				.setString("year", CodeData.getCurrentYear()).uniqueResult();

		transaction.commit();
		session.close();

		return ctNumber;
	}

	public static int getNextMaterialCode() {

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();

		Object result = session.createCriteria(MaterialMaster.class)
				.setProjection(Projections.max("materialCode")).uniqueResult();

		int materialCode = 1001;
		if (result != null)
			materialCode = (int) result + 1;

		transaction.commit();
		session.close();

		return materialCode;
	}

	public static int getNextTestCode() {

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();

		Object result = session.createCriteria(MaterialTests.class)
				.setProjection(Projections.max("testCode")).uniqueResult();

		int testCode = 1001;
		if (result != null)
			testCode = (int) result + 1;

		transaction.commit();
		session.close();

		return testCode;
	}

	public static void insertMaterial(MaterialMaster material) {

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();

		material.setMaterialCode(getNextMaterialCode());

		if (material.getInspectionAgency().getAgencyCode() <= 1000) {

			ValidationData.insertAgency(material.getInspectionAgency());
		}

		if (material.getSpecification().getSpecificationCode() <= 1000) {

			ValidationData.insertSpecification(material.getSpecification());
		}

		if (material.getItem().getItemCode() <= 1000) {

			ValidationData.insertItem(material.getItem());
		}

		int nextTestCode = getNextTestCode();
		for (MaterialTests materialTest : material.getMaterialTests()) {

			if (materialTest.getTestCode() <= 1000) {

				materialTest.setTestCode(nextTestCode);
				materialTest.setMaterialMaster(material);

				if (materialTest.getTest().getTestCode() <= 1000) {

					ValidationData.insertTest(materialTest.getTest());
				}

				if (materialTest.getCustomer().getCustomerCode() <= 1000) {

					ValidationData.insertCustomer(materialTest.getCustomer());
				}

				if (materialTest.getLaboratory().getLaboratoryCode() <= 1000) {

					ValidationData.insertLaboratory(materialTest
							.getLaboratory());
				}

				if (materialTest.getResult().getResultCode() <= 1000) {

					ValidationData.insertResult(materialTest.getResult());
				}

				nextTestCode++;
			}
		}

		session.save(material);

		transaction.commit();
		session.close();
	}

	public static void updateMaterial(MaterialMaster material) {

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();

		if (material.getInspectionAgency().getAgencyCode() <= 1000) {

			ValidationData.insertAgency(material.getInspectionAgency());
		}

		if (material.getSpecification().getSpecificationCode() <= 1000) {

			ValidationData.insertSpecification(material.getSpecification());
		}

		if (material.getItem().getItemCode() <= 1000) {

			ValidationData.insertItem(material.getItem());
		}

		int nextTestCode = getNextTestCode();
		for (MaterialTests materialTest : material.getMaterialTests()) {

			if (materialTest.getTestCode() == 0) {

				materialTest.setTestCode(nextTestCode);
				materialTest.setMaterialMaster(material);

				if (materialTest.getTest().getTestCode() <= 1000) {

					ValidationData.insertTest(materialTest.getTest());
				}

				if (materialTest.getCustomer().getCustomerCode() <= 1000) {

					ValidationData.insertCustomer(materialTest.getCustomer());
				}

				if (materialTest.getLaboratory().getLaboratoryCode() <= 1000) {

					ValidationData.insertLaboratory(materialTest
							.getLaboratory());
				}

				if (materialTest.getResult().getResultCode() <= 1000) {

					ValidationData.insertResult(materialTest.getResult());
				}

				nextTestCode++;
			}
		}

		session.update(material);

		transaction.commit();
		session.close();
	}

	public static MaterialMaster searchMaterialDetailsByCtNumber(String ctNumber) {

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();

		MaterialMaster material = (MaterialMaster) session
				.createCriteria(MaterialMaster.class)
				.add(Restrictions.eq("ctNumber", ctNumber)).uniqueResult();

		transaction.commit();
		session.close();
		return material;
	}

	public static List<MaterialMaster> getMaterialList() {

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();

		List<MaterialMaster> materials = session
				.createCriteria(MaterialMaster.class)
				.addOrder(
						OrderBySqlFormula
								.sqlFormula("cast(substring(substring_index(Ct_Number, '-', 1), 6) as unsigned) asc"))
				.list();

		transaction.commit();
		session.close();
		return materials;
	}

	public static List<String> searchMaterialDetails(String searchText) {

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();

		searchText = "%" + searchText + "%";
		Disjunction disjunction = Restrictions.disjunction();
		disjunction.add(Restrictions.like("ctNumber", searchText,
				MatchMode.ANYWHERE));
		/*
		 * disjunction.add(Restrictions.like("inspectionAgency.agencyName",
		 * searchText, MatchMode.ANYWHERE));
		 * disjunction.add(Restrictions.like("item.itemName", searchText,
		 * MatchMode.ANYWHERE));
		 */
		disjunction.add(Restrictions.like("size", searchText,
				MatchMode.ANYWHERE));
		disjunction.add(Restrictions.like("heatNumber", searchText,
				MatchMode.ANYWHERE));
		disjunction.add(Restrictions.like("plateNumber", searchText,
				MatchMode.ANYWHERE));
		/*
		 * disjunction.add(Restrictions.like("specification.specificationName",
		 * searchText, MatchMode.ANYWHERE));
		 * disjunction.add(Restrictions.like("materialTests.test", searchText,
		 * MatchMode.ANYWHERE));
		 * disjunction.add(Restrictions.like("materialTests.customer",
		 * searchText, MatchMode.ANYWHERE));
		 * disjunction.add(Restrictions.like("materialTests.equipments",
		 * searchText, MatchMode.ANYWHERE));
		 * disjunction.add(Restrictions.like("materialTests.laboratory",
		 * searchText, MatchMode.ANYWHERE));
		 * disjunction.add(Restrictions.like("materialTests.reportNumber",
		 * searchText, MatchMode.ANYWHERE));
		 * disjunction.add(Restrictions.like("materialTests.reportDate",
		 * searchText, MatchMode.ANYWHERE));
		 * disjunction.add(Restrictions.like("materialTests.result", searchText,
		 * MatchMode.ANYWHERE));
		 * disjunction.add(Restrictions.like("materialTests.remarks",
		 * searchText, MatchMode.ANYWHERE));
		 * disjunction.add(Restrictions.like("materialTests.witnessedBy",
		 * searchText, MatchMode.ANYWHERE));
		 * disjunction.add(Restrictions.like("materialTests.failureReason",
		 * searchText, MatchMode.ANYWHERE));
		 */

		List<String> ctNumbers = session
				.createCriteria(MaterialMaster.class)
				.add(disjunction)
				.setProjection(
						Projections.distinct(Projections.property("ctNumber")))
				.list();

		transaction.commit();
		session.close();

		return ctNumbers;
	}

	public static List<MaterialMaster> searchMaterialDetailsCt(
			String ctNumberFrom, String ctNumberTo) throws ParseException {

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();

		Query qry = null;
		if (ctNumberTo == null || ctNumberTo.trim().length() == 0) {
			qry = session.getNamedQuery("searchCtNumberQuerySingle");
			qry.setParameter("fromCtNumber", ctNumberFrom);
		} else {
			qry = session.getNamedQuery("searchCtNumberQuery");
			qry.setParameter("fromCtNumber", ctNumberFrom);
			qry.setParameter("toCtNumber", ctNumberTo);
		}

		List<MaterialMaster> materials = qry.list();

		transaction.commit();
		session.close();
		return materials;
	}

	public static List<MaterialMaster> searchMaterialDetailsDt(Date fromDate,
			Date toDate) throws ParseException {

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();

		List<MaterialMaster> materials = null;
		if (toDate == null) {
			materials = session
					.createCriteria(MaterialMaster.class)
					.add(Restrictions.ge("createdDate", fromDate))
					.addOrder(
							OrderBySqlFormula
									.sqlFormula("cast(substring(substring_index(Ct_Number, '-', 1), 6) as unsigned) asc"))
					.list();

		} else {
			Calendar cal = Calendar.getInstance();
			cal.setTime(toDate);
			cal.add(Calendar.DATE, 1);
			materials = session
					.createCriteria(MaterialMaster.class)
					.add(Restrictions.between("createdDate", fromDate,
							cal.getTime()))
					.addOrder(
							OrderBySqlFormula
									.sqlFormula("cast(substring(substring_index(Ct_Number, '-', 1), 6) as unsigned) asc"))
					.list();
		}

		transaction.commit();
		session.close();
		return materials;
	}

	public static void deleteMaterial(MaterialMaster material) {

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();

		session.delete(material);

		transaction.commit();
		session.close();
	}

}
