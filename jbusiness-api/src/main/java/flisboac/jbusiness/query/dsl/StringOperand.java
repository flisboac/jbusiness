package flisboac.jbusiness.query.dsl;

import java.util.Optional;

public interface StringOperand
		extends SingularOperand<String>,
		PrimitiveOperand<String>,
		SizedOperand<String> {
	
	public Expression isEmpty();
	public Expression isNotEmpty();

	// TODO Remove the Optional<Boolean> and add a proper class/enum to represent case sensitivity
	public Expression like(String pattern, Optional<Boolean> caseSensitive);
	public Expression notLike(String pattern, Optional<Boolean> caseSensitive);
	
	public Expression like(StringOperand pattern, Optional<Boolean> caseSensitive);
	public Expression notLike(StringOperand pattern, Optional<Boolean> caseSensitive);
}
