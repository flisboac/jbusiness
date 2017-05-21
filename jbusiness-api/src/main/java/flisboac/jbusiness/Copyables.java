package flisboac.jbusiness;

public class Copyables {

	private Copyables() {}
	
	public static <T extends Copyable<T>> T copy(T entity) {
		return entity.copy();
	}
	
	public static <T extends Duplicable<T>> T duplicate(T entity) {
		return entity.duplicate();
	}
}
