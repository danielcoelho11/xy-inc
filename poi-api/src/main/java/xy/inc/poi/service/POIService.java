package xy.inc.poi.service;

import java.util.List;

import xy.inc.poi.domain.POI;
import xy.inc.poi.domain.POIs;

public class POIService {

	private static POIService poiServiceInstance;

	public static POIService getInstance() {
		if (poiServiceInstance == null) {
			poiServiceInstance = new POIService();
		}

		return poiServiceInstance;
	}

	public List<POI> findAllPOIs() {
		return POIs.getInstance().findAll();
	}

	public POI createPOI(POI poi) {
		return POIs.getInstance().create(poi);
	}

	public List<POI> searchNearestPOIs(int pointX, int pointY, int maxDistance) {
		return POIs.getInstance().searchNearest(pointX, pointY, maxDistance);
	}
}
