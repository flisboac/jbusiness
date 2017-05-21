package flisboac.jbusiness.query.ast;

public interface TerminalNode<T> extends Node {
	
	public static enum Type {
		IMMEDIATE,
		OBJECT_PATH
	};
	
	public Type getType();
	@Override public String toString();
	public T getValue();
}
