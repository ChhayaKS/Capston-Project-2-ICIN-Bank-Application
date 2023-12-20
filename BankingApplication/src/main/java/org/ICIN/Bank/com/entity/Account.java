package org.ICIN.Bank.com.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Account {
	@Id
	private String accountNumber;
	private String userName;
	private String accountCIFNumber;
	private String branch;
	private int netBanking;
	private double balancePrimary;
	private double balanceSavings;
	
	
	public Account( String accountNumber ,String userName, String accountCIFNumber, String branch, int netBanking,
			double balancePrimary, double balanceSavings) {
		super();
		this.accountNumber=accountNumber;
		this.userName = userName;
		this.accountCIFNumber = accountCIFNumber;
		this.branch = branch;
		this.netBanking = netBanking;
		this.balancePrimary = balancePrimary;
		this.balanceSavings = balanceSavings;
	}
	public Account() {
		// TODO Auto-generated constructor stub
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
	public String getAccountCIFNumber() {
		return accountCIFNumber;
	}
	public void setAccountCIFNumber(String accountCIFNumber) {
		this.accountCIFNumber = accountCIFNumber;
	}
	public String getBranch() {
		return branch;
	}
	public void setBranch(String branch) {
		this.branch = branch;
	}
	public int getNetBanking() {
		return netBanking;
	}
	public void setNetBanking(int netBanking) {
		this.netBanking = netBanking;
	}
	public double getBalancePrimary() {
		return balancePrimary;
	}
	public void setBalancePrimary(double balancePrimary) {
		this.balancePrimary = balancePrimary;
	}
	public double getBalanceSavings() {
		return balanceSavings;
	}
	public void setBalanceSavings(double balanceSavings) {
		this.balanceSavings = balanceSavings;
	}
	
		
}
