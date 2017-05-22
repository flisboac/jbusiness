package flisboac.jbusiness.entities.specifications;

import flisboac.jbusiness.entities.specifications.composition.*;
import flisboac.jbusiness.util.FluentBuilder;

public final class Specify<T> implements FluentBuilder<Specification<T>> {

    private Specification<T> specification;

    public Specification<T> done() {
        return specification;
    }

    public static <T> Specify<T> from(Specification<T> specification) {
        Specify<T> builder = new Specify<>();
        builder.specification = IdentitySpecification.wrap(specification);
        return builder;
    }

    public Specify<T> and(Specification<? super T> specification) {
        this.specification = AndSpecification.byLhs(this.specification, specification);
        return this;
    }

    public Specify<T> or(Specification<? super T> specification) {
        this.specification = OrSpecification.byLhs(this.specification, specification);
        return this;
    }

    public Specify<T> not() {
        this.specification = NotSpecification.wrap(this.specification);
        return this;
    }

    public static <T> Specification<T> anything(Class<T> entityClass) {
        return new TrueSpecification<>();
    }

}
