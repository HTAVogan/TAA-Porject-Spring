package jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import jpa.Entites.User;

public interface UserRepository extends JpaRepository<User, Long>{
	User findOne(Long id);
	
	@Modifying
	@Query("update User u set u.favoriteStyle = set.favoriteStyle & ?1 where u.id = ?2")
	int setFixedFirstnameFor(long id, long lastname);
	
	User findByEmail(String email);
}
