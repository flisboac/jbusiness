package flisboac.jbusiness.messaging;

import java.net.URI;

public interface AddressResolver {

	public Class<?> findInterface(String interfaceName);
	public Address expandToScope(Address address, Address.Scope scope);
	public URI expandToEndpointUri(Address address);
}
