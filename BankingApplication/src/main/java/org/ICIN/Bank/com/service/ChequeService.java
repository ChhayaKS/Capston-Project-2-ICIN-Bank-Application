package org.ICIN.Bank.com.service;

import java.util.Date;
import java.util.List;

import org.ICIN.Bank.com.entity.Cheque;
import org.ICIN.Bank.com.repository.ChequeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
public class ChequeService {

	@Autowired
	private ChequeRepository chequeRepository;
@Transactional
	public String generateChequeBook(String accountNumber, String accountType) 
	{
//		if (isSomeChequeBookRequested(accountNumber, accountType).equals("No pending request")) 
//		{
			int count = getCheckBookCount(accountNumber) + 1;
			Cheque cheque = new Cheque();
			cheque.setAccountNumber(accountNumber);
			cheque.setChequeBookNumber(accountType + "" + count);
//			cheque.setChequeBookNumber(chequeBook);
			cheque.setAccountType(accountType);
			cheque.setChequeBookIssueDate(new Date());
			cheque.setChequeBookStatus(0);
			this.chequeRepository.save(cheque);
			return "Requested for a cheque book for account number - " + accountNumber;
//		}
//		return "There is already a pending request for cheque book from " + accountType + " account - " + accountNumber;
	}

	private int getCheckBookCount(String accountNumber) {
		int count = 0;
		List<Cheque> cheques = this.chequeRepository.findAll();
		for (Cheque temp : cheques) {
			if (temp.getAccountNumber().equals(accountNumber)) {
				count++;
			}
		}
		return count;
	}

	private Object isSomeChequeBookRequested(String accountNumber, String accountType) {
		List<Cheque> list = this.chequeRepository.findAll();
		for (Cheque temp : list) {
			if (temp.getAccountNumber().equals(accountNumber) && temp.getAccountType().equals(accountType)) {
				if (temp.getChequeBookStatus() == 0) {
					return "Already Requested!";
				}
			}
		}
		return "No Pending Request";
	}

	public String changeChequeBookStatus(String chequeBookNumber) {
		Cheque cheque = this.chequeRepository.getOne(chequeBookNumber);
		this.chequeRepository.getUpdatedPendingChequeBookRequests(1, chequeBookNumber);
		return "Cheque Book Request Accepted for account number - " + cheque.getAccountNumber();
	}

	public List<Cheque> getAllChequeBookIssued(String accountNumber) {
		return chequeRepository.getChequeBookIssueByAccountNumber(accountNumber);
	}

	public List<Cheque> getAllPendingChequeBookRequests() {
		return chequeRepository.getAllPendingChequeBookRequests(1);
	}
}
