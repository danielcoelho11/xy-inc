package xy.inc.poi.domain;

import java.util.List;

import org.hibernate.Transaction;
import org.hibernate.criterion.Order;

import xy.inc.poi.util.HibernateUtil;

@SuppressWarnings("unchecked")
public class POIs implements IPOIRepository {

	private static final String SEARCH_NEAREST_QUERY = "SELECT * FROM POI WHERE (POWER(:pointX - POINT_X, 2) + POWER(:pointY - POINT_Y, 2)) <=  POWER(:maxD, 2);";
	private static POIs poisInstance;

	public static POIs getInstance() {
		if (poisInstance == null) {
			poisInstance = new POIs();
		}

		return poisInstance;
	}

	@Override
	public POI create(POI poi) {
		Transaction tx = null;
		try {
			tx = HibernateUtil.getSession().beginTransaction();
			HibernateUtil.getSession().save(poi);
			tx.commit();
		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
			throw e;
		}

		return poi;
	}

	@Override
	public List<POI> findAll() {
		Transaction tx = null;
		try {
			tx = HibernateUtil.getSession().beginTransaction();
			List<POI> poiList = (List<POI>) HibernateUtil.getSession().createCriteria(POI.class)
			        .addOrder(Order.asc(POI.NAME)).list();
			tx.commit();
			return poiList;
		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
			throw e;
		}
	}

	@Override
	public List<POI> searchNearest(int pointX, int pointY, int maxDistance) {
		Transaction tx = null;
		try {
			tx = HibernateUtil.getSession().beginTransaction();
			List<POI> poiList = (List<POI>) HibernateUtil.getSession().createSQLQuery(SEARCH_NEAREST_QUERY)
			        .addEntity(POI.class).setParameter("pointX", pointX).setParameter("pointY", pointY)
			        .setParameter("maxD", maxDistance).list();
			tx.commit();
			return poiList;
		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
			throw e;
		}
	}

}
