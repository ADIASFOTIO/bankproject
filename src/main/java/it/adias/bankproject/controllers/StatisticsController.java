package it.adias.bankproject.controllers;

import it.adias.bankproject.model.services.abstractions.StatisticsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Map;

@RestController
@RequestMapping("/statistics")
@RequiredArgsConstructor
public class StatisticsController {
  private final StatisticsService service;
  @GetMapping("/sum-by-date/{user-id}")
  public ResponseEntity<Map<LocalDate, BigDecimal>> findSumTransactionsByDate(
          @PathVariable("user-id")     Integer userId,
          @RequestParam("start-date") LocalDate startDate,
          @RequestParam("end-date")     LocalDate endDate
  ){
    return ResponseEntity.ok(service.findSumTransactionsByDate(startDate, endDate, userId));
    }
    @GetMapping("/account-balance/{user-id}")
    public ResponseEntity<BigDecimal> getAccountBalance(
            @PathVariable("user-id") Integer userId){
    return ResponseEntity.ok(service.getAccountBalance(userId));
    }
    @GetMapping("/highest-transfer/{user-id}")
    public ResponseEntity<BigDecimal> highestTransfert(
            @PathVariable("user-id") Integer userId){
      return ResponseEntity.ok(service.highestTransfert(userId));
    }
    @GetMapping("/highest-deposit/{user-id}")
    public ResponseEntity<BigDecimal> highestDeposit(
            @PathVariable("user-id") Integer userId){
    return ResponseEntity.ok(service.highestDeposit(userId));
    }
}
