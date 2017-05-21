package flisboac.jbusiness.query.dsl;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

import flisboac.jbusiness.entities.Entity;

public interface Expression extends BooleanOperand {

	public Expression and(Boolean value);
	public Expression and(Expression value);
	public <V extends Number> NumberOperand and(V value);
	public <V extends Number> NumberOperand and(NumberOperand value);
	public StringOperand and(String value);
	public StringOperand and(StringOperand value);
	public <V extends Enum<V>> EnumOperand<V> and(V value);
	public <V extends Enum<V>> EnumOperand<V> and(EnumOperand<V> value);
	public <V> ArrayOperand<V> and(V[] value);
	public <V> ArrayOperand<V> and(ArrayOperand<V> value);
	public TemporalOperand<Date> and(Date date);
	public TemporalOperand<Calendar> and(Calendar calendar);
	public <T> TemporalOperand<T> and(TemporalOperand<T> calendar);
	public <V, C extends Collection<V>> CollectionOperand<V, C> and(C value);
	public <V, C extends Collection<V>> CollectionOperand<V, C> and(CollectionOperand<V, C> value);
	public <V extends Entity> EntityOperand<V> and(V value);
	public <V extends Entity> EntityOperand<V> and(EntityOperand<V> value);
	public <V> Operand<V> and(V value);
	public <V> Operand<V> and(Operand<V> value);
	

	public Expression or(Boolean value);
	public Expression or(Expression value);
	public <V extends Number> NumberOperand or(V value);
	public <V extends Number> NumberOperand or(NumberOperand value);
	public StringOperand or(String value);
	public StringOperand or(StringOperand value);
	public <V extends Enum<V>> EnumOperand<V> or(V value);
	public <V extends Enum<V>> EnumOperand<V> or(EnumOperand<V> value);
	public <V> ArrayOperand<V> or(V[] value);
	public <V> ArrayOperand<V> or(ArrayOperand<V> value);
	public TemporalOperand<Date> or(Date date);
	public TemporalOperand<Calendar> or(Calendar calendar);
	public <T> TemporalOperand<T> or(TemporalOperand<T> calendar);
	public <V, C extends Collection<V>> CollectionOperand<V, C> or(C value);
	public <V, C extends Collection<V>> CollectionOperand<V, C> or(CollectionOperand<V, C> value);
	public <V extends Entity> EntityOperand<V> or(V value);
	public <V extends Entity> EntityOperand<V> or(EntityOperand<V> value);
	public <V> Operand<V> or(V value);
	public <V> Operand<V> or(Operand<V> value);
	
	@Override
	public Expression not();
	
	public boolean evaluate();
}
