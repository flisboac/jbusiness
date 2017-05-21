package flisboac.jbusiness.validators;

import flisboac.jbusiness.BusinessError;

public class ValidationError extends BusinessError {
	private static final long serialVersionUID = 1L;

	private final ValidationResult validationResult;
	
	public ValidationError() {
		super();
		this.validationResult = null;
	}

	protected ValidationError(ValidationResult validationResult, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(validationResult.getSummary(), cause, enableSuppression, writableStackTrace);
		this.validationResult = validationResult;
	}

	public ValidationError(ValidationResult result, Throwable cause) {
		super(result.getSummary(), cause);
		this.validationResult = result;
	}

	public ValidationError(ValidationResult result) {
		super(result.getSummary());
		this.validationResult = result;
	}

	protected ValidationError(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		this.validationResult = null;
	}

	public ValidationError(String message, Throwable cause) {
		super(message, cause);
		this.validationResult = null;
	}

	public ValidationError(String message) {
		super(message);
		this.validationResult = null;
	}

	public ValidationError(Throwable cause) {
		super(cause);
		this.validationResult = null;
	}

	public ValidationResult getValidationResult() {
		return validationResult;
	}
	
}
