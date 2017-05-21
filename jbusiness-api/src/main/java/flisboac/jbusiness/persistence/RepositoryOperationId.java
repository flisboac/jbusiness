package flisboac.jbusiness.persistence;

import flisboac.jbusiness.BusinessError;
import flisboac.jbusiness.Immutable;

public final class RepositoryOperationId
implements Immutable {

	public static enum Type {
		CUSTOM,
		SELECT_BY_ID,
		SELECT_BY_IDENTITY,
		INSERT,
		UPDATE,
		DELETE_BY_ID,
		DELETE_BY_IDENTITY
	}
	
	private final Type type;
	private final String name;
	private final int hash;
	
	private RepositoryOperationId(
			Type type, 
			String name) {
		super();
		this.type = type;
		this.name = name;
		this.hash = calculateHashCode();
		checkInvariants();
	}
	
	public RepositoryOperationId(RepositoryOperationId other) {
		this.type = other.type;
		this.name = other.name;
		this.hash = other.hash;
	}

	public static RepositoryOperationId selectById() {
		return new RepositoryOperationId(Type.SELECT_BY_ID, null);
	}
	
	public static RepositoryOperationId selectByIdentity() {
		return new RepositoryOperationId(Type.SELECT_BY_IDENTITY, null);
	}

	public static RepositoryOperationId insert() {
		return new RepositoryOperationId(Type.INSERT, null);
	}

	public static RepositoryOperationId update() {
		return new RepositoryOperationId(Type.UPDATE, null);
	}
	
	public static RepositoryOperationId deleteById() {
		return new RepositoryOperationId(Type.DELETE_BY_ID, null);
	}
	
	public static RepositoryOperationId deleteByIdentity() {
		return new RepositoryOperationId(Type.DELETE_BY_IDENTITY, null);
	}
	
	public static RepositoryOperationId custom(String name) {
		return new RepositoryOperationId(Type.CUSTOM, name);
	}
	
	private void checkInvariants() {
		if (this.type == null) {
			// TODO better exception throw
			throw new BusinessError("Type cannot be null");
		}
		if (this.type == Type.CUSTOM && (this.name == null || this.name.isEmpty())) {
			// TODO better exception throw
			throw new BusinessError("Name must be provided if the operation's type is custom.");
		}
	}
	
	public int calculateHashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}
	
	public Type getType() {
		return type;
	}

	public String getName() {
		return name;
	}
	
	@Override
	public int hashCode() {
		return this.hash;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RepositoryOperationId other = (RepositoryOperationId) obj;
		if (type != other.type) {
			return false;
		} else {
			if (type == Type.CUSTOM && !name.equals(other.name)) {
				return false;
			}
		}
		return true;
	}

	public static final RepositoryOperationId SELECT_BY_ID = selectById();
	public static final RepositoryOperationId SELECT_BY_IDENTITY = selectByIdentity();
	public static final RepositoryOperationId INSERT = insert();
	public static final RepositoryOperationId UPDATE = update();
	public static final RepositoryOperationId DELETE_BY_ID = deleteById();
	public static final RepositoryOperationId DELETE_BY_IDENTITY = deleteByIdentity();
}
