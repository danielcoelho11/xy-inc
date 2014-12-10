package xy.inc.poi.business;

import java.util.List;

import xy.inc.poi.model.POI;

public class POIBusiness {

	private static POIBusiness poiBusinessInstance;

	public static POIBusiness getInstance() {
		if (poiBusinessInstance != null) {
			poiBusinessInstance = new POIBusiness();
		}

		return poiBusinessInstance;
	}

	/**
	 * Returns all POIs
	 * 
	 * @return a list of POIs
	 */
	public List<POI> findAllPOIs() {
		return null;
	}

	/**
	 * Creates a new POI
	 * 
	 * @param poi
	 * @return
	 */
	public POI createPOI(POI poi) {
		return null;
	}

	/**
	 * Returns the nearest POIs from a point within a max distance
	 * 
	 * @param pointX
	 * @param pointY
	 * @param maxDistance
	 * @return a list of POIs
	 */
	public List<POI> searchNearestPOIs(int pointX, int pointY, int maxDistance) {
		return null;
	}

}
