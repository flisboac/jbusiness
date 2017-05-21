package flisboac.jbusiness.specifications;

public interface Specification<T> {
	
	public boolean isSatisfiableBy(T entity);
}
