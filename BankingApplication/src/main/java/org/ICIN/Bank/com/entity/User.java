package org.ICIN.Bank.com.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class User {
	@Id
//	@GeneratedValue(generator = "system-uuid")
//	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	private String accountNumber;
	private String userId;
	private String userName;
	private String password;
	private int accountIsBlocked;

	public User(String accountNumber, String userName, String password, int accountIsBlocked) {
		super();
		this.accountNumber = accountNumber;
		this.userName = userName;
		this.password = password;
		this.accountIsBlocked = accountIsBlocked;
	}

	public User() {
		// TODO Auto-generated constructor stub
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getAccountIsBlocked() {
		return accountIsBlocked;
	}

	public void setAccountIsBlocked(int accountIsBlocked) {
		this.accountIsBlocked = accountIsBlocked;
	}

}
