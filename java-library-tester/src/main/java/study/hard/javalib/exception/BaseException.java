package study.hard.javalib.exception;

import org.apache.commons.lang.ArrayUtils;

public class BaseException extends RuntimeException {
	private static final long serialVersionUID = -2389300611955610890L;

	private Object params[];
	private Throwable currentThrowable;

	public ErrorCode getErrorCode() {
		return ErrorCode.UNKNOWN;
	}

	public BaseException(String message) {
		super(message);
		if (getErrorCode().isPrintStackTrace()) {
			this.currentThrowable = fillInStackTrace();
		}
	}

	public BaseException(String message, Object... params) {
		super(message);
		this.params = params;
		if (getErrorCode().isPrintStackTrace()) {
			this.currentThrowable = fillInStackTrace();
		}
	}

	public BaseException(String message, Throwable cause) {
		super(message, cause);
		if (getErrorCode().isPrintStackTrace()) {
			this.currentThrowable = fillInStackTrace();
		}
	}

	public BaseException(String message, Throwable cause, Object... params) {
		super(message, cause);
		this.params = params;
		if (getErrorCode().isPrintStackTrace()) {
			this.currentThrowable = fillInStackTrace();
		}
	}

	public void printErrorLog(Object... errorParams) {
		if (params != null) {
			params = ArrayUtils.addAll(params, errorParams);
		} else {
			params = errorParams;
		}

		if (!getErrorCode().isPrintStackTrace()) {
			ExceptionLogPrinter.print(getErrorCode().getLogLevel(), getMessage(), params);
		} else if (params == null) {
			ExceptionLogPrinter.print(getErrorCode().getLogLevel(), currentThrowable);
		} else {
			ExceptionLogPrinter.print(getErrorCode().getLogLevel(), currentThrowable, params);
		}
	}

	public Object[] getParams() {
		return params;
	}
}
