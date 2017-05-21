package flisboac.jbusiness.query.ast;

public interface OperationNode extends NonTerminalNode {

	public Operator getOperator();
	public boolean isNegated();
}
