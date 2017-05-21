package flisboac.jbusiness.util.navigables;

public interface ValuedNavigable<V, T extends Navigable<T>> extends Navigable<T> {

	public V getValue();
}
