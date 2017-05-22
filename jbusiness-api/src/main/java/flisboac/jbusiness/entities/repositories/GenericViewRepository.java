package flisboac.jbusiness.entities.repositories;

import flisboac.jbusiness.entities.GenericEntityService;
import flisboac.jbusiness.entities.specifications.Specification;
import flisboac.jbusiness.util.ordering.Ordering;
import flisboac.jbusiness.util.pagination.Pagination;
import flisboac.jbusiness.util.projections.Projection;

import java.math.BigInteger;
import java.util.List;

public interface GenericViewRepository extends GenericEntityService {

    // Entity existence (in the persistence environment)
    <T> boolean contains(T entity); // By any possible identity (e.g. surrogate ID, group of fields)
    <T> boolean containsById(Class<T> entityClass, Object id);

    // Counting
    <T> BigInteger count(Class<T> entityClass);
    <T> BigInteger count(Class<T> entityClass, Specification<? super T> specification);

    // Cardinality: 1
    <T> T findOne(T entity);
    <T> T findOne(Class<T> entityClass, Specification<? super T> specification);
    <T> T findOneById(Class<T> entityClass, Object id);

    // Cardinality: 0..1
    <T> T findAny(T entity);
    <T> T findAny(Class<T> entityClass, Specification<? super T> specification);
    <T> T findAnyById(Class<T> entityClass, Object id);

    // Cardinality: 0..n
    <T> List<T> findAll(Class<T> entityClass, Specification<? super T> specification, Ordering ordering);
    <T> List<T> findAll(Projection<T> projection, Specification<? super T> specification, Ordering ordering);
    <T> RepositoryPage<T> findAll(Class<T> entityClass, Specification<? super T> specification, Ordering ordering, Pagination config);
    <T> RepositoryPage<T> findAll(Projection<T> projection, Specification<? super T> specification, Ordering ordering, Pagination config);

    // Pagination handling
    <T> List<T> list(RepositoryPage<T> page);
}
