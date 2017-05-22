package flisboac.jbusiness.entities;

public interface EntityIdentity<T> {

    Class<T> getEntityClass();
    boolean isIdentified();
    @Override boolean equals(Object other);
    @Override int hashCode();
}
