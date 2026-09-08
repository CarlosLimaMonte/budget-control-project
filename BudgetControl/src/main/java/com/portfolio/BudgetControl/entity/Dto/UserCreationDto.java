package com.portfolio.BudgetControl.entity.Dto;

import com.portfolio.BudgetControl.entity.User;

public record UserCreationDto(
    String email,
    String password
) {

  public User toEntity(){
    return new User(
        null,
        email,
        password
    );
  }
}
