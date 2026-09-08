package com.portfolio.BudgetControl.entity.Dto;

import com.portfolio.BudgetControl.EnumClass.CategoryIncomes;
import com.portfolio.BudgetControl.entity.Income;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public record IncomeDto(
    Long id,
    String name,
    BigDecimal value,
    CategoryIncomes categoryIncomes,
    LocalDate dateTime
) {

  public static IncomeDto fromEntity(Income income){
    return new IncomeDto(
        income.getId(),
        income.getName(),
        income.getValue(),
        income.getCategoryIncomes(),
        income.getDateTime()
    );
  }


}
