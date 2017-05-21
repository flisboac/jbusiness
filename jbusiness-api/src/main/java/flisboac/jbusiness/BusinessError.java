package flisboac.jbusiness;

public class BusinessError extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public BusinessError() {
		super();
	}

	protected BusinessError(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public BusinessError(String message, Throwable cause) {
		super(message, cause);
	}

	public BusinessError(String message) {
		super(message);
	}

	public BusinessError(Throwable cause) {
		super(cause);
	}
	
}
