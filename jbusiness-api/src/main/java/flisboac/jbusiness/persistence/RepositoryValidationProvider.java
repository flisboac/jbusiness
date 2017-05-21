package flisboac.jbusiness.persistence;

import flisboac.jbusiness.validators.Validation;

public interface RepositoryValidationProvider {

	public <T> Validation getValidation(RepositoryOperationId operation, Class<T> entity);
}
