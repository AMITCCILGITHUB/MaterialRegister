package org.map.hibernate;

import java.io.File;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

public class HibernateUtil {

	private static final SessionFactory sessionFactory;
	private static ServiceRegistry serviceRegistry;

	static {
		try {
			File hCfgFile = new File("resources/hibernate.cfg.xml");

			Configuration hCfg = new Configuration();
			hCfg.configure(hCfgFile);

			serviceRegistry = new ServiceRegistryBuilder().applySettings(
					hCfg.getProperties()).buildServiceRegistry();
			sessionFactory = hCfg.buildSessionFactory(serviceRegistry);

		} catch (Throwable ex) {
			ex.printStackTrace();
			throw new ExceptionInInitializerError(ex);
		}
	}

	public static SessionFactory getSessionFactory() {

		return sessionFactory;
	}
}