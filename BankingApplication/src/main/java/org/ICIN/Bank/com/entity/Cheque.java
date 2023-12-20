package org.ICIN.Bank.com.entity;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Cheque {
	@Id
	private String chequeBookNumber;
	private String accountNumber;
	private String accountType;
	private Date chequeBookIssueDate;
	private int chequeBookStatus;
	public Cheque(String chequeBookNumber, String accountNumber, String accountType, Date chequeBookIssueDate,
			int chequeBookStatus) {
		super();
		this.chequeBookNumber = chequeBookNumber;
		this.accountNumber = accountNumber;
		this.accountType = accountType;
		this.chequeBookIssueDate = chequeBookIssueDate;
		this.chequeBookStatus = chequeBookStatus;
	}
	public Cheque() {
		// TODO Auto-generated constructor stub
	}
	public String getChequeBookNumber() {
		return chequeBookNumber;
	}
	public void setChequeBookNumber(String chequeBookNumber) {
		this.chequeBookNumber = chequeBookNumber;
	}
	public String getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	public String getAccountType() {
		return accountType;
	}
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	
	public Date getChequeBookIssueDate() {
		return chequeBookIssueDate;
	}
	public void setChequeBookIssueDate(Date chequeBookIssueDate) {
		this.chequeBookIssueDate = chequeBookIssueDate;
	}
	public int getChequeBookStatus() {
		return chequeBookStatus;
	}
	public void setChequeBookStatus(int chequeBookStatus) {
		this.chequeBookStatus = chequeBookStatus;
	}
	
	
	

}
