package flisboac.jbusiness.util.beans;

public enum MemberType {

	INVALID(false, AccessMode.NORMAL, false, false),
	FIELD(true, AccessMode.NORMAL, true, true),
	ACCESSIBLE_FIELD(true, AccessMode.FORCED, true, true),
	GETTER(false, AccessMode.NORMAL, true, false),
	SETTER(false, AccessMode.NORMAL, false, true),
	ACCESSOR(false, AccessMode.NORMAL, true, true),
	ACCESSIBLE_GETTER(false, AccessMode.FORCED, true, false),
	ACCESSIBLE_SETTER(false, AccessMode.FORCED, false, true),
	ACCESSIBLE_ACCESSOR(false, AccessMode.FORCED, true, true);
	
	private final boolean fieldAccess;
	private final AccessMode accessMode;
	private final boolean readable;
	private final boolean writable;
	
	private MemberType(boolean fieldAccess, AccessMode accessMode, boolean readable, boolean writable) {
		this.fieldAccess = fieldAccess;
		this.accessMode = accessMode;
		this.readable = readable;
		this.writable = writable;
	}

	public boolean isValid() {
		return this != INVALID;
	}
	
	public boolean isField() {
		return fieldAccess;
	}

	public boolean isMethod() {
		return !fieldAccess;
	}
	
	public AccessMode getAccessMode() {
		return accessMode;
	}

	public boolean isReadable() {
		return readable;
	}

	public boolean isWritable() {
		return writable;
	}
	
	public boolean isAccessor() {
		return readable && writable;
	}

	public static MemberType findByFieldBasedAccess(AccessMode accessMode) {
		switch (accessMode) {
		case FORCED:
			return ACCESSIBLE_FIELD;
		case NORMAL:
			return FIELD;
		}
		return INVALID;
	}
}
