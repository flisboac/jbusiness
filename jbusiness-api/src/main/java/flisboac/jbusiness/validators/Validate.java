package flisboac.jbusiness.validators;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import flisboac.jbusiness.FluentBuilder;

public final class Validate implements FluentBuilder<Validation> {
	
	private List<Object> subjectProperties = new ArrayList<>();
	private List<Class<?>> validationGroups = new ArrayList<>();
	
	private Validate() {
		super();
	}

	public static Validation all() {
		return new ImmutableValidation();
	}
	
	public static Validate property(Object... properties) {
		Validate validate = new Validate();
		return validate.withProperty(properties);
	}
	
	public static Validate properties(List<Object> properties) {
		Validate validate = new Validate();
		return validate.withProperties(properties);
	}
	
	public static Validate group(Class<?>... groups) {
		Validate validate = new Validate();
		return validate.withGroup(groups);
	}
	
	public static Validate groups(List<Class<?>> groups) {
		Validate validate = new Validate();
		return validate.withGroups(groups);
	}
	
	public Validate withProperty(Object... properties) {
		return withProperties(Arrays.asList(properties));
	}
	
	public Validate withProperties(List<Object> properties) {
		this.subjectProperties.addAll(properties);
		return this;
	}
	
	public Validate withGroup(Class<?>... groups) {
		return withGroups(Arrays.asList(groups));
	}
	
	public Validate withGroups(List<Class<?>> groups) {
		this.validationGroups.addAll(groups);
		return this;
	}
	
	public Validation done() {
		return new ImmutableValidation(
				subjectProperties, validationGroups
		);
	}

	public static final Validation ALL = Validate.all();
}
