package com.example.ibm.repo;

import com.example.ibm.exception.AccountNotFoundException;
import com.example.ibm.model.Account;

import java.util.*;

//import javax.security.auth.login.AccountNotFoundException;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@Component(value = "accountRepository")
public interface AccountRepo {


	Account createAccount(Account account);
	
	List<Account> getAllAccounts();

	Account getAccountByaccountNumber(String accountNumber) throws AccountNotFoundException;
	
	Account updateAccountByAccountNumber(String accountNumber, Account account)
			throws AccountNotFoundException;

	void deleteAccountByAccountNumber(String accountNumber) throws AccountNotFoundException;
}
