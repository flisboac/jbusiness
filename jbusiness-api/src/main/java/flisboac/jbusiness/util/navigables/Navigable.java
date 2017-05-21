package flisboac.jbusiness.util.navigables;

public interface Navigable<T extends Navigable<T>> {

	public boolean isAt(Navigation ref) throws UnsupportedOperationException;
	public boolean isNavigableTo(Navigation ref);
	public T navigateTo(Navigation ref) throws InvalidNavigationError;
}
