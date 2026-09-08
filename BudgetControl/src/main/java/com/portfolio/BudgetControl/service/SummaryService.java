package com.portfolio.BudgetControl.service;


import com.portfolio.BudgetControl.entity.Dto.SummaryDto;
import com.portfolio.BudgetControl.entity.Expense;
import com.portfolio.BudgetControl.entity.Income;
import com.portfolio.BudgetControl.repository.ExpenseRepository;
import com.portfolio.BudgetControl.repository.IncomeRepository;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Year;
import java.time.YearMonth;
import org.springframework.stereotype.Service;

@Service
public class SummaryService {

  private final ExpenseRepository expenseRepository;
  private final IncomeRepository incomeRepository;


  public SummaryService(ExpenseRepository expenseRepository, IncomeRepository incomeRepository) {
    this.expenseRepository = expenseRepository;
    this.incomeRepository = incomeRepository;
  }

  public SummaryDto getMonthlySummary(
      Long userId,
      YearMonth month
  ){
    LocalDate start = month.atDay(1);
    LocalDate end = month.plusMonths(1).atDay(1);

    BigDecimal totalIncome = incomeRepository
        .findAllByUser_IdAndDateTimeGreaterThanEqualAndDateTimeLessThan(
            userId,
            start,
            end
        ).stream()
        .map(Income::getValue)
        .reduce(BigDecimal.ZERO, BigDecimal::add);

    BigDecimal totalExpense = expenseRepository
        .findAllByUser_IdAndDateTimeGreaterThanEqualAndDateTimeLessThan(
            userId,
            start,
            end
        ).stream()
        .map(Expense::getValue)
        .reduce(BigDecimal.ZERO, BigDecimal::add);

    BigDecimal balance = totalIncome.subtract(totalExpense);

    return new SummaryDto(totalIncome, totalExpense,balance);

  }

  public SummaryDto getAllSummary(Long userId){

    BigDecimal totalExpense = expenseRepository.findAllByUserId(userId)
        .stream().map(Expense::getValue).reduce(BigDecimal.ONE, BigDecimal::add);

    BigDecimal totalIncome = incomeRepository.findAllByUserId(userId)
        .stream().map(Income::getValue).reduce(BigDecimal.ONE, BigDecimal::add);

    BigDecimal balance = totalIncome.subtract(totalExpense);

    return new SummaryDto(totalIncome, totalExpense, balance);
  }

  public SummaryDto getAnnualSummary(Long userId, Year year){
    LocalDate start = year.atMonth(1).atDay(1);
    LocalDate end = year.plusYears(1).atMonth(1).atDay(1);

    BigDecimal totalIncome = incomeRepository
        .findAllByUser_IdAndDateTimeGreaterThanEqualAndDateTimeLessThan(
            userId,
            start,
            end
        ).stream()
        .map(Income::getValue)
        .reduce(BigDecimal.ZERO, BigDecimal::add);

    BigDecimal totalExpense = expenseRepository
        .findAllByUser_IdAndDateTimeGreaterThanEqualAndDateTimeLessThan(
            userId,
            start,
            end
        ).stream()
        .map(Expense::getValue)
        .reduce(BigDecimal.ZERO, BigDecimal::add);

    BigDecimal balance = totalIncome.subtract(totalExpense);

    return new SummaryDto(totalIncome, totalExpense,balance);
  }

}
