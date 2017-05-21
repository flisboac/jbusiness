package flisboac.jbusiness.util.beans;

import java.lang.reflect.Field;
import java.util.Optional;

import flisboac.jbusiness.BusinessError;

public class FieldPropertyDescriptor implements PropertyDescriptor {

	private final Object instance;
	private final Class<?> instanceClass;
	private final MemberType type;
	private final Field member;
	
	public FieldPropertyDescriptor() {
		this.instance = Optional.empty();
		this.instanceClass = Void.class;
		this.type = MemberType.INVALID;
		this.member = null;
	}

	public FieldPropertyDescriptor(
	        Class<?> instanceClass,
			String fieldName,
			AccessMode accessMode) {
		this.instance = null;
		this.instanceClass = instanceClass;
		this.type = MemberType.findByFieldBasedAccess(accessMode);
		this.member = lookupField(fieldName);
	}
	
	public FieldPropertyDescriptor(Object instance, 
			String fieldName, 
			AccessMode accessMode) {
		this.instance = instance;
		this.instanceClass = instance != null ? instance.getClass() : null;
		this.type = MemberType.findByFieldBasedAccess(accessMode);
		this.member = lookupField(fieldName);
	}
	
	private Field lookupField(String fieldName) {
		Field field;
		try {
			field = this.instanceClass.getDeclaredField(fieldName);
		} catch (NoSuchFieldException | SecurityException e) {
			// TODO make a better exception throw
			throw new BusinessError(e);
		}
		if (this.type.getAccessMode().isForced()) {
			field.setAccessible(true);
		}
		return field;
	}
	
	@Override
	public boolean isInstanceBased() {
		return instance != null;
	}

	@Override
	public Object getInstance() {
		return instance;
	}

	@Override
	public Class<?> getInstanceClass() {
		return instanceClass;
	}

	@Override
	public Object get() {
		return get(getInstance());
	}

	@Override
	public <T> T get(Class<T> returnClass) {
		return get(getInstance(), returnClass);
	}

	@Override
	public void set(Object value) {
		set(getInstance(), value);
	}

	@Override
	public Object get(Object instance) {
		return get(instance, Object.class);
	}

	@Override
	public <T> T get(Object instance, Class<T> returnClass) {
		Object value = null;
		try {
			value = member.get(instance);
		} catch (IllegalAccessException 
				| IllegalArgumentException e) {
			// TODO make a better exception throw
			throw new BusinessError(e);
		}
		return returnClass.cast(value);
	}

	@Override
	public void set(Object instance, Object value) {
		try {
			member.set(instance, value);
		} catch (IllegalAccessException 
				| IllegalArgumentException e) {
			// TODO make a better exception throw
			throw new BusinessError(e);
		}
	}

	@Override
	public String getName() {
		return member != null ? member.getName() : null;
	}

	@Override
	public String getMemberName() {
		return member.getName();
	}

	@Override
	public MemberType getType() {
		return type;
	}

	
}
