package flisboac.jbusiness.query.ast;

import java.util.Map;

import flisboac.jbusiness.BusinessError;

public interface Node {

	public AstContext getContext();
	public Node getParent();
	public boolean execute(Map<String, Object> aliases) throws BusinessError;
}
