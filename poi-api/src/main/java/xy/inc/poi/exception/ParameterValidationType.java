package xy.inc.poi.exception;

public enum ParameterValidationType {
	
	REQUIRED("The field '%s' is required"),
	INTEGER_POSITIVE("The field '%s' must be a positive integer");
	
	private String description;
	
	private ParameterValidationType(String description) {
		this.description = description;
	}

	public String getDescritpion() {
		return description;
	}
}
