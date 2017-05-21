package flisboac.jbusiness.query.ast;

import java.lang.reflect.Member;
import java.util.List;

public interface ObjectPathTerminalNode<T> extends TerminalNode<T> {

	public Object getPathRoot();
	public List<Member> getPropertyPath();
}
