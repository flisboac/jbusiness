package flisboac.jbusiness.persistence;

import java.util.List;

import flisboac.jbusiness.specifications.Specification;
import flisboac.jbusiness.util.ordering.Ordering;
import flisboac.jbusiness.util.pagination.Page;
import flisboac.jbusiness.util.pagination.Pagination;

public interface SpecifiableRepository extends Repository {

	public <T> T findOne(Specification<T> spec); // 1
	public <T> T findAny(Specification<T> spec); // 0..1
	public <T> List<T> findAll(Specification<T> spec, Ordering ordering); // 0..n
	public <T> Page<T> findAll(Specification<T> spec, Ordering ordering, Pagination pagination); // 0..n
	
}
