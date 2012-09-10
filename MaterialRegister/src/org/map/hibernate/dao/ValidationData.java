package org.map.hibernate.dao;

import org.map.hibernate.ddo.ValidationMaster;
import java.util.Iterator;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.map.MaterialRegister;
import org.map.hibernate.HibernateUtil;
import org.map.utils.Alert;

public class ValidationData {

    public static List<String> getValidationTypes() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        List<String> validationTypes = session.createCriteria(ValidationMaster.class).setProjection(Projections.distinct(Projections.property("id.validationType"))).list();

        transaction.commit();
        session.close();
        return validationTypes;
    }

    public static List<String> getValidationName(String validationType) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        List<String> validationTypes = session.createCriteria(ValidationMaster.class).add(Restrictions.eq("id.validationType", validationType)).setProjection(Projections.distinct(Projections.property("validationName"))).list();

        transaction.commit();
        session.close();
        return validationTypes;
    }

    public static List<ValidationMaster> getValidationDetails(String validationType) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        List<ValidationMaster> validationTypes = session.createCriteria(ValidationMaster.class).add(Restrictions.eq("id.validationType", validationType)).list();

        transaction.commit();
        session.close();
        return validationTypes;
    }

    public static List<ValidationMaster> searchValidationDetails(String validationType, String validationName) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        List<ValidationMaster> validationTypes = session.createCriteria(ValidationMaster.class).add(Restrictions.eq("id.validationType", validationType)).add(Restrictions.like("validationName", "%" + validationName + "%")).list();

        transaction.commit();
        session.close();
        return validationTypes;
    }

    public static ValidationMaster getValidationDetail(String validationType, String validationName) {
        ValidationMaster vm = new ValidationMaster();

        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        Iterator it = session.createCriteria(ValidationMaster.class).add(Restrictions.eq("id.validationType", validationType)).add(Restrictions.eq("validationName", validationName)).list().iterator();

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
        Alert.showAlert(MaterialRegister.getMaterialRegister().getPrimaryStage(), "Alert", "Alert", "Validation details saved successfully.");
    }

    public static void updateValidationTypes(ValidationMaster vm) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        session.update(vm);

        transaction.commit();
        session.close();
        Alert.showAlert(MaterialRegister.getMaterialRegister().getPrimaryStage(), "Alert", "Alert", "Validation details updated successfully.");
    }

    public static int getNextValidationNumber(String validationType) throws HibernateException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        int validationNumber = 1000 + ((Integer) session.createCriteria(ValidationMaster.class).add(Restrictions.eq("id.validationType", validationType)).setProjection(Projections.rowCount()).uniqueResult()).intValue() + 1;

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
            if (vm.getValidationDesc().trim().length() == 0) {
                vm.setValidationDesc(vm.getValidationName());
            }
            vm.getId().setValidationCode(nextValNum + index);
            session.save(vm);
            index++;
        }

        transaction.commit();
        session.close();
    }
}
