package au.com.bluej.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import au.com.bluej.api.exception.AccountNotFoundException;
import au.com.bluej.api.model.Account;
import au.com.bluej.api.repository.AccountRepository;
import au.com.bluej.api.service.AccountManagementService;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.validation.Valid;



@RestController
public class AccountController implements AccountManagementService{

	/**
	ServletConfig:
		Scope: Specific to a single servlet. Each servlet instance has its own ServletConfig object.
		Purpose: Provides initialization parameters defined for that particular servlet in the web.xml deployment descriptor. 
				 These parameters are typically used for configuring the individual servlet's behavior or resources.
		Access: Accessed via the getServletConfig() method within a servlet.
		Example: Defining a database connection string or a file path specific to one servlet.
	**/
	@Autowired
	private ServletConfig servletConfig;

	/**
 	ServletContext:
		Scope: Application-wide. There is only one ServletContext object per web application, 
			   shared by all servlets within that application.
		Purpose: Provides application-wide configuration information and allows servlets to communicate with the servlet 
		         container and access shared resources. It can be used to store data accessible by all servlets, 
		         get information about the web application, or dispatch requests to other resources.
		Access: Accessed via the getServletContext() method within a servlet.
		Example: Storing global application settings, shared resources like a database connection pool, or logging application events.
	 */
	@Autowired
	private ServletContext servletContext;
	
	@Autowired
	private AccountRepository accountRepository;
	
	@PostMapping("/accounts")
	ResponseEntity<Account> createAccount(@Valid @RequestBody Account account) { //package-private
		account = accountRepository.save(account);
		return new ResponseEntity<>(account, HttpStatus.CREATED);
	}
	
	@GetMapping("/accounts/{id}")
	ResponseEntity<Account> one(@PathVariable Long id) {
		Account account = accountRepository.findById(id).orElseThrow(() -> new AccountNotFoundException(id));
		return new ResponseEntity<>(account, HttpStatus.OK);
	} 
}
