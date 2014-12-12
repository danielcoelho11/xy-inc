package xy.inc.poi.exceptions;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

public class InternalServerException extends WebApplicationException {

	private static final long serialVersionUID = -1252132793457662690L;
	private static final String MESSAGE = "An unexpected error has occurred. See server logs for details.";

	public InternalServerException() {
		super(Response.status(Status.INTERNAL_SERVER_ERROR).type(MediaType.APPLICATION_JSON)
		        .entity(new RestError(Status.INTERNAL_SERVER_ERROR.getStatusCode(), MESSAGE)).build());
	}
}
