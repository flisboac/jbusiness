package flisboac.jbusiness.entities.repositories;

import flisboac.jbusiness.entities.specifications.Specification;
import flisboac.jbusiness.util.ordering.Ordering;
import flisboac.jbusiness.util.projections.Projection;
import flisboac.jbusiness.util.pagination.Pagination;

import java.math.BigInteger;
import java.util.List;

public interface RootViewRepository<T> extends Repository {

    // Entity existence (in the persistence environment)
    boolean contains(T entity); // By any possible identity (e.g. surrogate ID, group of fields)
    boolean containsById(Object id);

    // Counting
    BigInteger count();
    BigInteger count(Specification<? super T> specification);

    // Cardinality: 1
    T findOne(T entity);
    T findOne(Specification<? super T> specification);
    T findOneById(Object id);

    // Cardinality: 0..1
    T findAny(T entity);
    T findAny(Specification<? super T> specification);
    T findAnyById(Object id);

    // Cardinality: 0..n
    List<T> findAll(Specification<? super T> specification, Ordering ordering);
    List<T> findAll(Projection<T> projection, Specification<? super T> specification, Ordering ordering);
    RepositoryPage<T> findAll(Specification<? super T> specification, Ordering ordering, Pagination config);
    RepositoryPage<T> findAll(Projection<T> projection, Specification<? super T> specification, Ordering ordering, Pagination config);

    // Pagination handling
    List<T> list(RepositoryPage<T> page);
}
