package org.tain.utils;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class JsonPrint implements JsonPrintImpl {
	
	private static JsonPrint instance = null;
	
	public synchronized static JsonPrint getInstance() {
		if (instance == null) {
			instance = new JsonPrint();
		}
		
		return instance;
	}
	
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	
	private JsonPrint() {
		if (this.objectMapper == null) {
			this.objectMapper = new ObjectMapper();
			this.objectMapper.registerModule(new JavaTimeModule());
			this.objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			//this.objectMapper.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, false);
		}
	}

	private ObjectMapper objectMapper = null;
	
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	
	@Override
	public synchronized String toJson(Object object) {
		try {
			return this.objectMapper.writeValueAsString(object);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "{}";
	}

	@Override
	public synchronized String toPrettyJson(Object object) {
		try {
			return this.objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(object);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "{}";
	}

	@Override
	public void printJson(Object object) {
		System.out.println("JSON >>>>> " + this.toJson(object));
	}

	@Override
	public void printPrettyJson(Object object) {
		System.out.println("PRETTY JSON >>>>> " + this.toPrettyJson(object));
	}
}
