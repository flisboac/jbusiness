package flisboac.jbusiness.specifications;

public final class NotSpecification<T> implements Specification<T> {

	private final Specification<? super T> specification;
	
	public NotSpecification() {
		this.specification = null;
	}

	public NotSpecification(Specification<? super T> specification) {
		this.specification = specification;
	}

	public static <T> NotSpecification<T> wrap(Class<T> entityClass, Specification<? super T> specification) {
		return new NotSpecification<T>(specification);
	}
	
	public static <T> NotSpecification<T> wrap(Specification<T> specification) {
		return new NotSpecification<>(specification);
	}
	
	@Override
	public boolean isSatisfiableBy(T entity) {
		return !this.specification.isSatisfiableBy(entity);
	}

	public Specification<? super T> getSpecification() {
		return specification;
	}
	
}
