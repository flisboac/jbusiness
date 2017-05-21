package flisboac.jbusiness.validators;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import flisboac.jbusiness.Copyable;

public class SimpleValidation
implements Copyable<SimpleValidation>, Validation {

	private final List<Object> subjectProperties;
	private final List<Class<?>> invariants;
	
	public SimpleValidation() {
		super();
		this.subjectProperties = new ArrayList<>();
		this.invariants = new ArrayList<>();
	}
	
	public SimpleValidation(SimpleValidation other) {
		super();
		this.subjectProperties = new ArrayList<>(other.subjectProperties);
		this.invariants = new ArrayList<>(other.invariants);
	}
	
	public SimpleValidation(List<Object> subjectProperties, List<Class<?>> validationGroups) {
		this();
		if (subjectProperties != null) {
			this.subjectProperties.addAll(subjectProperties);
		}
		if (validationGroups != null) {
			this.invariants.addAll(validationGroups);
		}
	}
	
	public boolean hasCriteria() {
		return !subjectProperties.isEmpty() || !invariants.isEmpty();
	}
	
	public List<Object> getSubjectProperties() {
		return Collections.unmodifiableList(subjectProperties);
	}
	
	public List<Class<?>> getInvariants() {
		return Collections.unmodifiableList(invariants);
	}

	@Override
	public SimpleValidation copy() {
		return new SimpleValidation(this);
	}

}
