package com.portfolio.BudgetControl.entity.Dto;

import com.portfolio.BudgetControl.entity.User;

public record UserDto(
    Long id,
    String email
) {

  public static UserDto fromEntity(User user){
    return new UserDto(
        user.getId(),
        user.getEmail()
    );
  }

}
