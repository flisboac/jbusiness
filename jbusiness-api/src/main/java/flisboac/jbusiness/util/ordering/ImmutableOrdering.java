package flisboac.jbusiness.util.ordering;

import java.util.List;

import flisboac.jbusiness.Immutable;

public class ImmutableOrdering extends SimpleOrdering implements Immutable {

	public ImmutableOrdering() {
		super();
	}

	public ImmutableOrdering(List<? extends Ordering.Element> elements) {
		super(elements);
	}

	public ImmutableOrdering(ImmutableOrdering ordering) {
		super(ordering);
	}

	
}
