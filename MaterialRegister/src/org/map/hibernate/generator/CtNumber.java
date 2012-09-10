package org.map.hibernate.generator;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.id.IdentifierGenerator;
import org.map.hibernate.ddo.MaterialMaster;

public class CtNumber implements IdentifierGenerator {

    @Override
    public Serializable generate(SessionImplementor si, Object o) throws HibernateException {
        Session session = (Session) si;

        SimpleDateFormat sdf = new SimpleDateFormat("YY");
        String ctNumber = "MP" + sdf.format(Calendar.getInstance().getTime()) + "/" + (((Integer) session.createCriteria(MaterialMaster.class).setProjection(Projections.rowCount()).uniqueResult()).intValue() + 1);

        return ctNumber;
    }
}
