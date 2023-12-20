package org.ICIN.Bank.com.service;

import java.util.List;

import org.ICIN.Bank.com.entity.User;
import org.ICIN.Bank.com.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;
	

	public String addUser(User user) {
		userRepository.save(user);
		return "Successfully registered!";
    }

	
	public String checkUserByIdAndPassword(String userId, String loginUserPassword) {
		List<User> list = this.userRepository.findAll();
		for(User user : list) {
			if(user.getUserId().equals(userId)) {
				if(user.getAccountIsBlocked() == 1) {
					return "failure";
				}
				if(user.getPassword().equals(loginUserPassword)) {
					return "success";
				} else {
					return "Password entered is wrong!";
				}
			}
		}
		return "no-user";
	}

	
	public User getUserbyUserId(String UserId) {
		List<User> list = this.userRepository.findAll();
		for(User user : list) {
			if(user.getUserId().equals(UserId)) {
				return user;
			}
		}
		return null;
	}

	
	public String updateLoginPassword(String newPassword, String accountNumber) {
		this.userRepository.updateUserPassword(newPassword, accountNumber);
		return "Successfully changed login password!";
	}

	public String blockUser(String userId) {
	    userRepository.toggleBlockUser(1,userId);
		return "You account has been blocked!\nPlease contact your nearest ICIN bank!";
	}

	public String unblockUser(String userId) {
		userRepository.toggleBlockUser(0, userId);
		return "Account Unblocked";
	}

	
	public List<User> getAllBlockedUser() {
		return userRepository.getAllBlockedUser(1);
	}

	public List<User> getAllUser() {
		return userRepository.findAll();
	}
	
	
}
