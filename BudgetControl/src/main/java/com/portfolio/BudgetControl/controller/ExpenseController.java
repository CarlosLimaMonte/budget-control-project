package com.portfolio.BudgetControl.controller;


import com.portfolio.BudgetControl.entity.Dto.ExpenseCreationDto;
import com.portfolio.BudgetControl.entity.Dto.ExpenseDto;
import com.portfolio.BudgetControl.entity.Dto.UserDto;
import com.portfolio.BudgetControl.entity.Expense;
import com.portfolio.BudgetControl.entity.User;
import com.portfolio.BudgetControl.service.ExpenseService;
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
@RequestMapping("/expense")
public class ExpenseController {

  private final ExpenseService expenseService;

  @Autowired
  public ExpenseController(ExpenseService expenseService) {
    this.expenseService = expenseService;
  }

  @GetMapping
  public List<ExpenseDto> getAllExpenses(@AuthenticationPrincipal User user){
    return expenseService.findAllExpenses(user.getId()).stream().map(ExpenseDto::fromEntity).collect(Collectors.toList());
  }

  @GetMapping("/{id}")
  public ExpenseDto getExpenseById(@PathVariable("id") Long expenseId, @AuthenticationPrincipal User user){
    return ExpenseDto.fromEntity(expenseService.findExpenseById(expenseId, user.getId()));
  }

  @PostMapping
  public ExpenseDto createExpense(@RequestBody ExpenseCreationDto expenseDto
      , @AuthenticationPrincipal User user){

    return ExpenseDto.fromEntity(expenseService.createExpense(expenseDto.toEntity(), user));

  }

  @PutMapping("/{id}")
  public ExpenseDto updateExpense(@PathVariable("id") Long expenseId,
      @RequestBody ExpenseCreationDto expenseDto,
      @AuthenticationPrincipal User user){
    return ExpenseDto.fromEntity(expenseService.updateExpense(expenseId, expenseDto.toEntity(), user.getId()));
  }

  @DeleteMapping("/delete/{id}")
  public ExpenseDto deleteExpense(@PathVariable("id") Long expenseId, @AuthenticationPrincipal User user) {
    return ExpenseDto.fromEntity(expenseService.deleteExpense(expenseId, user.getId()));
  }

  @GetMapping("/test")
  public UserDto teste(@AuthenticationPrincipal User user){
    return UserDto.fromEntity(user);
  }

  @PostMapping("/security-test")
  public String securityTest(
      @AuthenticationPrincipal User user
  ) {
    System.out.println("ENTROU NO CONTROLLER");
    System.out.println("USER: " + user.getEmail());

    return "POST autenticado: " + user.getEmail();
  }

}
