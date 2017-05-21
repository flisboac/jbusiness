package flisboac.jbusiness.resources;

import java.util.Locale;

public interface ResourceId {

	public String getGroupName(); // By default, this.getClass().getName() for enums, or this.getClass().getPackage().getName() for other objects.
	public String getName(); // By default, this.name() for enums, or this.getClass().getSimpleName() for other objects.
	public String getFullName(); // By default, String.format("%s.%s", getGroupName(), getName())
	public String getBundleName();
	public Locale getLocale();
	public int getArity();
	public Class<?> getResourceClass(); // The resulting loaded resource's class
}
