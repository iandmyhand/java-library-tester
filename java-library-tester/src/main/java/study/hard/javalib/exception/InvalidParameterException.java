package study.hard.javalib.exception;

public class InvalidParameterException extends BaseException {
	private static final long serialVersionUID = -7725119998228382441L;

	public InvalidParameterException(String message, Object... params) {
		super(message, params);
	}

	public ErrorCode getErrorCode() {
		return ErrorCode.INVALID_PARAMETERS;
	}

}
