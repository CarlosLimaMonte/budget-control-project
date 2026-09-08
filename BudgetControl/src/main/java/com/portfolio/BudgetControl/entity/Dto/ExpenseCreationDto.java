package com.portfolio.BudgetControl.entity.Dto;

import com.portfolio.BudgetControl.EnumClass.CategoryExpenses;
import com.portfolio.BudgetControl.entity.Expense;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public record ExpenseCreationDto(
    String name,
    BigDecimal value,
    CategoryExpenses categoryExpenses,
    LocalDate dateTime
) {

  public Expense toEntity(){
    return new Expense(
        null,
        name,
        value,
        categoryExpenses,
        dateTime
    );
  }

}
