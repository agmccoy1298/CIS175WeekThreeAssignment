package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="contacts")
/**
 * @author andrewmccoy - agmccoy
 * CIS175 - Fall 2021
 * Jan 29, 2023
 */
public class Contacts {
	@Id
	@GeneratedValue
	//vars
	@Column(name="ID")
	private int id;
	@Column(name="NAME")
	private String name;
	@Column(name="PHONENUMBER")
	private String phoneNumber;
	@Column(name="ADDRESS")
	private String address;
	
	//constructors
	public Contacts() {
		super();
	}
	public Contacts(String _name, String _phoneNumber, String _address) {
		this.name = _name;
		this.phoneNumber = _phoneNumber;
		this.address = _address;
	}
	
	//getters and setters
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	//helper methods
	public String returnContactInfo() {		
		return "Name : " + this.name + ", Phone Number: "
				+ this.phoneNumber + ", Address: " + this.address;
	}
	
}
