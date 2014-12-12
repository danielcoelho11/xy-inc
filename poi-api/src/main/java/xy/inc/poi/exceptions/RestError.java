package xy.inc.poi.exceptions;

import java.io.Serializable;

public class RestError implements Serializable {

	private static final long serialVersionUID = -6727058125142010712L;

	private int errorCode;
	private String errorMsg;

	public RestError(int errorCode, String errorMsg) {
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
	}

	public int getErrorCode() {
		return errorCode;
	}

	public String getErrorMsg() {
		return errorMsg;
	}
}
