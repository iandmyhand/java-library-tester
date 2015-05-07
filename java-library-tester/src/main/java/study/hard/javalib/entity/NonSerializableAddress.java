package study.hard.javalib.entity;

/**
 * Address class for non-serialize test.
 * @author han.seomgi@nhnent.com
 */
public class NonSerializableAddress {

	private boolean serializable;
	private String postalCode;
	private String raw;

	public NonSerializableAddress() {
		this.serializable = false;
	}

	public NonSerializableAddress(boolean serializable, String postalCode, String raw) {
		this.serializable = serializable;
		this.postalCode = postalCode;
		this.raw = raw;
	}

	public boolean isSerializable() {
		return serializable;
	}

	public void setSerializable(boolean serializable) {
		this.serializable = serializable;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getRaw() {
		return raw;
	}

	public void setRaw(String raw) {
		this.raw = raw;
	}

}
