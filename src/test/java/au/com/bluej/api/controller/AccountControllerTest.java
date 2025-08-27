package au.com.bluej.api.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import com.fasterxml.jackson.databind.ObjectMapper;

import au.com.bluej.api.model.Account;
import au.com.bluej.api.repository.AccountRepository;


@SpringBootTest
@AutoConfigureMockMvc
public class AccountControllerTest {


	//private AccountRepository accountRepository;
	
	@Test 
	void testCreateAccount_ShouldReturnSuccess(@Autowired MockMvc mockMvc, @Autowired ObjectMapper objectMapper) throws Exception {
		Account account = new Account();
		mockMvc.perform(post("/accounts")
                .contentType(MediaType.APPLICATION_JSON) // Or MediaType.APPLICATION_JSON if sending JSON
                .content(objectMapper.writeValueAsString(account)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.accountId").value(1));
	}
	
	@Test
	void testGetAccount_shouldReturnNotFound_whenNotExist(@Autowired MockMvc mockMvc, @Autowired ObjectMapper objectMapper) throws Exception {
		//Account account = new Account();
		//account.setAccountId(1L);
		
		//given(accountRepository.findById(1L)).willReturn(account);	
		mockMvc.perform(get("/accounts/{id}", 1))
	           .andExpect(status().isNotFound());
	                	
	}
}
