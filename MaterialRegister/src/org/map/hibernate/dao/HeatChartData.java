package org.map.hibernate.dao;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.map.hibernate.HibernateUtil;
import org.map.hibernate.OrderBySqlFormula;
import org.map.hibernate.ddo.HeatChartMaster;
import org.map.hibernate.ddo.HeatChartSheets;
import org.map.hibernate.ddo.MaterialTests;

public class HeatChartData {

	public static void insertHeatChart(HeatChartMaster heatChart) {

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();

		heatChart.setHeatChartCode(getNextHeatChartCode());
		
		int nextSheetCode = getNextHeatChartSheetCode();
		for (HeatChartSheets sheet : heatChart.getHeatChartSheets()) {
			if(sheet.getHeatChartSheetCode() == 0){
				sheet.setHeatChartSheetCode(nextSheetCode);
				sheet.setHeatchartmaster(heatChart);
				nextSheetCode++;
			}
		}
		
		session.save(heatChart);

		transaction.commit();
		session.close();
	}

	public static void updateHeatChart(HeatChartMaster heatChart) {

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();

		session.update(heatChart);

		transaction.commit();
		session.close();
	}

	public static void deleteHeatChart(HeatChartMaster heatChart) {

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();

		session.delete(heatChart);

		transaction.commit();
		session.close();
	}
	
	public static String getNextChartNumber()
			throws HibernateException {

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();

		String chartNumber = (String) session
				.getNamedQuery("nextHcNumberQuery")
				.setString("year", CodeData.getCurrentYear()).uniqueResult();

		transaction.commit();
		session.close();
		return chartNumber;
	}

	public static HeatChartMaster searchHeatChartDetailsByHcNumber(
			String heatChartNumber) throws ParseException {

		HeatChartMaster hcMaster = new HeatChartMaster();

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();

		Iterator it = session
				.createCriteria(HeatChartMaster.class)
				.add(Restrictions.like("chartNumber", heatChartNumber,
						MatchMode.ANYWHERE)).list().iterator();

		if (it.hasNext()) {
			hcMaster = (HeatChartMaster) it.next();
			hcMaster.getHeatChartSheets().size();
		}

		transaction.commit();
		session.close();
		return hcMaster;
	}

	public static List<HeatChartMaster> searchHeatChartDetailsHc(
			String hcNumberFrom, String hcNumberTo) throws ParseException {

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();

		Query qry = null;
		if (hcNumberTo == null || hcNumberTo.trim().length() == 0) {
			qry = session.getNamedQuery("searchHcNumberQuerySingle");
			qry.setParameter("fromHcNumber", hcNumberFrom);
		} else {
			qry = session.getNamedQuery("searchHcNumberQuery");
			qry.setParameter("fromHcNumber", hcNumberFrom);
			qry.setParameter("toHcNumber", hcNumberTo);
		}

		List<HeatChartMaster> heatCharts = qry.list();
		Iterator it = heatCharts.iterator();
		while (it.hasNext()) {
			HeatChartMaster heatChart = (HeatChartMaster) it.next();
			heatChart.getHeatChartSheets().size();
		}

		transaction.commit();
		session.close();
		return heatCharts;
	}

	public static List<HeatChartMaster> searchHeatChartDetailsDt(Date fromDate,
			Date toDate) throws ParseException {

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();

		List<HeatChartMaster> heatCharts = null;
		if (toDate == null) {
			heatCharts = session
					.createCriteria(HeatChartMaster.class)
					.add(Restrictions.ge("createdDate", fromDate))
					.addOrder(
							OrderBySqlFormula
									.sqlFormula("cast(substring(substring_index(Chart_Number, '-', 1), 6) as unsigned) asc"))
					.list();
		} else {
			Calendar cal = Calendar.getInstance();
			cal.setTime(toDate);
			cal.add(Calendar.DATE, 1);

			heatCharts = session
					.createCriteria(HeatChartMaster.class)
					.add(Restrictions.between("createdDate", fromDate,
							cal.getTime()))
					.addOrder(
							OrderBySqlFormula
									.sqlFormula("cast(substring(substring_index(Chart_Number, '-', 1), 6) as unsigned) asc"))
					.list();
		}
		Iterator it = heatCharts.iterator();

		while (it.hasNext()) {
			HeatChartMaster heatChart = (HeatChartMaster) it.next();
			heatChart.getHeatChartSheets().size();
		}

		transaction.commit();
		session.close();
		return heatCharts;
	}

	public static int getNextHeatChartCode() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();

		Object result = session
		.createCriteria(HeatChartMaster.class)
		.setProjection(Projections.max("heatChartCode")).uniqueResult();
		
		int heatChartCode = 1001;
		if(result != null)
			heatChartCode = (int) result + 1;

		transaction.commit();
		session.close();
		return heatChartCode;	
	}
	
	public static int getNextHeatChartSheetCode() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();

		Object result = session
		.createCriteria(HeatChartSheets.class)
		.setProjection(Projections.max("heatChartSheetCode")).uniqueResult();
		
		int heatChartCode = 1001;
		if(result != null)
			heatChartCode = (int) result + 1;

		transaction.commit();
		session.close();
		return heatChartCode;	
	}
}
