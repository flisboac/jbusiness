package flisboac.jbusiness.validators;

import java.util.List;

import flisboac.jbusiness.resources.ResourceId;

public interface ValidationResult {

	boolean isValid();
	Object getSubject();
	ResourceId getMessageId();
	String getSummary();
	List<ValidationMessage> getMessages();
}
