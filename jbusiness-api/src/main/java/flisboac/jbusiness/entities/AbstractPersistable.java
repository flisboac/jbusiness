package flisboac.jbusiness.entities;

public abstract class AbstractPersistable<T> implements Persistable<T> {
	
	public AbstractPersistable() {
		super();
	}
	
	@Override
	public boolean isIdentified() {
		return getId() != null;
	}
	
}
