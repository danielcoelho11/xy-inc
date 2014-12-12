package xy.inc.poi.domain;

import java.util.List;

public interface IPOIRepository {

	public POI create(POI poi);
	public List<POI> findAll();
	public List<POI> searchNearest(int pointX, int pointY, int maxDistance);
}
