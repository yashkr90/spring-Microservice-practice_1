package com.example.ibm.service;

import java.util.List;

//import javax.security.auth.login.AccountNotFoundException;

import org.springframework.stereotype.Component;

import com.example.ibm.exception.AccountNotFoundException;
import com.example.ibm.model.Account;
import com.example.ibm.repo.AccountRepo;

@Component(value = "accountService")
public class AccountServiceImpl implements AccountService{

	
	private AccountRepo accountRepository;

	public AccountServiceImpl(AccountRepo accountRepository) {
		super();
		this.accountRepository = accountRepository;
	}
	@Override
	public Account createAccount(Account account) {
		// TODO Auto-generated method stub
		return accountRepository.createAccount(account);
	}

	@Override
	public List<Account> getAllAccounts() {
		// TODO Auto-generated method stub
		return accountRepository.getAllAccounts();
	}
	@Override
	public Account getAccountByaccountNumber(String accountNumber)throws AccountNotFoundException {
		// TODO Auto-generated method stub
		return accountRepository.getAccountByaccountNumber(accountNumber);
	}
	@Override
	public Account updateAccountByAccountNumber(String accountNumber, Account account) throws AccountNotFoundException {
		// TODO Auto-generated method stub
		return accountRepository.updateAccountByAccountNumber(accountNumber, account);
	}
	@Override
	public void deleteAccountByAccountNumber(String accountNumber) throws AccountNotFoundException {
		// TODO Auto-generated method stub
		accountRepository.deleteAccountByAccountNumber(accountNumber);
		
	}

	
	
	
}
