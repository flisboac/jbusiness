package flisboac.jbusiness.query.dsl;

public interface SummableOperand<T> extends Operand<T> {

	public Expression add(T other);
	public Expression sub(T other);
	
	public Expression add(SummableOperand<T> other);
	public Expression sub(SummableOperand<T> other);
}
