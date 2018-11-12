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
    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("\n ICCCCCIIIIIIIIIIIIIIIIIIIIIII : "+username+"\n");
        Objects.requireNonNull(username);
        System.out.println("\n ICCCCCIIIIIIIIIIIIIIIIIIIIIII : "+username+"\n");
        User user = userRepository.findByUsername(username);
        return user;
    }
}
