package flisboac.jbusiness.resources;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

@Target({ElementType.TYPE, ElementType.FIELD})
public @interface BusinessResource {

	public String localeName() default "";
	public String bundleName() default "";
	public Class<?> resourceClass() default Void.class;
	public int arity() default -1;
	public String name() default "";
	public String groupName() default "";
	public String resourceName() default "";
}
