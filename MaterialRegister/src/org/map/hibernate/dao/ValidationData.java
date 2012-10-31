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

	public static List<String> getRoleList() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();

		List<String> roleTypes = session
				.createCriteria(RoleMaster.class)
				.setProjection(
						Projections.distinct(Projections.property("roleName")))
				.list();

		transaction.commit();
		session.close();
		return roleTypes;
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
							Projections.distinct(Projections.property("agencyName")))
					.list();
			break;
		case "Customer":
			validationNames = session
			.createCriteria(RoleMaster.class)
			.setProjection(
					Projections.distinct(Projections.property("customerName")))
			.list();
			break;
		case "Item":
			validationNames = session
			.createCriteria(RoleMaster.class)
			.setProjection(
					Projections.distinct(Projections.property("itemName")))
			.list();
			break;
		case "Laboratory":
			validationNames = session
			.createCriteria(RoleMaster.class)
			.setProjection(
					Projections.distinct(Projections.property("laboratoryName")))
			.list();
			break;
		case "Result":
			validationNames = session
			.createCriteria(RoleMaster.class)
			.setProjection(
					Projections.distinct(Projections.property("resultName")))
			.list();
			break;
		case "Role":
			validationNames = session
			.createCriteria(RoleMaster.class)
			.setProjection(
					Projections.distinct(Projections.property("roleName")))
			.list();
			break;
		case "Specification":
			validationNames = session
			.createCriteria(RoleMaster.class)
			.setProjection(
					Projections.distinct(Projections.property("specificationName")))
			.list();
			break;
		case "Test":
			validationNames = session
			.createCriteria(RoleMaster.class)
			.setProjection(
					Projections.distinct(Projections.property("testName")))
			.list();
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
			validationNumber = 1001 + ((Long) session
					.createCriteria(AgencyMaster.class)
					.setProjection(Projections.rowCount()).uniqueResult())
					.intValue();
			break;
		case "Customer":
			validationNumber = 1001 + ((Long) session
					.createCriteria(CustomerMaster.class)
					.setProjection(Projections.rowCount()).uniqueResult())
					.intValue();
			break;
		case "Item":
			validationNumber = 1001 + ((Long) session
					.createCriteria(ItemMaster.class)
					.setProjection(Projections.rowCount()).uniqueResult())
					.intValue();
			break;
		case "Laboratory":
			validationNumber = 1001 + ((Long) session
					.createCriteria(LaboratoryMaster.class)
					.setProjection(Projections.rowCount()).uniqueResult())
					.intValue();
			break;
		case "Result":
			validationNumber = 1001 + ((Long) session
					.createCriteria(ResultMaster.class)
					.setProjection(Projections.rowCount()).uniqueResult())
					.intValue();
			break;
		case "Role":
			validationNumber = 1001 + ((Long) session
					.createCriteria(RoleMaster.class)
					.setProjection(Projections.rowCount()).uniqueResult())
					.intValue();
			break;
		case "Specification":
			validationNumber = 1001 + ((Long) session
					.createCriteria(SpecificationMaster.class)
					.setProjection(Projections.rowCount()).uniqueResult())
					.intValue();
			break;
		case "Test":
			validationNumber = 1001 + ((Long) session
					.createCriteria(TestMaster.class)
					.setProjection(Projections.rowCount()).uniqueResult())
					.intValue();
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
	
	public static List<AgencyMaster> getAgencyList() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();

		List<AgencyMaster> agencyList = session
				.createCriteria(AgencyMaster.class)
				.list();

		transaction.commit();
		session.close();
		
		return agencyList;
	}
	
	public static List<CustomerMaster> getCustomerList() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();

		List<CustomerMaster> customerList = session
				.createCriteria(CustomerMaster.class)
				.list();

		transaction.commit();
		session.close();
		
		return customerList;
	}
	
	public static List<ItemMaster> getItemList() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();

		List<ItemMaster> itemList = session
				.createCriteria(ItemMaster.class)
				.list();

		transaction.commit();
		session.close();
		
		return itemList;
	}
	
	public static List<LaboratoryMaster> getLaboratoryList() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();

		List<LaboratoryMaster> laboratoryList = session
				.createCriteria(LaboratoryMaster.class)
				.list();

		transaction.commit();
		session.close();
		
		return laboratoryList;
	}
	
	public static List<ResultMaster> getResultList() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();

		List<ResultMaster> resultList = session
				.createCriteria(ResultMaster.class)
				.list();

		transaction.commit();
		session.close();
		
		return resultList;
	}
	
	public static List<SpecificationMaster> getSpecificationList() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();

		List<SpecificationMaster> specificationList = session
				.createCriteria(SpecificationMaster.class)
				.list();

		transaction.commit();
		session.close();
		
		return specificationList;
	}
	
	public static List<TestMaster> getTestList() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();

		List<TestMaster> testList = session
				.createCriteria(TestMaster.class)
				.list();

		transaction.commit();
		session.close();
		
		return testList;
	}
	
	/*
	public static List<String> getValidationName(String validationType) {

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();

		List<String> validationTypes = session
				.createCriteria(ValidationMaster.class)
				.add(Restrictions.eq("id.validationType", validationType))
				.setProjection(
						Projections.distinct(Projections
								.property("validationName"))).list();

		transaction.commit();
		session.close();
		return validationTypes;
	}

	public static List<ValidationMaster> getValidationDetails(
			String validationType) {

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();

		List<ValidationMaster> validationTypes = session
				.createCriteria(ValidationMaster.class)
				.add(Restrictions.eq("id.validationType", validationType))
				.addOrder(
						OrderBySqlFormula
								.sqlFormula("cast(Validation_Code as unsigned) asc"))
				.list();

		transaction.commit();
		session.close();
		return validationTypes;
	}

	public static List<ValidationMaster> searchValidationDetails(
			String validationType, String validationName) {

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();

		List<ValidationMaster> validationTypes = session
				.createCriteria(ValidationMaster.class)
				.add(Restrictions.eq("id.validationType", validationType))
				.add(Restrictions.like("validationName", "%" + validationName
						+ "%"))
				.addOrder(
						OrderBySqlFormula
								.sqlFormula("cast(Validation_Code as unsigned) asc"))
				.list();

		transaction.commit();
		session.close();
		return validationTypes;
	}

	public static ValidationMaster getValidationDetail(String validationType,
			String validationName) {

		ValidationMaster vm = new ValidationMaster(validationType);

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();

		Iterator it = session.createCriteria(ValidationMaster.class)
				.add(Restrictions.eq("id.validationType", validationType))
				.add(Restrictions.eq("validationName", validationName)).list()
				.iterator();

		if (it.hasNext()) {
			vm = (ValidationMaster) it.next();
		}

		transaction.commit();
		session.close();
		return vm;
	}

	public static void insertValidationTypes(Iterator<ValidationMaster> vListIt) {

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();

		while (vListIt.hasNext()) {
			ValidationMaster vm = (ValidationMaster) vListIt.next();
			session.save(vm);
		}

		transaction.commit();
		session.close();
	}

	public static void insertValidationTypes(ValidationMaster vm) {

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();

		session.save(vm);

		transaction.commit();
		session.close();
	}

	public static void updateValidationTypes(ValidationMaster vm) {

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();

		session.update(vm);

		transaction.commit();
		session.close();
	}

	public static int getNextValidationNumber(String validationType)
			throws HibernateException {

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();

		int validationNumber = 1001 + ((Long) session
				.createCriteria(ValidationMaster.class)
				.add(Restrictions.eq("id.validationType", validationType))
				.setProjection(Projections.rowCount()).uniqueResult())
				.intValue();

		transaction.commit();
		session.close();
		return validationNumber;
	}

	public static void insertTests(Iterator<ValidationMaster> newListIt) {

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();

		int nextValNum = getNextValidationNumber("Test");
		int index = 0;
		while (newListIt.hasNext()) {
			ValidationMaster vm = (ValidationMaster) newListIt.next();

			if (searchValidationDetails(vm.getId().getValidationType(),
					vm.getValidationName()).size() == 0
					&& vm.getValidationName().length() > 0) {
				if (vm.getValidationDesc().trim().length() == 0) {
					vm.setValidationDesc(vm.getValidationName());
				}
				vm.getId().setValidationCode(nextValNum + index);
				session.save(vm);
				index++;
			}
		}

		transaction.commit();
		session.close();
	}
	*/
}
