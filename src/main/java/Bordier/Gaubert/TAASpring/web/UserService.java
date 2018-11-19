package Bordier.Gaubert.TAASpring.web;

import java.util.Objects;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import Bordier.Gaubert.TAASpring.User;
import Bordier.Gaubert.TAASpring.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@Service

public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private User user ;
    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Objects.requireNonNull(username);
        User user = userRepository.findByUsername(username);
        this.user=user;
        return user;
    }
    public boolean password(String password) {
    	 System.out.println("USER PASSWORD : " + user.getPassword());
         System.out.println("GIVEN PASSWORD : "+ password);
    	return (this.user.getPassword().equals(password));
    }
}
