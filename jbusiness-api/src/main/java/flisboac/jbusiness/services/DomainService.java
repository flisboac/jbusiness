package flisboac.jbusiness.services;

public interface DomainService extends Service {

	public <T> boolean canOperateOn(Class<T> entityClass);
	public <T> boolean canOperateOn(T entity);
}
