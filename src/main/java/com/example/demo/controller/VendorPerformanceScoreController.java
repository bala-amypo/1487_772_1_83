package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.demo.model.VendorPerformanceScore;
import com.example.demo.service.VendorPerformanceScoreService;

@RestController
@RequestMapping("/api/scores")
public class VendorPerformanceScoreController {

@Autowired
private VendorPerformanceScoreService service;

// POST /calculate/{vendorid}
@PostMapping("/calculate/{vendorid}")
public VendorPerformanceScore calculate(@PathVariable Long vendorid) {
return service.calculateScore(vendorid);
}

// GET /latest/{vendorid}
@GetMapping("/latest/{vendorid}")
public VendorPerformanceScore getLatest(@PathVariable Long vendorid) {
return service.getLatestScore(vendorid);
}

// GET /vendor/{vendorid}
@GetMapping("/vendor/{vendorid}")
public List<VendorPerformanceScore> getHistory(@PathVariable Long vendorid) {
return service.getScoresForVendor(vendorid);
}
}
