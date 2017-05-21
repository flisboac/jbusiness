package flisboac.jbusiness.messaging;

import java.util.concurrent.Future;

public interface MessageHandler {
	
	public <T extends Message> Future<Boolean> send(T message); // Sends immediately and asynchronously
	
}
