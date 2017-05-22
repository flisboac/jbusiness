package flisboac.jbusiness.resources;

public interface ResourceLocator {

    public <T> T locate(ResourceId resourceId, Class<T> resourceClass, Object... args);
    public <T> T load(Class<T> resourceClass, Object... args);
}
