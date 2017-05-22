package flisboac.jbusiness.entities;

public interface ActiveEntityService<T> extends RootEntityService<T> {

    //
    // Entity data
    //

    Class<? extends T> getEntityClass(); // valid only when `isNew() || isPersisted()`

    //
    // State management
    //

    ActiveEntityService<T> reset();
    ActiveEntityService<T> from(T entity);
}
