package xy.inc.poi.util;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

	private static final SessionFactory sessionFactory;

	static {

		try {
			// creates the session factory
			Configuration configuration = new Configuration().configure();
			StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration
			        .getProperties());
			sessionFactory = configuration.buildSessionFactory(builder.build());

		} catch (Throwable ex) {
			// Log exception!
			throw new ExceptionInInitializerError(ex);
		}

	}

	public static Session getSession() throws HibernateException {
		return sessionFactory.getCurrentSession();
	}
}
