package xy.inc.poi.exceptions;

public enum ParameterValidationType {

	INVALID("Invalid parameter"),
	REQUIRED("The parameter '%s' is required"),
	NON_NEGATIVE_INTEGER("The parameter '%s' must be a non-negative integer"),
	NOT_A_NUMBER("The parameter '%s' must be a number");

	private String description;

	private ParameterValidationType(String description) {
		this.description = description;
	}

	public String getDescritpion() {
		return description;
	}
}
