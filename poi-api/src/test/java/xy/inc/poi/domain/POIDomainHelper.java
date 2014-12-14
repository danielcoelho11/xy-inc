package xy.inc.poi.domain;

import java.util.ArrayList;
import java.util.List;

public class POIDomainHelper {

	public static POI createPOI() {
		POI poiToCreate = new POI("Test Place", 11, 8);		
		return poiToCreate;
	}

	public static List<POI> createPOIList() {
		List<POI> poiList = new ArrayList<>();
		POI poi = new POI("Test Place 1", 9, 12);
		poi.setId(1L);		
		poiList.add(poi);

		poi = new POI("Test Place 2", 10, 10);
		poi.setId(2L);		
		poiList.add(poi);

		poi = new POI("Test Place 3", 12, 11);
		poi.setId(3L);		
		poiList.add(poi);

		return poiList;
	}

}
