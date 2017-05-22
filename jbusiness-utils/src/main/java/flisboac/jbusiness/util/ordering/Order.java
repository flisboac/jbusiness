package flisboac.jbusiness.util.ordering;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import flisboac.jbusiness.util.ordering.impl.SimpleOrderingImpl;
import flisboac.jbusiness.util.FluentBuilder;

public final class Order implements FluentBuilder<SimpleOrderingImpl> {

    private final List<SimpleOrderingImpl.Element> elements = new ArrayList<>();

    private Order() {
    }

    public List<? extends OrderingElement> getElements() {
        return Collections.unmodifiableList(elements);
    }

    public SimpleOrderingImpl done() {
        return new SimpleOrderingImpl(elements);
    }

    public Order and(Object property) {
        this.elements.add(new SimpleOrderingImpl.Element(property, Sort.NATURAL));
        return this;
    }

    public Order and(Object property, Sort order) {
        this.elements.add(new SimpleOrderingImpl.Element(property, order));
        return this;
    }

    public Order andAsc(Object property) {
        this.elements.add(new SimpleOrderingImpl.Element(property, Sort.ASC));
        return this;
    }

    public Order andAscNullsFirst(Object property) {
        this.elements.add(new SimpleOrderingImpl.Element(property, Sort.ASC_NULLS_FIRST));
        return this;
    }

    public Order andAscNullsLast(Object property) {
        this.elements.add(new SimpleOrderingImpl.Element(property, Sort.ASC_NULLS_LAST));
        return this;
    }

    public Order andDesc(Object property) {
        this.elements.add(new SimpleOrderingImpl.Element(property, Sort.DESC));
        return this;
    }

    public Order andDescNullsFirst(Object property) {
        this.elements.add(new SimpleOrderingImpl.Element(property, Sort.DESC_NULLS_FIRST));
        return this;
    }

    public Order andDescNullsLast(Object property) {
        this.elements.add(new SimpleOrderingImpl.Element(property, Sort.DESC_NULLS_LAST));
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

    public static SimpleOrderingImpl from(SimpleOrderingImpl.Element... elements) {
        return new SimpleOrderingImpl(Arrays.asList(elements));
    }

    public static SimpleOrderingImpl from(List<SimpleOrderingImpl.Element> elements) {
        return new SimpleOrderingImpl(elements);
    }

    public static SimpleOrderingImpl natural() {
        return new SimpleOrderingImpl();
    }

    public static final Ordering NATURAL = natural();
}
