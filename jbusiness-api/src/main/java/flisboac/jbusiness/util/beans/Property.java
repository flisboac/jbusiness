package flisboac.jbusiness.util.beans;

public interface Property {

	@Override public String toString();
	@Override public boolean equals(Object other);
	@Override public int hashCode();
	
	public boolean isInstanceBased();
	public Object getInstance();
	public Class<?> getInstanceClass();
	
	// Usable only if isInstanceBased()
	public Object get(); // Semantically, equals get(getInstance())
	public <T> T get(Class<T> returnClass); // Semantically, equals get(getInstance(), Object.class)
	public void set(Object value); // Semantically, equals set(getInstance(), value)

	// Usable anytime
	public Object get(Object instance);
	public <T> T get(Object instance, Class<T> returnClass);
	public void set(Object instance, Object value);
}
