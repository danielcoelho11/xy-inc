package xy.inc.poi.exception;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

public class RestParameterException extends WebApplicationException {

	private static final long serialVersionUID = 506104598119663449L;	

	public RestParameterException(String fieldName, ParameterValidationType type) {
		super(Response.status(Status.BAD_REQUEST).type(MediaType.APPLICATION_JSON)
				.entity(new RestError(Status.BAD_REQUEST.getStatusCode(),
						String.format(type.getDescritpion(), fieldName))).build());
	}

}
