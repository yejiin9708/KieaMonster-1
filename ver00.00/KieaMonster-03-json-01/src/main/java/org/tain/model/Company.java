package org.tain.model;

import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class Company {

	private int id;
	private String name;
	
	private List<Product> products;
	
	public void setId(int id){
		this.id = id;
	}
	
	public int getId(){
		return this.id;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public String getName(){
		return this.name;
	}
	
	public void setProducts(List<Product> products){
		this.products = products;
	}
	
	public List<Product> getProducts(){
		return this.products;
	}
	
	public Company(int id, String name, List<Product> products){
		this.id = id;
		this.name = name;
		this.products = products;
	}
	
	@Override
	public String toString() {
		ObjectMapper mapper = new ObjectMapper();
		
		String jsonString = "";
		try {
			mapper.enable(SerializationFeature.INDENT_OUTPUT);
			jsonString = mapper.writeValueAsString(this);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		return jsonString;
	}
}
