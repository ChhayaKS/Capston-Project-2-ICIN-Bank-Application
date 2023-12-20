package org.ICIN.Bank.com.repository;

import java.util.List;

import org.ICIN.Bank.com.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import jakarta.transaction.Transactional;

public interface AccountRepository extends JpaRepository<Account, String>{
	
	@Query(value = "SELECT * from account where ACCOUNT_NUMBER like ?1", nativeQuery = true)
	List<Account> getAccountByAccountNumber(String accountNumber);
	
	@Transactional
	@Modifying
	@Query(value = "UPDATE account set NET_BANKING=?1 where ACCOUNT_NUMBER like ?2", nativeQuery = true)
	void updateNetBankingStatus(int status ,String accountNumber);
	
	@Query(value = "SELECT * from account where NET_BANKING like ?1", nativeQuery = true)
	List<Account> getAllUnRegisteredUser(int Status);
	
	@Transactional
	@Modifying
	@Query(value = "UPDATE account set BALANCE_PRIMARY=?1 where ACCOUNT_NUMBER like ?2", nativeQuery = true)
	void updatePrimaryBalance(double balance,String accountNumber);
	
	@Transactional
	@Modifying
	@Query(value = "UPDATE account set BALANCE_SAVINGS=?1 where ACCOUNT_NUMBER like ?2", nativeQuery = true)
	void updateSavingBalance(double balance,String accountNumber);
	
	@Transactional
	@Modifying
	@Query(value = "UPDATE account set BALANCE_PRIMARY=?1 where ACCOUNT_NUMBER like ?2", nativeQuery = true)
	void depositPrimary(int balance,String accountNumber);
	
	@Transactional
	@Modifying
	@Query(value = "UPDATE account set BALANCE_SAVINGS=?1 where ACCOUNT_NUMBER like ?2", nativeQuery = true)
	void depositSaving(int balance,String accountNumber);
	
	@Transactional
	@Modifying
	@Query(value = "UPDATE account set BALANCE_PRIMARY=?1 where ACCOUNT_NUMBER like ?2", nativeQuery = true)
	void withdrawPrimary(int balance, String accountNumber);
	
	@Transactional
	@Modifying
	@Query(value = "UPDATE account set BALANCE_SAVINGS=?1 where ACCOUNT_NUMBER like ?2", nativeQuery = true)
	void withdrawSaving(int balance, String accountNumber);


}
