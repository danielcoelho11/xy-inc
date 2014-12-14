package xy.inc.poi.domain;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import xy.inc.poi.util.HibernateUtil;

public class POIsTest {

	private Session session;
	private IPOIRepository poiRepository;

	@Before
	public void configureExecution() {
		session = HibernateUtil.getSession();
		poiRepository = POIs.getInstance();
	}

	@Test
	public void createTest() {
		POI poi = POIDomainHelper.createPOI();
		poi.setId(null);

		HibernateUtil.beginTransaction();

		poiRepository.create(poi);
		assertNotNull(poi.getId());

		HibernateUtil.getSession().getTransaction().rollback();
	}

	@Test
	public void searchNearestTest() {
		
		List<POI> nearestPoints = new ArrayList<>();
		nearestPoints.add(new POI("Nearest POI 1", 27, 12));
		nearestPoints.add(new POI("Nearest POI 2", 23, 6));
		List<POI> farAwayPoints = new ArrayList<>();
		farAwayPoints.add(new POI("Nearest POI 3", 28, 2));
		farAwayPoints.add(new POI("Nearest POI 4", 31, 18));		

		HibernateUtil.beginTransaction();
		for(POI poi : nearestPoints) {
			poiRepository.create(poi);
		}
		for(POI poi : farAwayPoints) {
			poiRepository.create(poi);
		}
		
		List<POI> nearestPointsFromDB = poiRepository.searchNearest(20, 10, 10);
		assertTrue(nearestPointsFromDB.containsAll(nearestPointsFromDB));
		assertTrue(!nearestPointsFromDB.contains(farAwayPoints.get(0)));
		assertTrue(!nearestPointsFromDB.contains(farAwayPoints.get(1)));

		HibernateUtil.getSession().getTransaction().rollback();
	}

	@After
	public void afterExecution() {
		if (session != null) {
			try {
				session.close();
			} catch (HibernateException ex) {
				// ignore
			}
		}
	}
}
