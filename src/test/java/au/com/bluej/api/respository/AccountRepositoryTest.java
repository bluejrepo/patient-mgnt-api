package au.com.bluej.api.respository;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import au.com.bluej.api.model.Account;
import au.com.bluej.api.repository.AccountRepository;

@SpringBootTest
public class AccountRepositoryTest {
	
//	@Autowired
//	private  AccountRepository accountRepository;
//	
//	@BeforeEach
//	void setUp() {
//		account = new Account();
//		//account.setAccountName("NetSaver");
//		account = accountRepository.saveAndFlush(account);
//	}
//
//	
//	@Test
//	void test_findById_shouldReturnAccount() {
//		//Account persistedAccount = accountRepository.findById(account.getId());
//		//assertEquals(persistedAccount.getAccountName(), account.getAccountName());
//		assertEquals(1,1);
//	}
//	
//	@AfterAll
//	void tearDown() {
//		accountRepository.delete(account);
//	}
//	
	
}
