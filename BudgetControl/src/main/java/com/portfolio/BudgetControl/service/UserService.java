package com.portfolio.BudgetControl.service;

import com.portfolio.BudgetControl.entity.User;
import com.portfolio.BudgetControl.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;

  public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    return userRepository.findByEmail(username).orElseThrow(
        () -> new UsernameNotFoundException("Usuário não localizado!")
    );
  }

  public User createUser(User user){
    String encondedPassword = passwordEncoder.encode(user.getPassword());

    user.setPassword(encondedPassword);

    return userRepository.save(user);
  }


}
