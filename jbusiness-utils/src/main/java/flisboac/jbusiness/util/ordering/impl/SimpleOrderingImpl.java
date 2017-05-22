package flisboac.jbusiness.util.ordering.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import flisboac.jbusiness.util.ordering.Ordering;
import flisboac.jbusiness.util.ordering.OrderingElement;
import flisboac.jbusiness.util.ordering.Sort;
import flisboac.jbusiness.util.Copyable;

public class SimpleOrderingImpl implements Ordering, Copyable<SimpleOrderingImpl> {

    public static final class Element implements OrderingElement, Copyable<Element> {
        private final Object property;
        private final Sort sort;

        public Element() {
            super();
            this.property = null;
            this.sort = null;
        }

        public Element(OrderingElement element) {
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

    private final List<SimpleOrderingImpl.Element> elements = new ArrayList<>();

    public SimpleOrderingImpl() {
        super();
    }

    public SimpleOrderingImpl(SimpleOrderingImpl ordering) {
        super();
        this.elements.addAll(ordering.elements);
    }

    public SimpleOrderingImpl(List<? extends OrderingElement> elements) {
        super();
        for (OrderingElement element : elements) {
            this.elements.add(new Element(element));
        }
    }

    public List<SimpleOrderingImpl.Element> getElements() {
        return Collections.unmodifiableList(this.elements);
    }

    public boolean isNatural() {
        return this.elements.isEmpty();
    }

    public SimpleOrderingImpl copy() {
        return new SimpleOrderingImpl(this);
    }
}
