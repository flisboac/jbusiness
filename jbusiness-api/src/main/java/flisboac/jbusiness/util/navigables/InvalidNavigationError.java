package flisboac.jbusiness.util.navigables;

import flisboac.jbusiness.BusinessError;

public class InvalidNavigationError extends BusinessError {
	private static final long serialVersionUID = 1380554259580340819L;

	private final Navigation location;

	public InvalidNavigationError(Navigation nav) {
		super("Could not navigate a page to the location " + nav.toString());
		this.location = nav;
	}
	
	public InvalidNavigationError(String message, Navigation nav, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		this.location = nav;
	}

	public InvalidNavigationError(String message, Navigation nav, Throwable cause) {
		super(message, cause);
		this.location = nav;
	}

	public InvalidNavigationError(String message, Navigation nav) {
		super(message);
		this.location = nav;
	}

	public InvalidNavigationError(Navigation nav, Throwable cause) {
		super(cause);
		this.location = nav;
	}

	public Navigation getLocation() {
		return location;
	}
}
