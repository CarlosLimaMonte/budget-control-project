package com.portfolio.BudgetControl.entity.Dto;

import java.math.BigDecimal;

public record SummaryDto(
    BigDecimal totalIncome,
    BigDecimal totalExpense,
    BigDecimal balance
){



}
