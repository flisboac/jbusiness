package flisboac.jbusiness.messaging.spi;

public interface MessageEndpoint {

	public <T extends MessageChannel> T getChannel(Class<T> channelClass);
}
