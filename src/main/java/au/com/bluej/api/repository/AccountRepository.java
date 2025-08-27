package au.com.bluej.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.repository.CrudRepository;
//import org.springframework.data.repository.PagingAndSortingRepository;

import au.com.bluej.api.model.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {
	
}
