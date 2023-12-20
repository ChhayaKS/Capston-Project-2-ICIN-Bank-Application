package org.ICIN.Bank.com.repository;

import org.springframework.stereotype.Repository;

@Repository
public class FrontendTransaction {
	private Long id;

	private String fromAccountNumber;

	private String toAccountNumber;

	private String fromUserName;

	private String toUserName;

	private String fromAccountType;

	private String toAccountType;

	private double transferAmount;
	
	private String transferMessage;

	private String transferType;

	private String transferDate;

	private int transferStatus;

	public FrontendTransaction() {
		// TODO Auto-generated constructor stub
	}

	public FrontendTransaction(Long id, String fromAccountNumber, String toAccountNumber, String fromUserName,
			String toUserName, String fromAccountType, String toAccountType, double transferAmount,
			String transferMessage, String transferType, String transferDate, int transferStatus) {
		super();
		this.id = id;
		this.fromAccountNumber = fromAccountNumber;
		this.toAccountNumber = toAccountNumber;
		this.fromUserName = fromUserName;
		this.toUserName = toUserName;
		this.fromAccountType = fromAccountType;
		this.toAccountType = toAccountType;
		this.transferAmount = transferAmount;
		this.transferMessage = transferMessage;
		this.transferType = transferType;
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

	public double getTransferAmount() {
		return transferAmount;
	}

	public void setTransferAmount(double transferAmount) {
		this.transferAmount = transferAmount;
	}

	public String getTransferMessage() {
		return transferMessage;
	}

	public void setTransferMessage(String transferMessage) {
		this.transferMessage = transferMessage;
	}

	public String getTransferType() {
		return transferType;
	}

	public void setTransferType(String transferType) {
		this.transferType = transferType;
	}

	public String getTransferDate() {
		return transferDate;
	}

	public void setTransferDate(String transferDate) {
		this.transferDate = transferDate;
	}

	public int getTransferStatus() {
		return transferStatus;
	}

	public void setTransferStatus(int transferStatus) {
		this.transferStatus = transferStatus;
	}

	
}
