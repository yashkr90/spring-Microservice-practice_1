package com.example.ibm.repo;

import java.util.List;

//import javax.security.auth.login.AccountNotFoundException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.example.ibm.exception.AccountNotFoundException;
import com.example.ibm.model.Account;

import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
//import jakarta.websocket.Session;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component(value="accountRepository")
@EnableTransactionManagement

public class AccountRepoImpl implements AccountRepo {
	
	private final SessionFactory sessionFactory;

//	public  AccountRepoImpl(SessionFactory sessionFactory) {
//		super();
//		this.sessionFactory = sessionFactory;
//	}
	
	@Transactional
	@Override
	public Account createAccount(Account account) { // TODO
		Session session = sessionFactory.openSession();
		session.getTransaction().begin();
		session.persist(account);
		session.getTransaction().commit();
		return account;
	}

	@Override
	public List<Account> getAllAccounts() { // TODO Auto-generated
		Session session = sessionFactory.openSession();
		TypedQuery<Account> query = session.createQuery("FROM Account A", Account.class);
		return query.getResultList();
	}
	
	@Override
	public Account getAccountByaccountNumber(String accountNumber) throws AccountNotFoundException {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		Account account=session.get(Account.class, accountNumber);
		if(account==null)
		{
			throw new AccountNotFoundException("account with "+accountNumber+" not found");
		}
		//session.close();
		return account;
	}

	@Override
	public Account updateAccountByAccountNumber(String accountNumber, Account account) throws AccountNotFoundException {
		// TODO Auto-generated method stub
		Account tempAccount= getAccountByaccountNumber(accountNumber);
		
		if(tempAccount==null)
		{
			throw new AccountNotFoundException("account with " + accountNumber + " not found");
		}
		
		Session session = sessionFactory.openSession();
		
		tempAccount.setAccountHolderAddress(account.getAccountHolderAddress());
		tempAccount.setAccountHolderName(account.getAccountHolderName());
		tempAccount.setEmail(account.getEmail());
		
		session.beginTransaction();
		session.merge(tempAccount);
		session.getTransaction().commit();
		return tempAccount;
	}
	@Override
	@Transactional
	public void deleteAccountByAccountNumber(String accountNumber)
			throws AccountNotFoundException {
		// TODO Auto-generated method stub

		Session session = sessionFactory.openSession();
		session.getTransaction().begin();
		Account tempAccount = getAccountByaccountNumber(accountNumber);
		if (tempAccount == null) {
			throw new AccountNotFoundException(
					"account with " + accountNumber + " not found");
		}

		session.remove(session.merge(tempAccount));
		session.getTransaction().commit();

	}

	

}
