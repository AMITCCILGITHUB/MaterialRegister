package org.map.hibernate.dao;

import java.util.Arrays;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;
import org.map.hibernate.HibernateUtil;
import org.map.hibernate.ddo.AgencyMaster;
import org.map.hibernate.ddo.CustomerMaster;
import org.map.hibernate.ddo.ItemMaster;
import org.map.hibernate.ddo.LaboratoryMaster;
import org.map.hibernate.ddo.ResultMaster;
import org.map.hibernate.ddo.RoleMaster;
import org.map.hibernate.ddo.SpecificationMaster;
import org.map.hibernate.ddo.TestMaster;

public class ValidationData {

	public static List<String> getValidationTypes() {

		String[] validationTypes = { "Agency", "Customer", "Item",
				"Laboratory", "Result", "Role", "Specificatiin", "Test" };

		return Arrays.asList(validationTypes);
	}

	public static List<RoleMaster> getRoleList() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();

		List<RoleMaster> roleList = session.createCriteria(RoleMaster.class)
				.list();

		transaction.commit();
		session.close();
		return roleList;
	}

	public static List<String> getValidationNameList(String validationType) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();

		List<String> validationNames = null;

		switch (validationType) {
		case "Agency":
			validationNames = session
					.createCriteria(RoleMaster.class)
					.setProjection(
							Projections.distinct(Projections
									.property("agencyName"))).list();
			break;
		case "Customer":
			validationNames = session
					.createCriteria(RoleMaster.class)
					.setProjection(
							Projections.distinct(Projections
									.property("customerName"))).list();
			break;
		case "Item":
			validationNames = session
					.createCriteria(RoleMaster.class)
					.setProjection(
							Projections.distinct(Projections
									.property("itemName"))).list();
			break;
		case "Laboratory":
			validationNames = session
					.createCriteria(RoleMaster.class)
					.setProjection(
							Projections.distinct(Projections
									.property("laboratoryName"))).list();
			break;
		case "Result":
			validationNames = session
					.createCriteria(RoleMaster.class)
					.setProjection(
							Projections.distinct(Projections
									.property("resultName"))).list();
			break;
		case "Role":
			validationNames = session
					.createCriteria(RoleMaster.class)
					.setProjection(
							Projections.distinct(Projections
									.property("roleName"))).list();
			break;
		case "Specification":
			validationNames = session
					.createCriteria(RoleMaster.class)
					.setProjection(
							Projections.distinct(Projections
									.property("specificationName"))).list();
			break;
		case "Test":
			validationNames = session
					.createCriteria(RoleMaster.class)
					.setProjection(
							Projections.distinct(Projections
									.property("testName"))).list();
			break;
		}

		transaction.commit();
		session.close();

		return validationNames;
	}

	public static int getNextValidationNumber(String validationType)
			throws HibernateException {

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();

		int validationNumber = -1;
		switch (validationType) {
		case "Agency":
			validationNumber = (int) session.createCriteria(AgencyMaster.class)
					.setProjection(Projections.max("agencyCode"))
					.uniqueResult() + 1;
			break;
		case "Customer":
			validationNumber = (int) session
					.createCriteria(CustomerMaster.class)
					.setProjection(Projections.max("customerCode"))
					.uniqueResult() + 1;
			break;
		case "Item":
			validationNumber = (int) session.createCriteria(ItemMaster.class)
					.setProjection(Projections.max("itemCode")).uniqueResult() + 1;
			break;
		case "Laboratory":
			validationNumber = (int) session
					.createCriteria(LaboratoryMaster.class)
					.setProjection(Projections.max("laboratoryCode"))
					.uniqueResult() + 1;
			break;
		case "Result":
			validationNumber = (int) session.createCriteria(ResultMaster.class)
					.setProjection(Projections.max("resultCode"))
					.uniqueResult() + 1;
			break;
		case "Role":
			validationNumber = (int) session.createCriteria(RoleMaster.class)
					.setProjection(Projections.max("roleCode")).uniqueResult() + 1;
			break;
		case "Specification":
			validationNumber = (int) session
					.createCriteria(SpecificationMaster.class)
					.setProjection(Projections.max("specificationCode"))
					.uniqueResult() + 1;
			break;
		case "Test":
			validationNumber = (int) session.createCriteria(TestMaster.class)
					.setProjection(Projections.max("testCode")).uniqueResult() + 1;
			break;
		}

		transaction.commit();
		session.close();
		return validationNumber;
	}

	public static void insertAgency(AgencyMaster agency) {

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();

		session.save(agency);

		transaction.commit();
		session.close();
	}

	public static void insertCustomer(CustomerMaster customer) {

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();

		session.save(customer);

		transaction.commit();
		session.close();
	}

	public static void insertItem(ItemMaster item) {

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();

		session.save(item);

		transaction.commit();
		session.close();
	}

	public static void insertLaboratory(LaboratoryMaster lab) {

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();

		session.save(lab);

		transaction.commit();
		session.close();
	}

	public static void insertResult(ResultMaster result) {

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();

		session.save(result);

		transaction.commit();
		session.close();
	}

	public static void insertSpecification(SpecificationMaster spec) {

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();

		session.save(spec);

		transaction.commit();
		session.close();
	}

	public static void insertTest(TestMaster test) {

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();

		session.save(test);

		transaction.commit();
		session.close();
	}

	public static void updateAgency(AgencyMaster agency) {

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();

		session.update(agency);

		transaction.commit();
		session.close();
	}

	public static void updateCustomer(CustomerMaster customer) {

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();

		session.update(customer);

		transaction.commit();
		session.close();
	}

	public static void updateItem(ItemMaster item) {

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();

		session.update(item);

		transaction.commit();
		session.close();
	}

	public static void updateLaboratory(LaboratoryMaster lab) {

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();

		session.update(lab);

		transaction.commit();
		session.close();
	}

	public static void updateResult(ResultMaster result) {

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();

		session.update(result);

		transaction.commit();
		session.close();
	}

	public static void updateSpecification(SpecificationMaster spec) {

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();

		session.update(spec);

		transaction.commit();
		session.close();
	}

	public static void updateTest(TestMaster test) {

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();

		session.update(test);

		transaction.commit();
		session.close();
	}

	public static void deleteAgency(AgencyMaster agency) {

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();

		session.delete(agency);

		transaction.commit();
		session.close();
	}

	public static void deleteCustomer(CustomerMaster customer) {

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();

		session.delete(customer);

		transaction.commit();
		session.close();
	}

	public static void deleteItem(ItemMaster item) {

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();

		session.delete(item);

		transaction.commit();
		session.close();
	}

	public static void deleteLaboratory(LaboratoryMaster lab) {

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();

		session.delete(lab);

		transaction.commit();
		session.close();
	}

	public static void deleteResult(ResultMaster result) {

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();

		session.delete(result);

		transaction.commit();
		session.close();
	}

	public static void deleteSpecification(SpecificationMaster spec) {

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();

		session.delete(spec);

		transaction.commit();
		session.close();
	}

	public static void deleteTest(TestMaster test) {

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();

		session.delete(test);

		transaction.commit();
		session.close();
	}

	public static List<AgencyMaster> getAgencyList() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();

		List<AgencyMaster> agencyList = session.createCriteria(
				AgencyMaster.class).list();

		transaction.commit();
		session.close();

		return agencyList;
	}

	public static List<CustomerMaster> getCustomerList() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();

		List<CustomerMaster> customerList = session.createCriteria(
				CustomerMaster.class).list();

		transaction.commit();
		session.close();

		return customerList;
	}

	public static List<ItemMaster> getItemList() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();

		List<ItemMaster> itemList = session.createCriteria(ItemMaster.class)
				.list();

		transaction.commit();
		session.close();

		return itemList;
	}

	public static List<LaboratoryMaster> getLaboratoryList() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();

		List<LaboratoryMaster> laboratoryList = session.createCriteria(
				LaboratoryMaster.class).list();

		transaction.commit();
		session.close();

		return laboratoryList;
	}

	public static List<ResultMaster> getResultList() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();

		List<ResultMaster> resultList = session.createCriteria(
				ResultMaster.class).list();

		transaction.commit();
		session.close();

		return resultList;
	}

	public static List<SpecificationMaster> getSpecificationList() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();

		List<SpecificationMaster> specificationList = session.createCriteria(
				SpecificationMaster.class).list();

		transaction.commit();
		session.close();

		return specificationList;
	}

	public static List<TestMaster> getTestList() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();

		List<TestMaster> testList = session.createCriteria(TestMaster.class)
				.list();

		transaction.commit();
		session.close();

		return testList;
	}
}
