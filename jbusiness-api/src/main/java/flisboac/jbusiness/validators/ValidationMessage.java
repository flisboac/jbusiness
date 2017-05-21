package flisboac.jbusiness.validators;

import java.util.List;

import flisboac.jbusiness.resources.ResourceId;

public interface ValidationMessage {

	ResourceId getMessageId();
	Object getProperty();
    String getMessage();
	List<String> getDetails();
}
