package org.tain.utils;

public interface JsonPrintImpl {

	public String toJson(Object object);
	public String toPrettyJson(Object object);
	public void printJson(Object object);
	public void printPrettyJson(Object object);
}
