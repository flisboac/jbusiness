package flisboac.jbusiness.validators;

import java.util.List;

public interface Validation {
	
	public boolean hasCriteria();
	public List<Object> getSubjectProperties();
	public List<Class<?>> getInvariants();
}
