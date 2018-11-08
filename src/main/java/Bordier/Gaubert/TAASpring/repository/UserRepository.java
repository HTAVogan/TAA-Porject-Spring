package Bordier.Gaubert.TAASpring.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import Bordier.Gaubert.TAASpring.User;
@Transactional
@Component
public interface UserRepository extends JpaRepository<User, Long>{
	
	
	//public User findById(Long id);
	
/*	@Modifying
	//@Query("update User u set u.favoriteStyle = set.favoriteStyle & ?1 where u.id = ?2")
	@Query(value="UPDATE User SET favoriteStyle = 1")
	int setFixedFirstnameFor(long id, long lastname);*/
	
	List<User> findByEmail(String email);
}
