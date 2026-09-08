package com.portfolio.BudgetControl.repository;

import com.portfolio.BudgetControl.entity.Expense;
import com.portfolio.BudgetControl.entity.Income;
import com.portfolio.BudgetControl.entity.User;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IncomeRepository extends JpaRepository<Income, Long> {
  List<Income> findAllByUserId(Long id);
  Optional<Income> findByIdAndUser_id(Long incomeId,
      Long userId);

  List<Income> findAllByUser_IdAndDateTimeGreaterThanEqualAndDateTimeLessThan(Long userId, LocalDate dateTimeIsGreaterThan, LocalDate dateTimeIsLessThan);

}
