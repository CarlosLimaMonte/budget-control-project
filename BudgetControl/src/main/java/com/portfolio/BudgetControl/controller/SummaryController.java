package com.portfolio.BudgetControl.controller;

import com.portfolio.BudgetControl.entity.Dto.SummaryDto;
import com.portfolio.BudgetControl.entity.User;
import com.portfolio.BudgetControl.service.SummaryService;
import java.time.Year;
import java.time.YearMonth;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/summary")
public class SummaryController {

  private final SummaryService summaryService;


  public SummaryController(SummaryService summaryService) {
    this.summaryService = summaryService;
  }

  @GetMapping()
  public SummaryDto monthlySummary(
      @RequestParam Integer year,
      @RequestParam(required = false) Integer month,
      @AuthenticationPrincipal User user
  ){

    if(month == null){
      return summaryService.getAnnualSummary(user.getId(), Year.of(year));
    }

    YearMonth yearMonth = YearMonth.of(year, month);

    return summaryService.getMonthlySummary(user.getId(), yearMonth);

  }

  @GetMapping("/all")
  public SummaryDto allSummary(@AuthenticationPrincipal User user){
    return summaryService.getAllSummary(user.getId());
  }

}
