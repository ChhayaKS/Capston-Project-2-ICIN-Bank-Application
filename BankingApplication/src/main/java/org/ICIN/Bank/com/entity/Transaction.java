package org.ICIN.Bank.com.entity;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Transaction {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String fromAccountNumber;
	private String toAccountNumber;
	private String fromUserName;
	private String toUserName;
	private String fromAccountType;
	private String toAccountType;
	private String transferMessage;
	private double transferAmount;
	private Date transferDate;
	private int transferStatus;
	public Transaction() {
		// TODO Auto-generated constructor stub
	}
	public Transaction( String fromAccountNumber, String toAccountNumber, String fromUserName,
			String toUserName, String fromAccountType, String toAccountType, double transferAmount,
			String transferMessage, Date transferDate, int transferStatus) {
		super();
		this.fromAccountNumber = fromAccountNumber;
		this.toAccountNumber = toAccountNumber;
		this.fromUserName = fromUserName;
		this.toUserName = toUserName;
		this.fromAccountType = fromAccountType;
		this.toAccountType = toAccountType;
		this.transferMessage = transferMessage;
		this.transferAmount = transferAmount;
		this.transferDate = transferDate;
		this.transferStatus = transferStatus;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFromAccountNumber() {
		return fromAccountNumber;
	}
	public void setFromAccountNumber(String fromAccountNumber) {
		this.fromAccountNumber = fromAccountNumber;
	}
	public String getToAccountNumber() {
		return toAccountNumber;
	}
	public void setToAccountNumber(String toAccountNumber) {
		this.toAccountNumber = toAccountNumber;
	}
	public String getFromUserName() {
		return fromUserName;
	}
	public void setFromUserName(String fromUserName) {
		this.fromUserName = fromUserName;
	}
	public String getToUserName() {
		return toUserName;
	}
	public void setToUserName(String toUserName) {
		this.toUserName = toUserName;
	}
	public String getFromAccountType() {
		return fromAccountType;
	}
	public void setFromAccountType(String fromAccountType) {
		this.fromAccountType = fromAccountType;
	}
	public String getToAccountType() {
		return toAccountType;
	}
	public void setToAccountType(String toAccountType) {
		this.toAccountType = toAccountType;
	}
	public String getTransferMessage() {
		return transferMessage;
	}
	public void setTransferMessage(String transferMessage) {
		this.transferMessage = transferMessage;
	}
	public double getTransferAmount() {
		return transferAmount;
	}
	public void setTransferAmount(double transferAmount) {
		this.transferAmount = transferAmount;
	}
	public Date getTransferDate() {
		return transferDate;
	}
	public void setTransferDate(Date transferDate) {
		this.transferDate = transferDate;
	}
	public int getTransferStatus() {
		return transferStatus;
	}
	public void setTransferStatus(int transferStatus) {
		this.transferStatus = transferStatus;
	}
	
	
	
	

}
