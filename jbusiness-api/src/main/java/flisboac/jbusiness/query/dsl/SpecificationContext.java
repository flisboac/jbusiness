package flisboac.jbusiness.query.dsl;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Optional;

import flisboac.jbusiness.entities.Entity;

public interface SpecificationContext {

	public <T> T subject(Class<T> entity);
	public <T> T alias(Class<T> entity, Optional<String> name);
	public <T> T alias(T entity, Optional<String> name);

	public Expression value(Boolean value);
	public <V extends Number> NumberOperand value(V value);
	public StringOperand value(String value);
	public <V extends Enum<V>> EnumOperand<V> value(V value);
	public <V> ArrayOperand<V> value(V[] value);
	public TemporalOperand<Date> value(Date value);
	public TemporalOperand<Calendar> value(Calendar value);
	public <V, C extends Collection<V>> CollectionOperand<V, C> value(C value);
	public <V extends Entity> EntityOperand<V> value(V value);
	public <V> Operand<V> value(V value);
	
	Expression getExpression();
}
