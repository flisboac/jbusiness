package flisboac.jbusiness.query.dsl;

public interface ComparableOperand<T> extends Operand<T> {

	public Expression lt(T value);
	public Expression le(T value);
	public Expression eq(T value);
	public Expression ne(T value);
	public Expression ge(T value);
	public Expression gt(T value);

	public Expression lt(ComparableOperand<T> value);
	public Expression le(ComparableOperand<T> value);
	public Expression eq(ComparableOperand<T> value);
	public Expression ne(ComparableOperand<T> value);
	public Expression ge(ComparableOperand<T> value);
	public Expression gt(ComparableOperand<T> value);
	

	public Expression between(T floor, T ceil);
	public Expression between(ComparableOperand<T> floor, T ceil);
	public Expression between(T floor, ComparableOperand<T> ceil);
	public Expression between(ComparableOperand<T> floor, ComparableOperand<T> ceil);
	
	public Expression notBetween(T floor, T ceil);
	public Expression notBetween(ComparableOperand<T> floor, T ceil);
	public Expression notBetween(T floor, ComparableOperand<T> ceil);
	public Expression notBetween(ComparableOperand<T> floor, ComparableOperand<T> ceil);
}
