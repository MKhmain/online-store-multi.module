package org.example.entities.impl;

import org.example.utils.validator.Validate;
import org.example.entities.User;

public class DefaultUser implements User {


	public static int counter=0;
	private int id;
	@Validate(pattern = "[A-Z]{1}[a-z]+")
	private String firstName;
	@Validate(pattern = "[A-Z]{1}[a-z]+")
	private String lastName;
	private String password;
	@Validate(pattern = ".+@.+")
	private String email;

	private String roleName;
	private double money;
	private String creditCard;

	public DefaultUser() {
	}

	public DefaultUser(String firstName, String lastName, String password, String email) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		this.email = email;
	}

	public DefaultUser(Integer id, String firstName, String lastName, String password, String email) {
		this.id=id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		this.email = email;
	}

	public  int getCounter() {
		return counter;
	}
	public void setId(int id){
		this.id=id;
	}
	public static void setCounter(int counter) {
		DefaultUser.counter = counter;
	}

	{
		id=++counter;
	}
	@Override
	public String getFirstName() {

		return firstName;
	}

	@Override
	public String getLastName() {
		return lastName;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getEmail() {
		return email;
	}

	@Override
	public String toString() {
		return
				"FirstName='" + firstName + '\'' +
				", lastName='" + lastName + '\'' +
				", email='" + email + '\'';
	}

	@Override
	public void setPassword(String password) {
		if(password==null)
			return;
		this.password=password;
	}

	@Override
	public void setEmail(String newEmail) {
		if (newEmail == null) {
			return;
		}
		this.email = newEmail;
	}

	@Override
	public void setFirstName(String firstName) {
		this.firstName=firstName;
	}

	@Override
	public void setLastName(String lastName) {
		this.lastName=lastName;
	}

	@Override
	public String getRoleName() {
		return roleName;
	}

	@Override
	public void setRoleName(String roleName) {
		this.roleName=roleName;
	}

	@Override
	public double getMoney() {
		return money;
	}

	@Override
	public void setMoney(double money) {
		this.money=money;
	}

	@Override
	public String getCreditCard() {
		return creditCard;
	}

	@Override
	public void setCreditCard(String creditCard) {
		this.creditCard=creditCard;
	}

	@Override
	public int getId() {
		return id;
	}
	
	void clearState() {
		counter=0;
	}
}
