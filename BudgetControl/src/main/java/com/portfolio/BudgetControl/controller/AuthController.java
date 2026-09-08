package com.portfolio.BudgetControl.controller;


import com.portfolio.BudgetControl.entity.Dto.LoginDto;
import com.portfolio.BudgetControl.entity.Dto.LoginResponseDto;
import com.portfolio.BudgetControl.entity.Dto.UserCreationDto;
import com.portfolio.BudgetControl.entity.Dto.UserDto;
import com.portfolio.BudgetControl.entity.User;
import com.portfolio.BudgetControl.service.TokenService;
import com.portfolio.BudgetControl.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

  private final UserService userService;
  private final AuthenticationManager authenticationManager;
  private final TokenService tokenService;

  @Autowired
  public AuthController(UserService userService, AuthenticationManager authenticationManager,
      TokenService tokenService) {
    this.userService = userService;
    this.authenticationManager = authenticationManager;
    this.tokenService = tokenService;
  }

  @PostMapping("/register")
  public UserDto register(@RequestBody UserCreationDto userCreationDto){

    return UserDto.fromEntity(userService.createUser(userCreationDto.toEntity()));

  }

  @PostMapping("/login")
  public LoginResponseDto login(@RequestBody LoginDto loginDto){

    Authentication authentication = authenticationManager.authenticate(
        UsernamePasswordAuthenticationToken.unauthenticated(
            loginDto.email(),
            loginDto.password()
        )
    );

    User user = (User) authentication.getPrincipal();

    String token = tokenService.generateToken(user);

    return new LoginResponseDto(token);


  }

}
