package xy.inc.poi.api;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.apache.commons.lang3.StringUtils;

import xy.inc.poi.domain.POI;
import xy.inc.poi.exceptions.InternalServerException;
import xy.inc.poi.exceptions.ParameterValidationType;
import xy.inc.poi.exceptions.RestParameterException;
import xy.inc.poi.service.POIService;

@Path("pois")
public class POIServiceApi {

	private static Logger LOGGER = Logger.getLogger(POIServiceApi.class.getName());

	/**
	 * Returns all POIs
	 * 
	 * @return a list of POIs
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<POI> findAllPOIs() {
		try {
			return POIService.getInstance().findAllPOIs();
		} catch (Exception ex) {
			LOGGER.log(Level.SEVERE, "Error executing findAllPOIs.", ex);
			throw new InternalServerException();
		}
	}

	/**
	 * Creates a new POI
	 * 
	 * @param poi
	 * @return
	 */
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public POI createPOI(POI poi) {
		try {
			// validate poi info
			if (poi == null) {
				throw new RestParameterException("pointX", ParameterValidationType.INVALID);
			}
			if (StringUtils.isBlank(poi.getName())) {
				throw new RestParameterException("name", ParameterValidationType.REQUIRED);
			}
			validateNonNegativeIntParam("pointX", poi.getPointX());
			validateNonNegativeIntParam("pointY", poi.getPointY());					

			return POIService.getInstance().createPOI(poi);
		} catch (Exception ex) {
			LOGGER.log(Level.SEVERE, "Error executing createPOI.", ex);
			if (ex instanceof RestParameterException) {
				throw ex;
			} else {
				throw new InternalServerException();
			}
		}
	}

	/**
	 * Returns the nearest POIs from a point within a max distance
	 * 
	 * @param pointX
	 * @param pointY
	 * @param maxDistance
	 * @return a list of POIs
	 */
	@Path("/nearest")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<POI> searchNearestPOIs(@QueryParam("pointX") int pointX, @QueryParam("pointY") int pointY,
	        @QueryParam("maxDistance") int maxDistance) {
		try {
			validateNonNegativeIntParam("pointX", pointX);
			validateNonNegativeIntParam("pointY", pointY);
			validateNonNegativeIntParam("maxDistance", maxDistance);			

			return POIService.getInstance().searchNearestPOIs(pointX, pointY, maxDistance);
		} catch (Exception ex) {
			LOGGER.log(Level.SEVERE, "Error executing searchNearestPOIs.", ex);
			if (ex instanceof RestParameterException) {
				throw ex;
			} else {
				throw new InternalServerException();
			}
		}
	}
	
	private void validateNonNegativeIntParam(String parameterName, int parameterValue) {
		if (parameterValue < 0) {
			throw new RestParameterException(parameterName, ParameterValidationType.NON_NEGATIVE_INTEGER);
		}
	}

}
