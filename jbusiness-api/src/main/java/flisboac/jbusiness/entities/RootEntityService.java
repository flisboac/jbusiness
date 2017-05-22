package flisboac.jbusiness.entities;

public interface RootEntityService<T> extends EntityService {

    Class<T> getRootEntityClass();
}
