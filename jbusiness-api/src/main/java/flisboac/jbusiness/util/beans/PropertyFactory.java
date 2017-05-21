package flisboac.jbusiness.util.beans;

public interface PropertyFactory {

	public Property getProperty(Object property, AccessMode accessMode);
	public Property getProperty(Object instance, Object property, AccessMode accessMode);
}
