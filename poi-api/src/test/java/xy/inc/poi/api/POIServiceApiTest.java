package xy.inc.poi.api;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import xy.inc.poi.domain.POI;
import xy.inc.poi.domain.POIDomainHelper;
import xy.inc.poi.exceptions.ParameterValidationType;
import xy.inc.poi.exceptions.RestError;
import xy.inc.poi.service.POIService;

@RunWith(PowerMockRunner.class)
@PrepareForTest(POIService.class)
public class POIServiceApiTest extends JerseyTest {

	protected static final String MIME_TYPE = MediaType.APPLICATION_JSON;

	private POIService poiServiceMock = Mockito.mock(POIService.class);

	@Before
	public void configureExecution() {
		PowerMockito.mockStatic(POIService.class);
		Mockito.when(POIService.getInstance()).thenReturn(poiServiceMock);
	}

	@Override
	protected Application configure() {
		return new ResourceConfig(POIServiceApi.class);
	}

	@Test
	public void findAllPOIsCallTest() {
		List<POI> poiList = POIDomainHelper.createPOIList();
		Mockito.when(poiServiceMock.findAllPOIs()).thenReturn(poiList);

		Response callResponse = target("pois").request().get();
		assertEquals(200, callResponse.getStatus());

		List<POI> allPOIs = callResponse.readEntity(new GenericType<List<POI>>() {
		});

		assertEquals(poiList.size(), allPOIs.size());
		assertTrue(allPOIs.containsAll(poiList));
	}

	@Test
	public void createPOIWithSuccess() {
		POI poiToCreate = POIDomainHelper.createPOI();

		POI mockedPOI = new POI();
		mockedPOI.setId(10L);
		mockedPOI.setName(poiToCreate.getName());
		mockedPOI.setPointX(poiToCreate.getPointX());
		mockedPOI.setPointY(poiToCreate.getPointY());

		Mockito.when(poiServiceMock.createPOI(poiToCreate)).thenReturn(mockedPOI);
		Response callResponse = target("pois").request().post(Entity.entity(poiToCreate, MIME_TYPE));
		assertEquals(200, callResponse.getStatus());

		POI createdPOI = callResponse.readEntity(POI.class);
		assertEquals(mockedPOI, createdPOI);
	}

	@Test
	public void createPOIWithInvalidParameters() {
		//invalid name
		POI poiToCreate = POIDomainHelper.createPOI();
		poiToCreate.setName(null);
		Response callResponse = target("pois").request().post(Entity.entity(poiToCreate, MIME_TYPE));

		Assert.assertEquals(400, callResponse.getStatus());
		RestError error = callResponse.readEntity(RestError.class);
		assertEquals(error.getErrorCode(), 400);
		assertEquals(error.getErrorMsg(), String.format(ParameterValidationType.REQUIRED.getDescritpion(), "name"));
		
		//invalid pointX
		poiToCreate = POIDomainHelper.createPOI();
		poiToCreate.setPointX(-5);
		callResponse = target("pois").request().post(Entity.entity(poiToCreate, MIME_TYPE));

		Assert.assertEquals(400, callResponse.getStatus());
		error = callResponse.readEntity(RestError.class);
		assertEquals(error.getErrorCode(), 400);
		assertEquals(error.getErrorMsg(),
				String.format(ParameterValidationType.NON_NEGATIVE_INTEGER.getDescritpion(), "pointX"));
		
		//invalid pointY
		poiToCreate = POIDomainHelper.createPOI();
		poiToCreate.setPointY(-1);
		callResponse = target("pois").request().post(Entity.entity(poiToCreate, MIME_TYPE));

		Assert.assertEquals(400, callResponse.getStatus());
		error = callResponse.readEntity(RestError.class);
		assertEquals(error.getErrorCode(), 400);
		assertEquals(error.getErrorMsg(),
				String.format(ParameterValidationType.NON_NEGATIVE_INTEGER.getDescritpion(), "pointY"));
		
		//with no parameters
		callResponse = target("pois").request().post(Entity.entity(null, MIME_TYPE));
		Assert.assertEquals(400, callResponse.getStatus());
		error = callResponse.readEntity(RestError.class);
		assertEquals(error.getErrorCode(), 400);
		assertEquals(error.getErrorMsg(), ParameterValidationType.INVALID.getDescritpion());
	}	

	@Test
	public void searchNearestWithSuccess() {
		int pointX = 10;
		int pointY = 10;
		int maxDistance = 25;
		List<POI> mockedPOIList = POIDomainHelper.createPOIList();
		Mockito.when(poiServiceMock.searchNearestPOIs(pointX, pointY, maxDistance)).thenReturn(mockedPOIList);

		Response callResponse = target("pois/nearest")
				.queryParam("pointX", pointX).queryParam("pointY", pointY)
				.queryParam("maxDistance", maxDistance).request().get();

		assertEquals(200, callResponse.getStatus());

		List<POI> nearestPOIs = callResponse.readEntity(new GenericType<List<POI>>() {
		});
		assertEquals(mockedPOIList, nearestPOIs);
	}

	@Test
	public void searchNearestWithInvalidParametes() {	
		//invalid pointX
		Response callResponse = target("pois/nearest")
				.queryParam("pointX", -10).queryParam("pointY", 10)
				.queryParam("maxDistance", 25).request().get();

		assertEquals(400, callResponse.getStatus());
		RestError error = callResponse.readEntity(RestError.class);
		assertEquals(error.getErrorCode(), 400);
		assertEquals(error.getErrorMsg(),
				String.format(ParameterValidationType.NON_NEGATIVE_INTEGER.getDescritpion(), "pointX"));
		
		//invalid pointY
		callResponse = target("pois/nearest")
				.queryParam("pointX", 10).queryParam("pointY", -10)
				.queryParam("maxDistance", 25).request().get();

		assertEquals(400, callResponse.getStatus());
		error = callResponse.readEntity(RestError.class);
		assertEquals(error.getErrorCode(), 400);
		assertEquals(error.getErrorMsg(),
				String.format(ParameterValidationType.NON_NEGATIVE_INTEGER.getDescritpion(), "pointY"));
		
		//invalid maxDistance
		callResponse = target("pois/nearest")
				.queryParam("pointX", 10).queryParam("pointY", 10)
				.queryParam("maxDistance", -25).request().get();

		assertEquals(400, callResponse.getStatus());
		error = callResponse.readEntity(RestError.class);
		assertEquals(error.getErrorCode(), 400);
		assertEquals(error.getErrorMsg(),
				String.format(ParameterValidationType.NON_NEGATIVE_INTEGER.getDescritpion(), "maxDistance"));
	}	
}
