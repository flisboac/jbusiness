package flisboac.jbusiness.query.ast;

import java.lang.reflect.Member;
import java.util.List;

public interface ObjectPath {

	public static interface Component {
		
		public Member getMember();
	}
	
	public Object getRootSubject();
	public List<Component> getComponents();
	public <T> T getResolvedValue(Class<T> valueClass);
	@Override public String toString();
}
