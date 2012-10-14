package org.map.hibernate.dao;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

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
import org.map.hibernate.ddo.MaterialTestMap;
import org.map.hibernate.ddo.MaterialTestMapId;
import org.map.hibernate.ddo.ValidationMaster;

public class MaterialData {

	public static MaterialMaster getMaterialDetails(String ctNumber) {

		MaterialMaster material = new MaterialMaster();

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();

		Iterator it = session.createCriteria(MaterialMaster.class)
				.add(Restrictions.eq("ctNumber", ctNumber)).list().iterator();

		if (it.hasNext()) {
			material = (MaterialMaster) it.next();
			material.getTests().size();
		}

		transaction.commit();
		session.close();
		return material;
	}

	public static String getNextCtNumber(String selectedYear) {

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();

		String ctNumber = (String) session.getNamedQuery("nextCtNumberQuery")
				.setString("year", selectedYear.substring(2)).uniqueResult();

		transaction.commit();
		session.close();

		return ctNumber;
	}

	public static MaterialMaster searchMaterialDetailsByCtNumber(String ctNumber) {

		MaterialMaster material = new MaterialMaster();

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();

		Iterator it = session.createCriteria(MaterialMaster.class)
				.add(Restrictions.eq("ctNumber", ctNumber)).list().iterator();

		if (it.hasNext()) {
			material = (MaterialMaster) it.next();
			material.getTests().size();
		}

		transaction.commit();
		session.close();
		return material;
	}

	public static List<MaterialMaster> getMaterialList() {

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();

		session.enableFilter("printFilter");
		List<MaterialMaster> materials = session
				.createCriteria(MaterialMaster.class)
				.addOrder(
						OrderBySqlFormula
								.sqlFormula("cast(substring(substring_index(Ct_Number, '-', 1), 6) as unsigned) asc"))
				.list();

		Iterator it = materials.iterator();
		while (it.hasNext()) {
			MaterialMaster material = (MaterialMaster) it.next();
			material.getTests().size();
		}
		session.disableFilter("printFilter");

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
		disjunction.add(Restrictions.like("inspectionAgency", searchText,
				MatchMode.ANYWHERE));
		disjunction.add(Restrictions.like("item", searchText,
				MatchMode.ANYWHERE));
		disjunction.add(Restrictions.like("size", searchText,
				MatchMode.ANYWHERE));
		disjunction.add(Restrictions.like("heatNumber", searchText,
				MatchMode.ANYWHERE));
		disjunction.add(Restrictions.like("plateNumber", searchText,
				MatchMode.ANYWHERE));
		disjunction.add(Restrictions.like("specification", searchText,
				MatchMode.ANYWHERE));
		disjunction.add(Restrictions.like("customer", searchText,
				MatchMode.ANYWHERE));
		disjunction.add(Restrictions.like("equipments", searchText,
				MatchMode.ANYWHERE));
		disjunction.add(Restrictions.like("laboratory", searchText,
				MatchMode.ANYWHERE));
		disjunction.add(Restrictions.like("reportNumber", searchText,
				MatchMode.ANYWHERE));
		disjunction.add(Restrictions.like("reportDate", searchText,
				MatchMode.ANYWHERE));
		disjunction.add(Restrictions.like("result", searchText,
				MatchMode.ANYWHERE));
		disjunction.add(Restrictions.like("remarks", searchText,
				MatchMode.ANYWHERE));
		disjunction.add(Restrictions.like("witnessedBy", searchText,
				MatchMode.ANYWHERE));

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
			String ctNumberFrom, String ctNumberTo, boolean filtered)
			throws ParseException {

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

		if (filtered) {
			session.enableFilter("printFilter");
		}

		List<MaterialMaster> materials = qry.list();
		Iterator it = materials.iterator();
		while (it.hasNext()) {
			MaterialMaster material = (MaterialMaster) it.next();
			material.getTests().size();
		}

		if (filtered) {
			session.disableFilter("printFilter");
		}

		transaction.commit();
		session.close();
		return materials;
	}

	public static List<MaterialMaster> searchMaterialDetailsDt(Date fromDate,
			Date toDate, boolean filtered) throws ParseException {

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();

		if (filtered) {
			session.enableFilter("printFilter");
		}

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
		Iterator it = materials.iterator();

		while (it.hasNext()) {
			MaterialMaster material = (MaterialMaster) it.next();
			material.getTests().size();
		}

		if (filtered) {
			session.disableFilter("printFilter");
		}

		transaction.commit();
		session.close();
		return materials;
	}

	public static void insertMaterialTestMap(MaterialMaster material,
			Iterator<ValidationMaster> newListIt,
			Iterator<ValidationMaster> oldListIt) {

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();

		while (newListIt.hasNext()) {
			ValidationMaster vm = (ValidationMaster) newListIt.next();

			MaterialTestMapId id = new MaterialTestMapId(
					material.getCtNumber(), vm.getId().getValidationCode());
			MaterialTestMap map = new MaterialTestMap(id,
					vm.getValidationName(), vm.getValidationValue(), "TRUE",
					"SYSTEM", Calendar.getInstance().getTime());
			material.getTests().add(map);
		}

		while (oldListIt.hasNext()) {
			ValidationMaster vm = (ValidationMaster) oldListIt.next();
			if (vm.getId().getValidationCode() == 0) {
				vm.getId().setValidationCode(
						ValidationData
								.getValidationDetail(
										vm.getId().getValidationType(),
										vm.getValidationName()).getId()
								.getValidationCode());
			}

			MaterialTestMapId id = new MaterialTestMapId(
					material.getCtNumber(), vm.getId().getValidationCode());
			MaterialTestMap map = new MaterialTestMap(id,
					vm.getValidationName(), vm.getValidationValue(), "TRUE",
					"SYSTEM", Calendar.getInstance().getTime());
			material.getTests().add(map);
		}

		material.setTestQuantity(1);
		session.save(material);

		transaction.commit();
		session.close();
	}

	public static void deleteMaterial(MaterialMaster material) {

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();

		session.delete(material);

		transaction.commit();
		session.close();
	}

	public static void updateMaterialTestMap(MaterialMaster material,
			Iterator<ValidationMaster> newListIt) {

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();

		while (newListIt.hasNext()) {
			ValidationMaster vm = (ValidationMaster) newListIt.next();
			if (vm.getId().getValidationCode() == 0) {
				vm.getId().setValidationCode(
						ValidationData
								.getValidationDetail(
										vm.getId().getValidationType(),
										vm.getValidationName()).getId()
								.getValidationCode());
			}
			MaterialTestMapId id = new MaterialTestMapId(
					material.getCtNumber(), vm.getId().getValidationCode());
			MaterialTestMap map = new MaterialTestMap(id,
					vm.getValidationName(), vm.getValidationValue(), "TRUE",
					"SYSTEM", Calendar.getInstance().getTime());
			material.getTests().add(map);
		}

		ArrayList<MaterialTestMap> tests = new ArrayList<>(material.getTests());
		ListIterator<MaterialTestMap> oldListIt = tests.listIterator();
		while (oldListIt.hasNext()) {
			MaterialTestMap map = (MaterialTestMap) oldListIt.next();
			map.getId().setCtNumber(material.getCtNumber());
			oldListIt.set(map);
		}

		material.setTestQuantity(1);
		session.save(material);

		transaction.commit();

		session.refresh(material);
		session.close();

	}
}
