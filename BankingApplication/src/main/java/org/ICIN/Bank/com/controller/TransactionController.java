package org.ICIN.Bank.com.controller;

import java.util.List;

import org.ICIN.Bank.com.entity.Message;
import org.ICIN.Bank.com.entity.Transaction;
import org.ICIN.Bank.com.repository.FrontendTransaction;
import org.ICIN.Bank.com.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transaction")
@CrossOrigin("http://localhost:4200")
public class TransactionController {
	@Autowired
	private TransactionService transactionService;

	@PostMapping("/addTransactions")
	public Message addTransaction(@RequestBody Transaction transaction) {
		Message msg=new Message(transactionService.addTransaction(transaction));
		return msg;
	}

	@GetMapping("/transactions/{accountNumber}")
	public List<FrontendTransaction> getTransactionsByAccountNumber(@PathVariable String accountNumber) {
		return transactionService.getTransactionsForAccountNumber(accountNumber);
	}

	@GetMapping("/admin/get-all-pending-transactions")
	public List<Transaction> getPendingTransactions() {
		return this.transactionService.getAllPendingTransactions();
	}

	@GetMapping("/admin/allow/transaction/{id}")
	public Message permitTransaction(@PathVariable Long id) {
		Message msg=new Message(transactionService.updateTransaction(id));
		return msg;
	}

	@GetMapping("/transactions/{accountNumber}/{startDate}/{endDate}")
	public List<FrontendTransaction> getFilteredTransactions(@PathVariable String accountNumber,
			@PathVariable String startDate, @PathVariable String endDate) {
		return this.transactionService.getFilteredTransactions(accountNumber, startDate, endDate);
	}

}
