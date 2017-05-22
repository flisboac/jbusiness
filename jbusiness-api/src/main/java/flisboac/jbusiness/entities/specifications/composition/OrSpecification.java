package flisboac.jbusiness.entities.specifications.composition;

import flisboac.jbusiness.entities.specifications.Specification;

public final class OrSpecification<T> implements Specification<T> {

    private final Specification<? super T> lhs;
    private final Specification<? super T> rhs;

    public OrSpecification() {
        this.lhs = null;
        this.rhs = null;
    }

    public OrSpecification(Specification<? super T> lhs, Specification<? super T> rhs) {
        this.lhs = lhs;
        this.rhs = rhs;
    }

    public static <T> OrSpecification<T> byEntity(Class<T> entityClass, Specification<? super T> lhs, Specification<? super T> rhs) {
        return new OrSpecification<>(lhs, rhs);
    }

    public static <T> OrSpecification<T> byLhs(Specification<T> lhs, Specification<? super T> rhs) {
        return new OrSpecification<>(lhs, rhs);
    }

    public static <T> OrSpecification<T> byRhs(Specification<? super T> lhs, Specification<T> rhs) {
        return new OrSpecification<>(lhs, rhs);
    }

    @Override
    public boolean isSatisfiableBy(T entity) {
        return this.lhs.isSatisfiableBy(entity) || this.rhs.isSatisfiableBy(entity);
    }

    public Specification<? super T> getLhs() {
        return lhs;
    }

    public Specification<? super T> getRhs() {
        return rhs;
    }

}
