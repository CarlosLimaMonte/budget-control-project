package com.portfolio.BudgetControl.exception;

public class ExpenseNotFoundException extends NotFoundException {

  public ExpenseNotFoundException() {
    super("Gasto não localizado!");
  }
}
