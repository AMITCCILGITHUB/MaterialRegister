package org.map.hibernate.generator;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.id.IdentifierGenerator;
import org.map.hibernate.ddo.HeatChartMaster;

public class ChartNumber implements IdentifierGenerator {

    @Override
    public Serializable generate(SessionImplementor si, Object o) throws HibernateException {
        Session session = (Session) si;

        SimpleDateFormat sdf = new SimpleDateFormat("YY");
        String chartNumber = "HC" + sdf.format(Calendar.getInstance().getTime()) + "/" + (((Integer) session.createCriteria(HeatChartMaster.class).setProjection(Projections.rowCount()).uniqueResult()).intValue() + 1);

        return chartNumber;
    }
}
