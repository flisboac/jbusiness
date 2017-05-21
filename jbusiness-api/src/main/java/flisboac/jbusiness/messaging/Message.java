package flisboac.jbusiness.messaging;

import java.util.List;
import java.util.Optional;

public interface Message {
	
	public Address getMessageId();
	
	public Optional<Address> getDestinationAddress();
	
	public Optional<Address> getSourceMessageId();
	
	public List<Address> getReferencedMessageIds();
	
	@Override public boolean equals(Object other);
	
	@Override public int hashCode();
}
