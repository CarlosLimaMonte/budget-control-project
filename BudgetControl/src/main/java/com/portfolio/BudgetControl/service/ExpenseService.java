package com.portfolio.BudgetControl.service;


import com.portfolio.BudgetControl.entity.Expense;
import com.portfolio.BudgetControl.entity.User;
import com.portfolio.BudgetControl.exception.ExpenseNotFoundException;
import com.portfolio.BudgetControl.repository.ExpenseRepository;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExpenseService {

  private final ExpenseRepository expenseRepository;

  @Autowired
  public ExpenseService(ExpenseRepository expenseRepository) {
    this.expenseRepository = expenseRepository;
  }

  public Expense createExpense(Expense expense, User user) {
    expense.setUser(user);
    return expenseRepository.save(expense);
  }

  public List<Expense> findAllExpenses(Long userId){
    return expenseRepository.findAllByUserId(userId);
  }

  public Expense findExpenseById(Long expenseId, Long userId){
    return expenseRepository.findByIdAndUser_id(expenseId, userId).orElseThrow(ExpenseNotFoundException::new);
  }

  public Expense updateExpense(Long id, Expense expense, Long userId){
    Expense expenseFromDb = findExpenseById(id, userId);

    expenseFromDb.setName(expense.getName());
    expenseFromDb.setValue(expense.getValue());
    expenseFromDb.setCategory(expense.getCategory());
    expenseFromDb.setDateTime(expense.getDateTime());

    return expenseRepository.save(expenseFromDb);
  }

  public Expense deleteExpense(Long expenseId, Long userId) {
    Expense expenseFromDb = findExpenseById(expenseId, userId);

    expenseRepository.deleteById(expenseId);

    return expenseFromDb;
  }

  public List<Expense> findExpensivesByPeriod(Long userId, YearMonth month){

    LocalDate start = month.atDay(1);
    LocalDate end = month.plusMonths(1).atDay(1);

    return expenseRepository.findAllByUser_IdAndDateTimeGreaterThanEqualAndDateTimeLessThan(
        userId,
        start,
        end
    );

  }




}
