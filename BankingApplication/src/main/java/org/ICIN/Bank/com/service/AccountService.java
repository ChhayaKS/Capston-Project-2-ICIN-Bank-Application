package org.ICIN.Bank.com.service;

import java.util.List;

import org.ICIN.Bank.com.entity.Account;
import org.ICIN.Bank.com.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
public class AccountService {
	@Autowired
	private AccountRepository accountRepository;

	public List<Account> getAllAccounts() {
		return accountRepository.findAll();
	}

	public Account getAccount(String accountNumber) {
		List<Account> account = accountRepository.getAccountByAccountNumber(accountNumber);
		if (account.size() == 0) {
			return null;
		}
		return account.get(0);
	}

	public boolean isNetBankingReg(String accountNumber) {
		List<Account> account = this.accountRepository.findAll();
		for (Account accounts : account) {
			if (accounts.getAccountNumber().equals(accountNumber)) {
				if (accounts.getNetBanking() == 0) {
					return false;
				}
				return false;
			}
		}
		return false;
	}

	public Account addAccount(Account account) {
//		List<Account> account1 = accountRepository.findAll();
//		for (Account temp : account1) {
//			if (temp.getAccountNumber().equals(account.getAccountNumber())) {
//				return account;
//			} else {
//				this.accountRepository.save(account);
//			}
//		}
	accountRepository.save(account);
	return account;
	}

	public Account getAccountById(String accId) {
		return accountRepository.findById(accId).get();
	}

	@Transactional
	public Account updateAccountDetails(String accountNumber, Account account) {
		Account acc = new Account();
		acc.setAccountCIFNumber(account.getAccountCIFNumber());
		acc.setAccountNumber(account.getAccountNumber());
		acc.setUserName(account.getUserName());
		acc.setBalancePrimary(account.getBalancePrimary());
		acc.setBalanceSavings(account.getBalanceSavings());
		acc.setBranch(account.getBranch());
		acc.setNetBanking(account.getNetBanking());
		return accountRepository.save(acc);
	}

	@Transactional
	public String upadteAccountNetBankingStatus(int status, String accountNumber) {
		accountRepository.updateNetBankingStatus(1, accountNumber);
		return "Account is now net banking enable";
	}

	public List<Account> getAllUnregisterUser() {
		return accountRepository.getAllUnRegisteredUser(1);
	}

	public String depositPrimary(int balance, String accountNumber) {
		this.accountRepository.depositPrimary(balance, accountNumber);
		return "Deposit to primary account is successful";
	}

	public String depositSaving(int balance, String accountNumber) {
		this.accountRepository.depositSaving(balance, accountNumber);
		return "Deposit to savings account is successful";
	}

	public String withdrawPrimary(int balance, String accountNumber) {
		 accountRepository.withdrawPrimary(balance, accountNumber);
		return "Withdrawal from primary account is successful";
	}

	public String withdrawSaving(int balance, String accountNumber) {
		 accountRepository.withdrawSaving(balance, accountNumber);
		return "Withdrawal from savings account is successful";
	}

}
