package flisboac.jbusiness.entities.specifications.composition;

import flisboac.jbusiness.entities.specifications.Specification;

public final class TrueSpecification<T> implements Specification<T> {

    @Override
    public boolean isSatisfiableBy(T entity) {
        return true;
    }
}
