package flisboac.jbusiness.util.beans;

import java.util.List;

public interface PropertyPath extends Property {

	public static final String SEPARATOR = ".";
	
	public PropertyDescriptor getRoot(); // Equals getPath().get(0)
	public PropertyDescriptor getEnd(); // Equals getPath().get(getPath().size() - 1)
	public List<PropertyDescriptor> getPath();
}
