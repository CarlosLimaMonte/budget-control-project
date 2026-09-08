package com.portfolio.BudgetControl.repository;

import com.portfolio.BudgetControl.entity.Expense;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.cglib.core.Local;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {

  List<Expense> findAllByUserId(Long id);
  Optional<Expense> findByIdAndUser_id(Long expenseId,
      Long userId);

  List<Expense> findAllByUser_IdAndDateTimeGreaterThanEqualAndDateTimeLessThan(Long userId, LocalDate dateTimeIsGreaterThan, LocalDate dateTimeIsLessThan);

}
