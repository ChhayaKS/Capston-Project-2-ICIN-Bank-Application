package org.ICIN.Bank.com.repository;

import java.util.List;

import org.ICIN.Bank.com.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import jakarta.transaction.Transactional;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

	@Query(value = "SELECT * from transaction where FROM_ACCOUNT_NUMBER like ?1 OR TO_ACCOUNT_NUMBER like ?1 order by ID desc", nativeQuery = true)
	List<Transaction> getTransactionForAccountNumber(String accountNumber);

	@Query(value = "SELECT * from transaction where TRANSFER_STATUS like ?1 order by TRANSFER_DATE", nativeQuery = true)
	List<Transaction> getPendingTransactions(int status);

	@Query(value = "SELECT * from transaction " + "where (FROM_ACCOUNT_NUMBER like ?1 OR TO_ACCOUNT_NUMBER like ?1) "
			+ "and " + "(TRANSFER_DATE between CAST(?2 as DATE) and CAST(?3 as DATE)) "
			+ "order by TRANSFER_DATE desc", nativeQuery = true)
	List<Transaction> getFilteredTransactions(String accountNumber, String startDate, String endDate);

	@Transactional
	@Modifying
	@Query(value = "UPDATE transaction set TRANSFER_STATUS=?1 where ID like ?2", nativeQuery = true)
	void updateTransaction(int status, Long id);

}
