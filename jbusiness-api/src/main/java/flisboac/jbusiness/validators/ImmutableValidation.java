package flisboac.jbusiness.validators;

import java.util.List;

import flisboac.jbusiness.Immutable;

public class ImmutableValidation extends SimpleValidation implements Immutable{

	public ImmutableValidation() {
		super();
	}

	public ImmutableValidation(List<Object> subjectProperties, List<Class<?>> validationGroups) {
		super(subjectProperties, validationGroups);
	}

	public ImmutableValidation(ImmutableValidation other) {
		super(other);
	}

}
