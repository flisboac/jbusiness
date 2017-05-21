package flisboac.jbusiness.persistence;

import java.util.List;

import flisboac.jbusiness.util.ordering.Ordering;
import flisboac.jbusiness.util.pagination.Page;
import flisboac.jbusiness.util.pagination.Pagination;

public interface ViewRepository extends Repository {
	
	public <T> boolean isPersisted(T entity);
	
	public <T> boolean exists(T entity);
	public <ID> boolean existsById(ID id);
	
	public <T> T findById(Object id);
	public <T> T findAny(T entity); // 0..1
	public <T> T findOne(T entity); // 1
	public <T> List<T> findAll(Ordering ordering); // 0..n
	
	public <T> Page<T> paginateAll(Ordering ordering, Pagination config); // 0..n
}
