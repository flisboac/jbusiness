package flisboac.jbusiness.entities.specifications;

public interface Specification<T> {

    boolean isSatisfiableBy(T entity);
}
