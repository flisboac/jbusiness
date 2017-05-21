package flisboac.jbusiness.query.ast;

import java.util.Map;

public interface AstContext {

	public Map<String, Object> getAliases();
	public Node getRootNode();
	public boolean execute();
	public boolean execute(Map<String, Object> aliases);
}
