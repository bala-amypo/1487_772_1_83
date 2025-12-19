package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.demo.model.DeliveryEvaluation;
import com.example.demo.service.DeliveryEvaluationService;

@RestController
@RequestMapping("/api/evaluations")
public class DeliveryEvaluationController {

@Autowired
private DeliveryEvaluationService service;

// POST / - Create delivery evaluation
@PostMapping("/")
public DeliveryEvaluation create(@RequestBody DeliveryEvaluation evaluation) {
return service.createEvaluation(evaluation);
}

// GET /{id} - Get evaluation by ID
@GetMapping("/{id}")
public DeliveryEvaluation getById(@PathVariable Long id) {
return service.getEvaluationById(id);
}

// GET /vendor/{vendorid} - List evaluations for vendor
@GetMapping("/vendor/{vendorid}")
public List<DeliveryEvaluation> getByVendor(@PathVariable Long vendorid) {
return service.getEvaluationsForVendor(vendorid);
}

// GET /requirement/{reqid} - List evaluations for SLA requirement
@GetMapping("/requirement/{reqid}")
public List<DeliveryEvaluation> getByRequirement(@PathVariable Long reqid) {
return service.getEvaluationsForRequirement(reqid);
}
}