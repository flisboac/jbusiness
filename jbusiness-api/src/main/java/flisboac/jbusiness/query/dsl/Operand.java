package flisboac.jbusiness.query.dsl;

public interface Operand<T> {
	
	public Class<T> getValueClass();
	
	public Expression isNull();
	public Expression isNotNull();

	public <V extends T> Expression equal(V value);
	public <V extends T> Expression notEqual(V value);
}
