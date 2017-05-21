package flisboac.jbusiness.util.ordering;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import flisboac.jbusiness.Copyable;

public class SimpleOrdering implements Ordering, Copyable<SimpleOrdering> {

	public static final class Element implements Ordering.Element, Copyable<Element> {
		private final Object property;
		private final Sort sort;
		
		public Element() {
			super();
			this.property = null;
			this.sort = null;
		}
		
		public Element(Ordering.Element element) {
			super();
			this.property = element.getProperty();
			this.sort = element.getSort();
		}

		public Element(Element element) {
			super();
			this.property = element.property;
			this.sort = element.sort;
		}
		
		public Element(Object property, Sort sort) {
			super();
			this.property = property;
			this.sort = sort;
		}

		public Object getProperty() {
			return property;
		}
		
		public Sort getSort() {
			return sort;
		}
		
		public Element copy() {
			return new Element(this);
		}
	}
	
	private final List<SimpleOrdering.Element> elements = new ArrayList<>();
	
	public SimpleOrdering() {
		super(); 
	}
	
	public SimpleOrdering(SimpleOrdering ordering) {
		super();
		this.elements.addAll(ordering.elements);
	}
	
	public SimpleOrdering(List<? extends Ordering.Element> elements) {
		super();
		for (Ordering.Element element : elements) {
			this.elements.add(new Element(element));
		}
	}
	
	public List<SimpleOrdering.Element> getElements() {
		return Collections.unmodifiableList(this.elements);
	}
	
	public boolean isNatural() {
		return this.elements.isEmpty();
	}
	
	public SimpleOrdering copy() {
		return new SimpleOrdering(this);
	}
}
