package flisboac.jbusiness.query.dsl;

public interface NumberOperand
	extends SingularOperand<Number>,
		ComparableOperand<Number>,
		SummableOperand<Number>,
		PrimitiveOperand<Number> {

	public Expression plus();
	public Expression minus();
	public Expression multiply(Number other);
	public Expression divide(Number other);
}
