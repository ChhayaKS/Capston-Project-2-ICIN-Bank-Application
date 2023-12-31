package org.ICIN.Bank.com.repository;

import java.util.List;

import org.ICIN.Bank.com.entity.Cheque;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import jakarta.transaction.Transactional;

public interface ChequeRepository extends JpaRepository<Cheque, String>{

	@Query(value = "SELECT * from cheque where ACCOUNT_NUMBER like ?1 order by CHEQUE_BOOK_ISSUE_DATE desc", nativeQuery = true)
	List<Cheque> getChequeBookIssueByAccountNumber(String accountNumber);
	
	@Query(value = "SELECT * from cheque where CHEQUE_BOOK_STATUS like ?1 order by CHEQUE_BOOK_ISSUE_DATE", nativeQuery = true)
	List<Cheque> getAllPendingChequeBookRequests(int status);
		
	@Transactional
	@Modifying
	@Query(value = "UPDATE cheque set CHEQUE_BOOK_STATUS=?1 where CHEQUE_BOOK_NUMBER like ?2", nativeQuery = true)
	void getUpdatedPendingChequeBookRequests(int i, String chequeBookNumber);

}
