package com.portfolio.BudgetControl.entity.Dto;

import com.portfolio.BudgetControl.EnumClass.CategoryIncomes;
import com.portfolio.BudgetControl.entity.Income;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public record IncomeCreationDto(
    String name,
    BigDecimal value,
    CategoryIncomes categoryIncomes,
    LocalDate dateTime
) {

  public Income toEntity(){
    return new Income(
        null,
        name,
        value,
        categoryIncomes,
        dateTime
    );
  }

}
