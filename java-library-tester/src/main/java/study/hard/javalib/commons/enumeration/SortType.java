package study.hard.javalib.commons.enumeration;

public enum SortType implements Code, Text {
	ASCENDING("ASC", "Ascending", 0),
	DESCENDING("DESC", "Descending", 1);

	private String code;
	private String text;
	private int ordering;

	SortType(String code, String text, int ordering) {
		this.code = code;
		this.text = text;
		this.ordering = ordering;
	}

	public String getCode() {
		return code;
	}

	public String getText() {
		return text;
	}

	public int getOrdering() {
		return ordering;
	}

}
