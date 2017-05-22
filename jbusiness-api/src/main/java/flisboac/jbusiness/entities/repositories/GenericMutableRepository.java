package flisboac.jbusiness.entities.repositories;

import flisboac.jbusiness.entities.GenericEntityService;
import flisboac.jbusiness.entities.specifications.Specification;

public interface GenericMutableRepository extends GenericEntityService {

    <T> T add(T entity);

    <T> void remove(T entity);
    <T> void remove(Class<T> entityClass, Specification<? super T> specification);
    <T> void removeById(Class<T> entityClass, Object id);
}
