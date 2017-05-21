package flisboac.jbusiness.services;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public interface Service {
	
	public static final List<String> RESERVED_NAMES = 
			Collections.unmodifiableList(
					Arrays.asList(new String[]{
							"~"
					})
			);
	
	public String getApplicationName();
	
	public String getServiceName();
}
