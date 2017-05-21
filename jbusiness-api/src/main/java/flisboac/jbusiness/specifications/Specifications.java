package flisboac.jbusiness.specifications;

public class Specifications {

	private Specifications() {
		super();
	}

	public static <T> SpecificationBuilder<T> from(Specification<T> specification) {
		return SpecificationBuilder.from(specification);
	}
}
