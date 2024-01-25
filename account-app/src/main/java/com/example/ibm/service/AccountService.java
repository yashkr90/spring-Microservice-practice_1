package com.example.ibm.service;

import java.util.List;

import com.example.ibm.exception.AccountNotFoundException;

//import javax.security.auth.login.AccountNotFoundException;

import com.example.ibm.model.Account;

public interface AccountService {

	
	Account createAccount(Account account);
	
	List<Account> getAllAccounts();

	Account getAccountByaccountNumber(String accountNumber)throws AccountNotFoundException;
	
	Account updateAccountByAccountNumber(String accountNumber, Account account)
			throws AccountNotFoundException;
	
	void deleteAccountByAccountNumber(String accountNumber) throws AccountNotFoundException;
}

