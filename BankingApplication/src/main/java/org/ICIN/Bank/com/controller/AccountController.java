package org.ICIN.Bank.com.controller;

import java.util.List;

import org.ICIN.Bank.com.entity.Account;
import org.ICIN.Bank.com.entity.Message;
import org.ICIN.Bank.com.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/account")
@CrossOrigin("http://localhost:4200")
public class AccountController {

	@Autowired
	private AccountService accountService;

	@GetMapping("/accounts")
	public List<Account> getAllAccount() {
		return accountService.getAllAccounts();
	}
	
	@GetMapping("/{accountNumber}")
	public Account getAccountByAccountNumber (@PathVariable String accountNumber) {
		return accountService.getAccount(accountNumber);
	}

	@GetMapping("/isNetBankingActivated/{accountNumber}")
	public boolean checkIfNetBankingActivated(@PathVariable String accountNumber)
	{
		return accountService.isNetBankingReg(accountNumber);
	}
		
	
	@PostMapping("/add-account")
	public Message addAccount(@RequestBody Account account) {
		accountService.addAccount(account);
		return new Message("Account added");
	}
	
	@GetMapping("/netbankingenable/{status}/{accountNumber}")
	public Message enableNetBanking(@PathVariable int status, @PathVariable String accountNumber) {
		Message msg=new Message(accountService.upadteAccountNetBankingStatus(1, accountNumber));
		return msg;
	}
	
	@GetMapping("/unregisteredUsers")
	public List<Account> getUnregisteredAccounts() {
		return accountService.getAllUnregisterUser();
	}
	
	@GetMapping("/depositSavings/{balance}/{accountNumber}")
	public Message depositSaving(@PathVariable int balance, @PathVariable String accountNumber) {
		Message msg=new Message(accountService.depositSaving(balance, accountNumber));
		return msg;
	}
	
	@GetMapping("/depositPrimary/{balance}/{accountNumber}")
	public Message depositPrimary(@PathVariable int balance, @PathVariable String accountNumber) {
		Message msg=new Message(accountService.depositPrimary(balance, accountNumber));
		return msg;
	}
	
	@GetMapping("/withdrawSavings/{balance}/{accountNumber}")
	public Message withdrawSaving(@PathVariable int balance, @PathVariable String accountNumber) {
		Message msg=new Message(accountService.withdrawSaving(balance, accountNumber));
		return msg;
	}
	
	@GetMapping("/withdrawPrimary/{balance}/{accountNumber}")
	public Message withdrawPrimary(@PathVariable int balance, @PathVariable String accountNumber) {
		Message msg= new Message(accountService.withdrawPrimary(balance, accountNumber));
		return msg;
	}
	
	@PutMapping("/update/{accountNumber}")
	public Account updateAccountDetails(@RequestBody Account account,@PathVariable String accountNumber)
	{
		return accountService.updateAccountDetails(accountNumber, account);	
	}
	
}
