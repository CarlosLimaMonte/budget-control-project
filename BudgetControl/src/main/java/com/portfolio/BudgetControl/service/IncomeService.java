package com.portfolio.BudgetControl.service;

import com.portfolio.BudgetControl.entity.Income;
import com.portfolio.BudgetControl.entity.User;
import com.portfolio.BudgetControl.exception.IncomeNotFoundException;
import com.portfolio.BudgetControl.repository.IncomeRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IncomeService {

  private final IncomeRepository incomeRepository;

  @Autowired
  public IncomeService(IncomeRepository incomeRepository) {
    this.incomeRepository = incomeRepository;
  }

  public Income createIncome(Income income, User user){

    income.setUser(user);

    return incomeRepository.save(income);
  }

  public List<Income> findAllIncomes(Long userId){
    return incomeRepository.findAllByUserId(userId);
  }

  public Income findIncomeById(Long id, Long userId){
    return incomeRepository.findByIdAndUser_id(id, userId).orElseThrow(IncomeNotFoundException::new);
  }

  public Income updateIncome(Long id, Income income, Long userId){
    Income incomeFromDb = findIncomeById(id, userId);

    incomeFromDb.setName(income.getName());
    incomeFromDb.setValue(income.getValue());
    incomeFromDb.setCategoryIncomes(income.getCategoryIncomes());
    incomeFromDb.setDateTime(income.getDateTime());

    return incomeRepository.save(incomeFromDb);
  }

  public Income deleteIncome(Long id, Long userId){
    Income incomeFromDb = findIncomeById(id, userId);

    incomeRepository.deleteById(id);

    return incomeFromDb;

  }




}
