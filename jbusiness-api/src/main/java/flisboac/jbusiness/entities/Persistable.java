package flisboac.jbusiness.entities;

public interface Persistable<T> {

	public T getId();
	
	public boolean isIdentified();
	
	// Don't know if this is really needed.
	// public boolean isLoaded();
}
