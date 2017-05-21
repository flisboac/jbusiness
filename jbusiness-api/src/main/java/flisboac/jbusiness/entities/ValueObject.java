package flisboac.jbusiness.entities;

public interface ValueObject {

	@Override public boolean equals(Object other);
	
	@Override public int hashCode();
}
