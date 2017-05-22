package flisboac.jbusiness.entities.repositories;

import flisboac.jbusiness.entities.specifications.Specification;
import flisboac.jbusiness.util.ordering.Ordering;
import flisboac.jbusiness.util.projections.Projection;
import flisboac.jbusiness.util.pagination.Page;
import flisboac.jbusiness.util.pagination.Pagination;

public interface RepositoryPage<T> extends Page<T> {

    Class<T> getEntityClass(); // The root type of result that'll be returned. Always an entity because Repositories work based on aggregate roots.
    Projection<T> getProjection(); // The "SELECT": What I want to be returned
    Specification<T> getSpecification(); // The "WHERE": What I want to search for
    Ordering getOrdering(); // The "ORDER BY": In which order I want my results to be presented
    @Override Pagination getPagination(); // The "LIMIT": How many results I want to be returned
}
