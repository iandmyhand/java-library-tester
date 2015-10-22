package study.hard.javalib.lang;

import java.io.UnsupportedEncodingException;

import org.apache.commons.lang.StringUtils;

public class URLUtils {

	public static boolean isURLEncoded(String str) {
		if (StringUtils.isBlank(str)) {
			return false;
		}
		try {
			if (StringUtils.equals(str, java.net.URLDecoder.decode(str, "UTF-8"))) {
				return false;
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
}
