package flisboac.jbusiness.query.ast;

public enum Operator {
	
	/* Object */
	IS_NULL(1, true), // null entity.property | value(entity.getProperty()).isNull()
	
	/* Sizable */
	IS_EMPTY(1, true), // empty entity.property | value(entity.getProperty()).isEmpty()
	CONTAINS(2, true), // someValue in entity.property | value(entity.getProperty()).contains(someValue)
	SIZE(1, false), // size(entity.property) | value(entity.getCollection()).size()
	
	/* Pattern matching */
	LIKE(2, true), // entity.property like "..."
	LIKE_CASE_INSENSITIVE(2, true), // upper/lower(entity.property) like upper/lower("...")
	
	/* Arithmetic */
	PLUS(1, false),
	MINUS(1, false),
	ADD(2, false),
	SUBTRACT(2, false),
	MULTIPLY(2, false),
	DIVIDE(2, false),
	
	/* Comparison */
	LESS_THAN(2, true),
	LESS_OR_EQUAL(2, true),
	EQUAL(2, true),
	GREATER_OR_EQUAL(2, true),
	GREATER_THAN(2, true),
	BETWEEN(3, true),
	;
	
	private final int arity;
	private final boolean resolvable;
	
	private Operator(int arity, boolean resolvable) {
		this.arity = arity;
		this.resolvable = resolvable;
	}

	public int getArity() {
		return arity;
	}

	public boolean isResolvable() {
		return resolvable;
	}
	
}
