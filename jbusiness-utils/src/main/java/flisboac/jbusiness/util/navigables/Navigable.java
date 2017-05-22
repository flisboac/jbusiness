package flisboac.jbusiness.util.navigables;

public interface Navigable<T extends Navigable<T>> {

    boolean isAt(Navigation ref) throws UnsupportedOperationException;
    boolean isNavigableTo(Navigation ref);
    T navigateTo(Navigation ref) throws IllegalStateException, IllegalArgumentException;
}
