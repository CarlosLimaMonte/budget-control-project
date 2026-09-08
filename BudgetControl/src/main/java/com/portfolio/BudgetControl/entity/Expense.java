package com.portfolio.BudgetControl.entity;

import com.portfolio.BudgetControl.EnumClass.CategoryExpenses;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "Expenses")
public class Expense {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;

  private BigDecimal value;

  @Enumerated(EnumType.STRING)
  private CategoryExpenses category;

  private LocalDate dateTime;

  @ManyToOne
  @JoinColumn(name = "user_id")
  private User user;

  public Expense() {
  }

  public Expense(Long id, String name, BigDecimal value, CategoryExpenses category, LocalDate dateTime) {
    this.id = id;
    this.name = name;
    this.value = value;
    this.category = category;
    this.dateTime = dateTime;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public BigDecimal getValue() {
    return value;
  }

  public void setValue(BigDecimal value) {
    this.value = value;
  }

  public CategoryExpenses getCategory() {
    return category;
  }

  public void setCategory(CategoryExpenses category) {
    this.category = category;
  }

  public LocalDate getDateTime() {
    return dateTime;
  }

  public void setDateTime(LocalDate dateTime) {
    this.dateTime = dateTime;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }
}
