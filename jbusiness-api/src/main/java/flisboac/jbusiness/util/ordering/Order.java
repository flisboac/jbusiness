package flisboac.jbusiness.util.ordering;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import flisboac.jbusiness.FluentBuilder;
import flisboac.jbusiness.util.ordering.SimpleOrdering.Element;

public final class Order implements FluentBuilder<SimpleOrdering> {	
		
	private final List<SimpleOrdering.Element> elements = new ArrayList<>();
	
	private Order() {
	}
	
	public List<? extends Ordering.Element> getElements() {
		return Collections.unmodifiableList(elements);
	}
	
	public SimpleOrdering done() {
		return new SimpleOrdering(elements);
	}
	
	public Order and(Object property) {
		this.elements.add(new SimpleOrdering.Element(property, Sort.NATURAL));
		return this;
	}

	public Order and(Object property, Sort order) {
		this.elements.add(new SimpleOrdering.Element(property, order));
		return this;
	}
	
	public Order andAsc(Object property) {
		this.elements.add(new SimpleOrdering.Element(property, Sort.ASC));
		return this;
	}
	
	public Order andAscNullsFirst(Object property) {
		this.elements.add(new SimpleOrdering.Element(property, Sort.ASC_NULLS_FIRST));
		return this;
	}
	
	public Order andAscNullsLast(Object property) {
		this.elements.add(new SimpleOrdering.Element(property, Sort.ASC_NULLS_LAST));
		return this;
	}
	
	public Order andDesc(Object property) {
		this.elements.add(new SimpleOrdering.Element(property, Sort.DESC));
		return this;
	}

	public Order andDescNullsFirst(Object property) {
		this.elements.add(new SimpleOrdering.Element(property, Sort.DESC_NULLS_FIRST));
		return this;
	}
	
	public Order andDescNullsLast(Object property) {
		this.elements.add(new SimpleOrdering.Element(property, Sort.DESC_NULLS_LAST));
		return this;
	}
	
	public static Order by(Object property) {
		Order order = new Order();
		return order.and(property, Sort.NATURAL);
	}

	public static Order by(Object property, Sort sort) {
		Order order = new Order();
		return order.and(property, sort);
	}
	
	public static Order asc(Object property) {
		Order order = new Order();
		return order.andAsc(property);
	}
	
	public static Order ascNullsFirst(Object property) {
		Order order = new Order();
		return order.andAscNullsFirst(property);
	}
	
	public static Order ascNullsLast(Object property) {
		Order order = new Order();
		return order.andAscNullsLast(property);
	}
	
	public static Order desc(Object property) {
		Order order = new Order();
		return order.andDesc(property);
	}

	public static Order descNullsFirst(Object property) {
		Order order = new Order();
		return order.andDescNullsFirst(property);
	}
	
	public static Order descNullsLast(Object property) {
		Order order = new Order();
		return order.andDescNullsLast(property);
	}
	
	public static SimpleOrdering from(Element... elements) {
		return new SimpleOrdering(Arrays.asList(elements));
	}
	
	public static SimpleOrdering from(List<Element> elements) {
		return new SimpleOrdering(elements);
	}

	public static SimpleOrdering natural() {
		return new SimpleOrdering();
	}
	
	public static final Ordering NATURAL = natural();
}
