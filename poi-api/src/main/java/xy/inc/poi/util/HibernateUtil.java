package xy.inc.poi.util;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

	private static Logger LOGGER = Logger.getLogger(HibernateUtil.class.getName());
	private static final SessionFactory sessionFactory;

	static {

		try {
			// creates the session factory
			Configuration configuration = new Configuration().configure();
			StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration
					.getProperties());
			sessionFactory = configuration.buildSessionFactory(builder.build());

		} catch (Throwable ex) {
			LOGGER.log(Level.SEVERE, "Error during the SessionFactory initialization.", ex);
			throw new ExceptionInInitializerError(ex);
		}
	}

	public static Session getSession() throws HibernateException {
		return sessionFactory.getCurrentSession();
	}

	/**
	 * Begins a transaction if it's still not running
	 * 
	 * @return true if a new transaction was initiated; false if the transaction was already
	 *         initiated
	 */
	public static boolean beginTransaction() {
		if(HibernateUtil.getSession().getTransaction().isActive()) {
			return false;
		} else {
			HibernateUtil.getSession().getTransaction().begin();
			return true;
		}
	}
}
