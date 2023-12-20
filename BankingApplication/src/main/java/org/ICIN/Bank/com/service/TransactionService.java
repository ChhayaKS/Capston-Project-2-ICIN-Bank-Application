package org.ICIN.Bank.com.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.ICIN.Bank.com.entity.Account;
import org.ICIN.Bank.com.entity.Transaction;
import org.ICIN.Bank.com.repository.AccountRepository;
import org.ICIN.Bank.com.repository.FrontendTransaction;
import org.ICIN.Bank.com.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {
	
	@Autowired
	private TransactionRepository transactionRepository;

	@Autowired
	private AccountRepository accountRepository;

	public String addTransaction(Transaction transaction) {
		List<Transaction> transactions = transactionRepository.getTransactionForAccountNumber(transaction.getFromAccountNumber());
		double amountPrimary = 0;
		double amountSaving = 0;
		for (Transaction temp : transactions) 
		{
			if(temp.getTransferStatus()==1)
			{
				if(temp.getFromAccountType().equals("primary"))
				{
					amountPrimary+=temp.getTransferAmount();
				}else {
					amountSaving+=temp.getTransferAmount();
				}
			}		
		}
		@SuppressWarnings("deprecation")
		Account accountSender=accountRepository.getOne(transaction.getFromAccountNumber());
		@SuppressWarnings("deprecation")
		Account accountReceiver=accountRepository.getOne(transaction.getToAccountNumber());
		boolean check=false;
		List<Account>acc=accountRepository.findAll();
		for(Account temp:acc)
		{
			if(temp.getAccountNumber().equals(transaction.getToAccountNumber()))
			{
				check=true;
			}
		}
		if(!check)
		{
			return "Transfer bank account does not exists!";
		}
		
		@SuppressWarnings("deprecation")
		Account myAccount = accountRepository.getOne(transaction.getFromAccountNumber());
		if (transaction.getFromAccountType().equals("Primary")) {
			if (myAccount.getBalancePrimary() - amountPrimary < transaction.getTransferAmount()) {
				return "You already have some pending transactions!\nYour primary account would not have that much balance if these transactions are permitted!";
			}
		} else {
			if (myAccount.getBalanceSavings() - amountSaving < transaction.getTransferAmount()) {
				return "You already have some pending transactions!\nYour savings account would not have that much balance if these transactions are permitted!";
			}
		}

//		Transaction finalTransaction = new Transaction(transaction.getFromAccountNumber(),
//				transaction.getToAccountNumber(), accountSender.getUserName(),
//				accountReceiver.getUserName(), transaction.getFromAccountType(),
//				transaction.getToAccountType(), transaction.getTransferAmount(), transaction.getTransferMessage(),
//				new Date(), transaction.getTransferStatus());
		
		Transaction finalTransaction=new Transaction(transaction.getFromAccountNumber(),transaction.getToAccountNumber(),accountSender.getUserName(),
				accountReceiver.getAccountNumber(),transaction.getFromAccountType(),transaction.getToAccountType(),transaction.getTransferAmount(),
				transaction.getTransferMessage(),new Date(), transaction.getTransferStatus());
		
		

		this.transactionRepository.save(finalTransaction);
		return "Transfer initiated.\nCheck the status in the transactions tab!";
	}

	
	@SuppressWarnings("deprecation")
	public String updateTransaction(Long id) {
		Transaction transaction = transactionRepository.getOne(id);
		
		Account receiverAccount = this.accountRepository.getOne(transaction.getToAccountNumber());
		Account senderAccount = this.accountRepository.getOne(transaction.getFromAccountNumber());
		
		if(transaction.getFromAccountType().equals("Primary")) {
			if(transaction.getToAccountType().equals("Primary")) {
				this.accountRepository.updatePrimaryBalance(
						receiverAccount.getBalancePrimary()+transaction.getTransferAmount(), 
						receiverAccount.getAccountNumber()
				);
			} else {
				this.accountRepository.updateSavingBalance(
						receiverAccount.getBalanceSavings()+transaction.getTransferAmount(), 
						receiverAccount.getAccountNumber()
				);
			}
			this.accountRepository.updatePrimaryBalance(
					senderAccount.getBalancePrimary()-transaction.getTransferAmount(), 
					senderAccount.getAccountNumber()
			);
		} else {
			if(transaction.getToAccountType().equals("Primary")) {
				this.accountRepository.updatePrimaryBalance(
						receiverAccount.getBalancePrimary()+transaction.getTransferAmount(), 
						receiverAccount.getAccountNumber()
				);
			} else {
				this.accountRepository.updateSavingBalance(
						receiverAccount.getBalanceSavings()+transaction.getTransferAmount(), 
						receiverAccount.getAccountNumber()
				);
			}
			this.accountRepository.updateSavingBalance(
					senderAccount.getBalanceSavings()-transaction.getTransferAmount(), 
					senderAccount.getAccountNumber()
			);
		}
		
		this.transactionRepository.updateTransaction(1, id);
		return "Transaction Permitted!";
	}

	
	public List<FrontendTransaction> getTransactionsForAccountNumber(String accountNumber) {
		List<Transaction> mainList =transactionRepository.getTransactionForAccountNumber(accountNumber);
		List<FrontendTransaction> toReturnList = new ArrayList<FrontendTransaction>();
		for (Transaction temp : mainList) {
			FrontendTransaction tempTransaction = new FrontendTransaction();
			tempTransaction.setId(temp.getId());
			tempTransaction.setFromAccountNumber(temp.getFromAccountNumber());
			tempTransaction.setToAccountNumber(temp.getToAccountNumber());
			tempTransaction.setFromUserName(temp.getFromUserName());
			tempTransaction.setToUserName(temp.getToUserName());
			tempTransaction.setFromAccountType(temp.getFromAccountType());
			tempTransaction.setToAccountType(temp.getToAccountType());
			tempTransaction.setTransferAmount(temp.getTransferAmount());
			tempTransaction.setTransferMessage(temp.getTransferMessage());
			tempTransaction.setTransferDate(temp.getTransferDate()+"");
			tempTransaction.setTransferStatus(temp.getTransferStatus());
			if (temp.getFromAccountNumber().equals(accountNumber)) {
				tempTransaction.setTransferType("Debited");
			} else {
				tempTransaction.setTransferType("Credited");
			}
			toReturnList.add(tempTransaction);
		}
		return toReturnList;
	}

	
	public List<Transaction> getAllPendingTransactions() {
		return this.transactionRepository.getPendingTransactions(0);
	}

	public List<FrontendTransaction> getFilteredTransactions(String accountNumber, String startDate, String endDate) {
		List<Transaction> mainList = this.transactionRepository
				.getFilteredTransactions(accountNumber, startDate, endDate);
		System.out.println(mainList);
		List<FrontendTransaction> toReturnList = new ArrayList<FrontendTransaction>();
		for (Transaction temp : mainList) {
			FrontendTransaction tempTransaction = new FrontendTransaction();
			tempTransaction.setId(temp.getId());
			tempTransaction.setFromAccountNumber(temp.getFromAccountNumber());
			tempTransaction.setToAccountNumber(temp.getToAccountNumber());
			tempTransaction.setFromUserName(temp.getFromUserName());
			tempTransaction.setToUserName(temp.getToUserName());
			tempTransaction.setFromAccountType(temp.getFromAccountType());
			tempTransaction.setToAccountType(temp.getToAccountType());
			tempTransaction.setTransferAmount(temp.getTransferAmount());
			tempTransaction.setTransferMessage(temp.getTransferMessage());
			tempTransaction.setTransferDate(temp.getTransferDate()+"");
			tempTransaction.setTransferStatus(temp.getTransferStatus());
			if (temp.getFromAccountNumber().equals(accountNumber)) {
				tempTransaction.setTransferType("Debited");
			} else {
				tempTransaction.setTransferType("Credited");
			}
			toReturnList.add(tempTransaction);
		}
		return toReturnList;
	}


	
	
	
}
