package com.portfolio.BudgetControl.entity.Dto;

import com.portfolio.BudgetControl.EnumClass.CategoryExpenses;
import com.portfolio.BudgetControl.entity.Expense;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public record ExpenseDto(
    Long id,
    String name,
    BigDecimal value,
    CategoryExpenses categoryExpenses,
    LocalDate dateTime
    ) {

  public static ExpenseDto fromEntity(Expense expense){
    return new ExpenseDto(
        expense.getId(),
        expense.getName(),
        expense.getValue(),
        expense.getCategory(),
        expense.getDateTime()
    );
  }

}
