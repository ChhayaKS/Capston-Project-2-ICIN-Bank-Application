package org.ICIN.Bank.com.repository;

import java.util.List;

import org.ICIN.Bank.com.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import jakarta.transaction.Transactional;

public interface UserRepository extends JpaRepository<User, String> {

	@Query(value = "SELECT * from user where USER_ID like ?1", nativeQuery = true)
	List<User> getUserByUserId(String userId);

	@Query(value = "SELECT * from user where ACCOUNT_IS_BLOCKED like ?1", nativeQuery = true)
	List<User> getAllBlockedUser(int status);

	@Transactional
	@Modifying
	@Query(value = "UPDATE user set PASSWORD=?1 where ACCOUNT_NUMBER like ?2", nativeQuery = true)
	void updateUserPassword(String newPassword, String accountNumber);

	@Transactional
	@Modifying
	@Query(value = "UPDATE user set ACCOUNT_IS_BLOCKED=?1 where USER_ID like ?2", nativeQuery = true)
	void toggleBlockUser(int status, String userId);

}
