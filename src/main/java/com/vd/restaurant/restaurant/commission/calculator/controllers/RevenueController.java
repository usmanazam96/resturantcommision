package com.vd.restaurant.restaurant.commission.calculator.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vd.restaurant.restaurant.commission.calculator.services.RevenueService;

@RestController
@RequestMapping("/revenue")
public class RevenueController {

    @Autowired
    private RevenueService revenueService;

    @GetMapping("/total")
    public ResponseEntity<Double> calculateTotalRevenue() {
        double totalRevenue= revenueService.calculateTotalRevenue();
        return new ResponseEntity<>(totalRevenue, HttpStatus.OK);
    }

    @GetMapping("/commission")
    public  ResponseEntity<Double>  calculateCommissionedRevenue() {
        double commission = revenueService.calculateCommissionedRevenue();
        return new ResponseEntity<>(commission, HttpStatus.OK);
    }
}