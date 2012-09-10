package org.map.hibernate.dao;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.map.MaterialRegister;
import org.map.hibernate.HibernateUtil;
import org.map.hibernate.ddo.UserMaster;
import org.map.utils.Alert;

public class UserData {

    public static boolean validateUser(UserMaster user) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        int rowCount = session.createCriteria(UserMaster.class).add(Restrictions.eq("userName", user.getUserName())).add(Restrictions.eq("password", user.getPassword())).list().size();

        transaction.commit();
        session.close();
        return ((rowCount > 0) ? true : false);
    }

    public static UserMaster getUserDetails(String userName) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        UserMaster um = (UserMaster) session.get(UserMaster.class, userName);

        transaction.commit();
        session.close();
        return um;
    }

    public static List<UserMaster> getUserList() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        List<UserMaster> users = session.createCriteria(UserMaster.class).list();

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
        Alert.showAlert(MaterialRegister.getMaterialRegister().getPrimaryStage(), "Alert", "Alert", "User details saved successfully.");
    }

    public static void updateUser(UserMaster user) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        session.update(user);

        transaction.commit();
        session.close();
        Alert.showAlert(MaterialRegister.getMaterialRegister().getPrimaryStage(), "Alert", "Alert", "User details updated successfully.");
    }
}
