package org.map.hibernate.dao;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import org.hibernate.*;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.map.MaterialRegister;
import org.map.hibernate.HibernateUtil;
import org.map.hibernate.ddo.*;
import org.map.utils.Alert;

public class HeatChartData {

    public static void insertHeatChart(HeatChartMaster heatChart) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        session.save(heatChart);

        transaction.commit();
        session.close();
        Alert.showAlert(MaterialRegister.getMaterialRegister().getPrimaryStage(), "Alert", "Alert", "Heat Chart details saved successfully.");
    }

    public static void updateHeatChart(HeatChartMaster heatChart) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        session.update(heatChart);

        transaction.commit();
        session.close();
        Alert.showAlert(MaterialRegister.getMaterialRegister().getPrimaryStage(), "Alert", "Alert", "Heat Chart details updated successfully.");
    }

    public static String getNextChartNumber() throws HibernateException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        String chartNumber = (String) session.getNamedQuery("nextHcNumberQuery").uniqueResult();

        transaction.commit();
        session.close();
        return chartNumber;
    }

    public static HeatChartMaster searchHeatChartDetailsByHcNumber(String heatChartNumber) throws ParseException {
        HeatChartMaster hcMaster = new HeatChartMaster();

        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        Iterator it = session.createCriteria(HeatChartMaster.class).add(Restrictions.like("chartNumber", heatChartNumber, MatchMode.ANYWHERE)).list().iterator();

        if (it.hasNext()) {
            hcMaster = (HeatChartMaster) it.next();
            hcMaster.getHeatchartsheets().size();
        }

        transaction.commit();
        session.close();
        return hcMaster;
    }

    public static List<HeatChartMaster> searchHeatChartDetailsHc(String hcNumberFrom, String hcNumberTo) throws ParseException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        Query qry = session.getNamedQuery("searchHcNumberQuery");
        qry.setParameter("fromHcNumber", hcNumberFrom.substring(5));
        qry.setParameter("toHcNumber", hcNumberTo.substring(5));

        List<HeatChartMaster> heatCharts = qry.list();
        Iterator it = heatCharts.iterator();
        while (it.hasNext()) {
            HeatChartMaster heatChart = (HeatChartMaster) it.next();
            heatChart.getHeatchartsheets().size();
        }

        transaction.commit();
        session.close();
        return heatCharts;
    }

    public static List<HeatChartMaster> searchHeatChartDetailsDt(Date fromDate, Date toDate) throws ParseException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        Calendar cal = Calendar.getInstance();
        cal.setTime(toDate);
        cal.add(Calendar.DATE, 1);

        List<HeatChartMaster> heatCharts = session.createCriteria(HeatChartMaster.class).add(Restrictions.between("createdDate", fromDate, cal.getTime())).list();
        Iterator it = heatCharts.iterator();

        while (it.hasNext()) {
            HeatChartMaster heatChart = (HeatChartMaster) it.next();
            heatChart.getHeatchartsheets().size();
        }

        transaction.commit();
        session.close();
        return heatCharts;
    }
}
