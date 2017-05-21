package flisboac.jbusiness.query.dsl;

public interface MultivaloredOperand<T> extends Operand<T> {

	public Expression isEmpty();
	public Expression isNotEmpty();
	
	public Expression contains(T value);
}
