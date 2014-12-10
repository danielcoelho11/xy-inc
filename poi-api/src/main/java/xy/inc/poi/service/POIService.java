package xy.inc.poi.service;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import xy.inc.poi.business.POIBusiness;
import xy.inc.poi.model.POI;

@Path("pois")
public class POIService {
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<POI> findAllPOIs() {
		return POIBusiness.getInstance().findAllPOIs();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public POI createPOI(POI poi) {
		//validate poi info
		
		return POIBusiness.getInstance().createPOI(poi);
	}
	
	@Path("/nearest")
	@GET	
	@Produces(MediaType.APPLICATION_JSON)
	public List<POI> searchNearestPOIs(@QueryParam("pointX") Integer pointX, @QueryParam("pointX") Integer pointY, 
			@QueryParam("pointX") Integer maxDistance) {
		//all parameters are required
		return POIBusiness.getInstance().searchNearestPOIs(pointX, pointY, maxDistance);		
	}

}
