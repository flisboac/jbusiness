package flisboac.jbusiness.validators;

public interface Validator {

	public <T> ValidationResult validate(T subject, Validation procedure);
	public <T> void validOrError(T subject, Validation procedure) throws ValidationError;
}
