package flisboac.jbusiness.entities.repositories;

import flisboac.jbusiness.entities.specifications.Specification;

public interface RootMutableRepository<T> extends Repository {

    T add(T entity);

    void remove(T entity);
    void remove(Specification<? super T> specification);
    void removeById(Object id);
}
