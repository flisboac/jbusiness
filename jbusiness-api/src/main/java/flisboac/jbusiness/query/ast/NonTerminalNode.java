package flisboac.jbusiness.query.ast;

import java.util.List;

public interface NonTerminalNode extends Node {

	public List<Node> getChildren();
}
