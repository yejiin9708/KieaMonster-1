package org.tain.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "wife")
public class Wife {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	private String firstName;
	private String lastName;
	private int age;
	
	
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "husband_id")
	private Husband husband;
	
	public Wife(){}
	
	public Wife(String firstNane, String lastName, int age){
		this.firstName = firstNane;
		this.lastName = lastName;
		this.age = age;
	}
	
	public Wife(int id, String firstName, String lastName, int age, Husband husband){
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
		this.husband = husband;
	}
	
	public void setId(int id){
		this.id = id;
	}
	
	public int getId(){
		return this.id;
	}
	
	
	public void setHusband(Husband husband){
		this.husband = husband;
	}
	
	public void setFirstName(String firstName){
		this.firstName = firstName;
	}
	
	public String getFirstName(){
		return this.firstName;
	}
	
	public void setLastName(String lastName){
		this.lastName = lastName;
	}
	
	public String getLastName(){
		return this.lastName;
	}
	
	public void setAge(int age){
		this.age = age;
	}
	
	public int getAge(){
		return this.age;
	}
	
	public Husband getHusband(){
		return this.husband;
	}
	
	public String toString(){
		String info = String.format("Wife: {'firstname': %s, 'lastname': %s, age: %d, husband: {'firstname': %s, 'lastname': %s, age: %d}}", 
						this.firstName, this.lastName, this.age, this.husband.getFirstName(), this.husband.getLastName(), this.husband.getAge());
		return info;
	}
}
