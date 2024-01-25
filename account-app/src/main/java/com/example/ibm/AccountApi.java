package com.example.ibm;

import java.util.List;

//import javax.security.auth.login.AccountNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.ibm.exception.AccountNotFoundException;
import com.example.ibm.exception.ui.ErrorResponse;
import com.example.ibm.model.Account;
import com.example.ibm.service.AccountService;

@RestController
public class AccountApi {

	
	
	private final AccountService accountService;

	@ExceptionHandler
	public ErrorResponse handleException(AccountNotFoundException e) {

		ErrorResponse response = new ErrorResponse();
		response.setMessage(e.getMessage());
		response.setStatusCode(HttpStatus.BAD_REQUEST.value());
		response.setToOfError(System.currentTimeMillis());
		return response;
	}
	
	// or you can use @AllArgsCOnstrucotr
	public AccountApi(AccountService accountService) {
		super();
		this.accountService = accountService;
	}
	
	
	///------------or above class name write @RequestMapping("/accounts")-----
	@PostMapping("/accounts")
	public Account createAccount(@RequestBody Account account)
	{
		return accountService.createAccount(account);
	}
	
	
	@GetMapping("/accounts")
	public List<Account> listAccounts()
	{
		return accountService.getAllAccounts();
	}
	
	
	@GetMapping("/accounts/{accountNumber}")
	public Account getAccountByNumber(@PathVariable("accountNumber") String accountNumber)
			throws AccountNotFoundException {
		return accountService.getAccountByaccountNumber(accountNumber);
	}
	
	@PutMapping("/accounts/{accountNumber}")
	public Account updateAccount(@PathVariable("accountNumber") String accountNumber, @RequestBody Account account) throws AccountNotFoundException 
	{
		return accountService.updateAccountByAccountNumber(accountNumber, account);
	}
	
	@DeleteMapping("/accounts/{accountNumber}")
	public void deleteAccount(@PathVariable("accountNumber") String accountNumber) throws AccountNotFoundException
	{
		accountService.deleteAccountByAccountNumber(accountNumber);
	}
}
