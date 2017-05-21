package flisboac.jbusiness.specifications;

import flisboac.jbusiness.FluentBuilder;

public final class SpecificationBuilder<T> implements FluentBuilder<Specification<T>> {
	
	private Specification<T> specification;
	
	public Specification<T> done() {
		return specification;
	}
	
	public static <T> SpecificationBuilder<T> from(Specification<T> specification) {
		SpecificationBuilder<T> builder = new SpecificationBuilder<>();
		builder.specification = IdentitySpecification.wrap(specification);
		return builder;
	}
	
	public SpecificationBuilder<T> and(Specification<? super T> specification) {
		this.specification = AndSpecification.byLhs(this.specification, specification);
		return this;
	}
	
	public SpecificationBuilder<T> or(Specification<? super T> specification) {
		this.specification = OrSpecification.byLhs(this.specification, specification);
		return this;
	}
	
	public SpecificationBuilder<T> not() {
		this.specification = NotSpecification.wrap(this.specification);
		return this;
	}
}
