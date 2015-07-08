package study.hard.javalib.exception;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExceptionLogPrinter {
	private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionLogPrinter.class);

	private static final String START_MARK = "[";
	private static final String END_MARK = "] ";
	private static final String PARAMETER = ", Parameters : ";

	private static String makeString(String message, Object... params) {
		StringBuilder sb = new StringBuilder();
		sb.append(START_MARK);
		sb.append(message);
		sb.append(END_MARK);
		if (params != null) {
			sb.append(PARAMETER);
			sb.append(getParametersAsString(params));
		}

		return sb.toString();
	}

	public static void print(ErrorLogLevel logLevel, Throwable cause) {
		printByLogLevel(logLevel, makeString(cause.getMessage()), cause);
	}

	public static void print(ErrorLogLevel logLevel, Throwable cause, Object... params) {
		printByLogLevel(logLevel, makeString(cause.getMessage(), params), cause);
	}

	public static void print(ErrorLogLevel logLevel, String causeMessage, Object... params) {
		printByLogLevel(logLevel, makeString(causeMessage, params), null);
	}

	private static void printByLogLevel(ErrorLogLevel logLevel, String message, Throwable e) {
		switch (logLevel) {
			case DEBUG:
				LOGGER.debug(message, e);
				break;
			case INFO:
				LOGGER.info(message, e);
				break;
			case WARN:
				LOGGER.warn(message, e);
				break;
			case ERROR:
				LOGGER.error(message, e);
				break;
			default:
				break;
		}
	}

	private static String getParametersAsString(Object[] params) {
		return Arrays.toString(params);
	}
}