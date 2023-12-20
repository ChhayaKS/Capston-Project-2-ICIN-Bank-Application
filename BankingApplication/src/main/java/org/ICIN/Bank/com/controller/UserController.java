package org.ICIN.Bank.com.controller;

import java.util.List;

import org.ICIN.Bank.com.entity.Cheque;
import org.ICIN.Bank.com.entity.Message;
import org.ICIN.Bank.com.entity.User;
import org.ICIN.Bank.com.service.ChequeService;
import org.ICIN.Bank.com.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@CrossOrigin("http://localhost:4200")
public class UserController {
	@Autowired
	private UserService userService;
	@Autowired
	private ChequeService chequeService;

	@GetMapping("/users")
	public List<User> getAllUser() {
		return userService.getAllUser();
	}

	@GetMapping("/{userId}")
	public User getUserByUserId(@PathVariable String userId) {
		return userService.getUserbyUserId(userId);
	}
	
	@GetMapping("/check/{userId}/{password}")
	public Message checkUserByIdAndPassword(@PathVariable String userId, @PathVariable String password) {
		Message msg =new Message (userService.checkUserByIdAndPassword(userId,password));
		return msg;
	}
	
	@PostMapping("/addUser")
	public Message addUser(@RequestBody User user) {
		Message msg=new Message(userService.addUser(user));
		return msg;
	}
	
	@GetMapping("/update/loginPassword/{newPassword}/{accountNumber}")
	public Message updateLoginPassword(@PathVariable String newPassword, @PathVariable String accountNumber) {
		Message msg=new Message(userService.updateLoginPassword(newPassword, accountNumber));
		return msg;
	}
	@GetMapping("/update/transactionPassword/{newPassword}/{accountNumber}")
	public Message updateTransactionPassword(@PathVariable String newPassword, @PathVariable String accountNumber) {
		Message msg=new Message(userService.updateLoginPassword(newPassword, accountNumber));
		return msg;
	}
	
	@GetMapping("/request-cheque-book/{accountNumber}/{accountType}")
	public Message requestChequeBook(@PathVariable String accountNumber, @PathVariable String accountType) {
		Message msg =new Message(chequeService.generateChequeBook(accountNumber, accountType));
		return msg;
	}
	
	@GetMapping("/cheque-books/{accountNumber}")
	public List<Cheque> getChequeBooksIssuedByAccountNumber(@PathVariable String accountNumber) {
		return chequeService.getAllChequeBookIssued(accountNumber);
	}
	
	@GetMapping("/cheque-books-requests")
	public List<Cheque> getAllChequeBookRequests() {
		return chequeService.getAllPendingChequeBookRequests();
	}
	
	@GetMapping("/cheque-books-requests/accept/{chequeBookNumber}")
	public Message acceptChequeBookRequest(@PathVariable String chequeBookNumber) {
		Message msg=new Message(chequeService.changeChequeBookStatus(chequeBookNumber));
		return msg;
	}
	
	@GetMapping("/block/{userId}")
	public Message blockUser(@PathVariable String userId) {
		Message msg=new Message(userService.blockUser(userId));
		return msg;
	}
	
	@GetMapping("/unblock/{userId}")
	public Message unblockUser(@PathVariable String userId) {
		Message msg=new Message(userService.unblockUser(userId));
		return msg;
	}
	
	@GetMapping("/blocked-users")
	public List<User> getAllblockUser() {
		return userService.getAllBlockedUser();
	}


}