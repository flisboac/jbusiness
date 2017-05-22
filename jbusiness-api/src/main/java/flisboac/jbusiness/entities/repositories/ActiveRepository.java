package flisboac.jbusiness.entities.repositories;

import flisboac.jbusiness.entities.ActiveEntityService;
import flisboac.jbusiness.entities.specifications.Specification;
import flisboac.jbusiness.util.ordering.Ordering;
import flisboac.jbusiness.util.projections.Projection;
import flisboac.jbusiness.util.pagination.Pagination;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

public interface ActiveRepository<T> extends ActiveEntityService<T> { // More like a decorator than an active record... Always @Dependent scope!

    //
    // Entity data
    //

    boolean isNew();
    boolean isPersisted();

    //
    // State management
    //

    ActiveRepository<T> reset();
    ActiveRepository<T> from(T entity);
    <C extends T> ActiveRepository<T> from(Class<C> entityClass); // Used only to specify a derived class

    //
    // Creation and Mutation (DML)
    //

    ActiveRepository<T> create(); // Used when from(entity) is not used
    ActiveRepository<T> add();
    ActiveRepository<T> remove();

    //
    // Selection configuration (DQL)
    //

    ActiveRepository<T> join(Projection<T> projection);
    ActiveRepository<T> withIdentity(T entity);
    ActiveRepository<T> withId(Object id);
    ActiveRepository<T> where(Specification<? super T> specification);
    ActiveRepository<T> orderBy(Ordering ordering);

    //
    // Selection result
    //

    RepositoryPage<T> limit(Pagination config);
    BigInteger count();
    T get(); // Cardinality: 1
    Optional<T> getAny(); // Cardinality: 0..1
    List<T> getAll();
    List<T> getAll(RepositoryPage<T> pageToList);
}
