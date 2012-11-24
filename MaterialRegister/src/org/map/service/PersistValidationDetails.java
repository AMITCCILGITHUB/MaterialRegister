package org.map.service;

import javafx.concurrent.Service;
import javafx.concurrent.Task;

import org.map.hibernate.dao.ValidationData;
import org.map.hibernate.ddo.AgencyMaster;
import org.map.hibernate.ddo.CustomerMaster;
import org.map.hibernate.ddo.ItemMaster;
import org.map.hibernate.ddo.LaboratoryMaster;
import org.map.hibernate.ddo.ResultMaster;
import org.map.hibernate.ddo.SpecificationMaster;
import org.map.hibernate.ddo.TestMaster;

public class PersistValidationDetails extends Service<Void> {

	AgencyMaster agency;
	CustomerMaster customer;
	ItemMaster item;
	LaboratoryMaster laboratory;
	ResultMaster result;
	SpecificationMaster specification;
	TestMaster test;
	String validationType;
	PersistType persistType;

	public PersistValidationDetails(String validationType, AgencyMaster agency,
			PersistType persistType) {
		this.validationType = validationType;
		this.agency = agency;
		this.persistType = persistType;
	}

	public PersistValidationDetails(String validationType,
			CustomerMaster customer, PersistType persistType) {
		this.validationType = validationType;
		this.customer = customer;
		this.persistType = persistType;
	}

	public PersistValidationDetails(String validationType, ItemMaster item,
			PersistType persistType) {
		this.validationType = validationType;
		this.item = item;
		this.persistType = persistType;
	}

	public PersistValidationDetails(String validationType,
			LaboratoryMaster laboratory, PersistType persistType) {
		this.validationType = validationType;
		this.laboratory = laboratory;
		this.persistType = persistType;
	}

	public PersistValidationDetails(String validationType, ResultMaster result,
			PersistType persistType) {
		this.validationType = validationType;
		this.result = result;
		this.persistType = persistType;
	}

	public PersistValidationDetails(String validationType,
			SpecificationMaster specification, PersistType persistType) {
		this.validationType = validationType;
		this.specification = specification;
		this.persistType = persistType;
	}

	public PersistValidationDetails(String validationType, TestMaster test,
			PersistType persistType) {
		this.validationType = validationType;
		this.test = test;
		this.persistType = persistType;
	}

	@Override
	protected Task<Void> createTask() {

		return new Task<Void>() {

			@Override
			protected Void call() {
				switch (persistType) {
				case INSERT:

					switch (validationType) {
					case "Agency":
						ValidationData.insertAgency(agency);
						break;
					case "Customer":
						ValidationData.insertCustomer(customer);
						break;
					case "Item":
						ValidationData.insertItem(item);
						break;
					case "Laboratory":
						ValidationData.insertLaboratory(laboratory);
						break;
					case "Result":
						ValidationData.insertResult(result);
						break;
					case "Specification":
						ValidationData.insertSpecification(specification);
						break;
					case "Test":
						ValidationData.insertTest(test);
						break;
					default:
						break;
					}
					break;
				case UPDATE:

					switch (validationType) {
					case "Agency":
						ValidationData.updateAgency(agency);
						break;
					case "Customer":
						ValidationData.updateCustomer(customer);
						break;
					case "Item":
						ValidationData.updateItem(item);
						break;
					case "Laboratory":
						ValidationData.updateLaboratory(laboratory);
						break;
					case "Result":
						ValidationData.updateResult(result);
						break;
					case "Specification":
						ValidationData.updateSpecification(specification);
						break;
					case "Test":
						ValidationData.updateTest(test);
						break;
					default:
						break;
					}
					break;
				case DELETE:

					switch (validationType) {
					case "Agency":
						ValidationData.deleteAgency(agency);
						break;
					case "Customer":
						ValidationData.deleteCustomer(customer);
						break;
					case "Item":
						ValidationData.deleteItem(item);
						break;
					case "Laboratory":
						ValidationData.deleteLaboratory(laboratory);
						break;
					case "Result":
						ValidationData.deleteResult(result);
						break;
					case "Specification":
						ValidationData.deleteSpecification(specification);
						break;
					case "Test":
						ValidationData.deleteTest(test);
						break;
					default:
						break;
					}
					break;
				default:
					break;
				}

				return null;
			}
		};
	}

}