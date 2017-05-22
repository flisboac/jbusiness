package flisboac.jbusiness.entities;

public interface GenericEntityService extends EntityService {

    <T> boolean isOperableOn(Class<T> entityClass);
    <T> boolean isOperableOn(T entity);
}
