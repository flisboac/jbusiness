package flisboac.jbusiness.entities.specifications.composition;

import flisboac.jbusiness.entities.specifications.Specification;

public final class IdentitySpecification<T> implements Specification<T> {

    private final Specification<? super T> specification;

    public IdentitySpecification() {
        this.specification = null;
    }

    public IdentitySpecification(Specification<? super T> specification) {
        this.specification = specification;
    }

    public static <T> IdentitySpecification<T> wrap(Class<T> entityClass, Specification<? super T> specification) {
        return new IdentitySpecification<T>(specification);
    }

    public static <T> IdentitySpecification<T> wrap(Specification<T> specification) {
        return new IdentitySpecification<>(specification);
    }

    @Override
    public boolean isSatisfiableBy(T entity) {
        return this.specification.isSatisfiableBy(entity);
    }

    public Specification<? super T> getSpecification() {
        return specification;
    }

}
