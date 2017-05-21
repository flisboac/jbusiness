package flisboac.jbusiness.query.dsl;

public interface BooleanOperand
		extends SingularOperand<Boolean>,
		PrimitiveOperand<Boolean> {

	public Expression not();
}
