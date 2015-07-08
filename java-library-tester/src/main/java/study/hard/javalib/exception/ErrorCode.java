package study.hard.javalib.exception;

public enum ErrorCode {

	INVALID_PARAMETERS(1001, "You're request contains invalid parameters.", ErrorLogLevel.INFO, false),
	UNKNOWN(9999, "An unknown error occurred.", ErrorLogLevel.ERROR);

	private final int code;
	private final String message;
	private final ErrorLogLevel logLevel;
	private final boolean isPrintStackTrace;

	private ErrorCode(int code, String message, ErrorLogLevel logLevel) {
		this.code = code;
		this.message = message;
		this.logLevel = logLevel;
		this.isPrintStackTrace = true;
	}

	private ErrorCode(int code, String message, ErrorLogLevel logLevel, boolean isPrintStackTrace) {
		this.code = code;
		this.message = message;
		this.logLevel = logLevel;
		this.isPrintStackTrace = isPrintStackTrace;
	}

	public int getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}

	public ErrorLogLevel getLogLevel() {
		return logLevel;
	}

	public static ErrorCode convertToErrorCodeFromCode(Integer code) {
		if (code == null) {
			return null;
		}
		for (ErrorCode errorCode : ErrorCode.values()) {
			if (errorCode.getCode() == code) {
				return errorCode;
			}
		}
		return null;
	}

	public boolean isPrintStackTrace() {
		return isPrintStackTrace;
	}
}
