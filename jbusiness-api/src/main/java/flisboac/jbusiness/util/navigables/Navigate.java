package flisboac.jbusiness.util.navigables;

import java.io.Serializable;

import flisboac.jbusiness.Immutable;

public final class Navigate implements Serializable, Navigation, Immutable {
	private static final long serialVersionUID = 1L;
	
	private NavigationReference reference;
	private Number index;
	private NavigationOverflow overflow;

	private Navigate(NavigationReference reference, Number index, NavigationOverflow overflow) {
		this.reference = reference;
		this.index = index;
		this.overflow = overflow;
	}
	
	public static Navigate current() {
		return new Navigate(NavigationReference.CURRENT, null, NavigationOverflow.NATURAL);
	}
	
	public static Navigate first() {
		return new Navigate(NavigationReference.FIRST, null, NavigationOverflow.NATURAL);
	}
	
	public static Navigate last() {
		return new Navigate(NavigationReference.LAST, null, NavigationOverflow.NATURAL);
	}
	
	public static Navigate next() {
		return new Navigate(NavigationReference.NEXT, null, NavigationOverflow.NATURAL);
	}

	public static Navigate boundNext() {
		return new Navigate(NavigationReference.NEXT, null, NavigationOverflow.BOUND);
	}

	public static Navigate wrappedNext() {
		return new Navigate(NavigationReference.NEXT, null, NavigationOverflow.WRAPPED);
	}
	
	public static Navigate previous() {
		return new Navigate(NavigationReference.PREVIOUS, null, NavigationOverflow.NATURAL);
	}
	
	public static Navigate boundPrevious() {
		return new Navigate(NavigationReference.PREVIOUS, null, NavigationOverflow.BOUND);
	}
	
	public static Navigate wrappedPrevious() {
		return new Navigate(NavigationReference.PREVIOUS, null, NavigationOverflow.WRAPPED);
	}
	
	public static Navigate relative(Number amount) {
		return new Navigate(NavigationReference.RELATIVE, amount, NavigationOverflow.NATURAL);
	}

	public static Navigate boundRelative(Number amount) {
		return new Navigate(NavigationReference.RELATIVE, amount, NavigationOverflow.BOUND);
	}

	public static Navigate wrappedRelative(Number amount) {
		return new Navigate(NavigationReference.RELATIVE, amount, NavigationOverflow.WRAPPED);
	}
	
	public static Navigate absolute(Number index) {
		return new Navigate(NavigationReference.ABSOLUTE, index, NavigationOverflow.NATURAL);
	}

	public static Navigate boundAbsolute(Number index) {
		return new Navigate(NavigationReference.ABSOLUTE, index, NavigationOverflow.BOUND);
	}

	public static Navigate wrappedAbsolute(Number index) {
		return new Navigate(NavigationReference.ABSOLUTE, index, NavigationOverflow.WRAPPED);
	}
	
	@Override
	public NavigationReference getReference() {
		return reference;
	}

	@Override
	public Number getIndex() {
		return index;
	}
	
	@Override
	public NavigationOverflow getOverflow() {
		return overflow;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((index == null) ? 0 : index.hashCode());
		result = prime * result + ((reference == null) ? 0 : reference.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Navigate other = (Navigate) obj;
		if (index == null) {
			if (other.index != null)
				return false;
		} else if (!index.equals(other.index))
			return false;
		if (reference != other.reference)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Navigate [reference=" + reference + ", index=" + index + "]";
	}

	public static final Navigation CURRENT = current();
	public static final Navigation FIRST = first();
	public static final Navigation LAST = last();
	public static final Navigation NEXT = next();
	public static final Navigation BOUND_NEXT = boundNext();
	public static final Navigation WRAPPED_NEXT = wrappedNext();
	public static final Navigation PREVIOUS = previous();
	public static final Navigation BOUND_PREVIOUS = boundPrevious();
	public static final Navigation WRAPPED_PREVIOUS = wrappedPrevious();
}
