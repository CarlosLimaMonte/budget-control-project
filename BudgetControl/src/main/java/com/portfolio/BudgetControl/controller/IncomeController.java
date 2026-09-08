package com.portfolio.BudgetControl.controller;


import com.portfolio.BudgetControl.entity.Dto.IncomeCreationDto;
import com.portfolio.BudgetControl.entity.Dto.IncomeDto;
import com.portfolio.BudgetControl.entity.Income;
import com.portfolio.BudgetControl.entity.User;
import com.portfolio.BudgetControl.service.IncomeService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/income")
public class IncomeController {

  private final IncomeService incomeService;

  @Autowired
  public IncomeController(IncomeService incomeService) {
    this.incomeService = incomeService;
  }

  @GetMapping
  public List<IncomeDto> getAllIncomes(@AuthenticationPrincipal User user){
    return incomeService.findAllIncomes(user.getId()).stream().map(IncomeDto::fromEntity).collect(Collectors.toList());
  }

  @GetMapping("/{id}")
  public IncomeDto getIncomeById(@PathVariable Long id, @AuthenticationPrincipal User user){
    return IncomeDto.fromEntity(incomeService.findIncomeById(id, user.getId()));
  }

  @PostMapping
  public IncomeDto createIncome(@RequestBody IncomeCreationDto incomeDto, @AuthenticationPrincipal User user){
    return IncomeDto.fromEntity(incomeService.createIncome(incomeDto.toEntity(), user));
  }

  @PutMapping("/{id}")
  public IncomeDto updateIncome (@PathVariable Long id, @RequestBody IncomeCreationDto incomeDto, @AuthenticationPrincipal User user){
    return IncomeDto.fromEntity(incomeService.updateIncome(id, incomeDto.toEntity(), user.getId()));
  }

  @DeleteMapping("/delete/{id}")
  public IncomeDto deleteIncome(@PathVariable Long id, @AuthenticationPrincipal User user){
    return IncomeDto.fromEntity(incomeService.deleteIncome(id, user.getId()));

  }




}
