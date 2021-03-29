package org.tain.utils.enums;

@Deprecated
public enum _JsonPrintType {
	NORMAL("Normal"),
	STEP01("STEP01"),
	STEP02("STEP02"),
	STEP03("STEP03"),
	DEFAULT("DEFAULT");

	private String value;

	_JsonPrintType(String value) {
		this.value = value;
	}

	public String getValue() {
		return this.value;
	}

}
