package flisboac.jbusiness;

public interface Duplicable<T> {

	/**
	 * Makes a duplicate of the entity. A duplicate is an entity that has the
	 * same data but a different ID: while they may represent the same subject
	 * in content, they are different entities entirely. In a more practical
	 * view, if you duplicate an entity and successfully save it, it will have
	 * a non-null ID that is different from that of its original entity. 
	 * @return
	 */
	public T duplicate();
	
}
