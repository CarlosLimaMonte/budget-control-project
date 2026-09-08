package com.portfolio.BudgetControl.entity;

import com.portfolio.BudgetControl.EnumClass.CategoryIncomes;
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

@Entity
@Table(name = "Incomes")
public class Income {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;

  private BigDecimal value;

  @Enumerated(EnumType.STRING)
  private CategoryIncomes categoryIncomes;

  private LocalDate dateTime;

  @ManyToOne
  @JoinColumn(name = "user_id")
  private User user;

  public Income() {
  }

  public Income(Long id, String name, BigDecimal value, CategoryIncomes categoryIncomes, LocalDate dateTime) {
    this.id = id;
    this.name = name;
    this.value = value;
    this.categoryIncomes = categoryIncomes;
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

  public CategoryIncomes getCategoryIncomes() {
    return categoryIncomes;
  }

  public void setCategoryIncomes(CategoryIncomes categoryIncomes) {
    this.categoryIncomes = categoryIncomes;
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
