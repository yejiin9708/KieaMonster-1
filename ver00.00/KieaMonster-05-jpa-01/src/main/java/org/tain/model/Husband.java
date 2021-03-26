package org.tain.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "husband")
public class Husband {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	private String firstName;
	private String lastName;
	private int age;
	
	@OneToOne(mappedBy = "husband")
	private Wife wife;
	
public Husband(){}
	
	public Husband(String firstName, String lastName, int age){
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
	}
	
	public Husband(int id, String firstName, String lastName, int age, Wife wife){
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
		this.wife = wife;
	}
	
	public void setId(int id){
		this.id = id;
	}
	
	public int getId(){
		return this.id;
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
	
	public void setWife(Wife wife){
		this.wife = wife;
	}
	
	public Wife getWife(){
		return this.wife;
	}
	
	public String toString(){
		String info = String.format("Husband:{'firstname':%s, 'lastname': %s, age: %d, wife: {'firstname': %s, 'lastname': %s, age: %d}}", 
				this.firstName, this.lastName, this.age, this.wife.getFirstName(), this.wife.getLastName(), this.wife.getAge());
		return info;
	}
}
