package xy.inc.poi.api;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import xy.inc.poi.domain.POI;
import xy.inc.poi.service.POIService;

@PrepareForTest(POIService.class)
@RunWith(PowerMockRunner.class)
public class POIServiceApiTest extends JerseyTest {

	protected static final String MIME_TYPE = MediaType.APPLICATION_JSON;
	
	public POIService poiServiceMock = Mockito.mock(POIService.class);
	
	@Override
	protected Application configure() {
		return new ResourceConfig(POIServiceApi.class);
	}
		
	@Test
	public void findAllPOIsCallTest() {
		PowerMockito.mockStatic(POIService.class);
		Mockito.when(POIService.getInstance()).thenReturn(poiServiceMock);
		Mockito.when(poiServiceMock.findAllPOIs()).thenReturn(new ArrayList<POI>());
		
		Response callResponse = target("pois").request().get();		
		Assert.assertEquals(200, callResponse.getStatus());
		
		List<POI> allPOIs = callResponse.readEntity(new GenericType<List<POI>>() {});
		allPOIs.size();
	}
	
	@Test
	@Transactional
	public void createPOITest() {
		POI newPOI = new POI();
		newPOI.setName("Test Place");
		newPOI.setPointX(11);
		newPOI.setPointY(8);
	
		Response callResponse = target("pois").request().post(Entity.entity(newPOI, MIME_TYPE));
		Assert.assertEquals(200, callResponse.getStatus());
		POI createdPOI = callResponse.readEntity(POI.class);
		Assert.assertNotNull(createdPOI);
		Assert.assertNotNull(createdPOI.getId());
		Assert.assertEquals(newPOI.getName(), createdPOI.getName());
		Assert.assertEquals(newPOI.getPointX(), createdPOI.getPointX());
		Assert.assertEquals(newPOI.getPointY(), createdPOI.getPointY());		
	}
}
