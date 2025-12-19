package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.demo.model.SLARequirement;
import com.example.demo.service.SLARequirementService;

@RestController
@RequestMapping("/api/sla-requirements")
public class SLARequirementController {

@Autowired
private SLARequirementService service;

// POST / - Create SLA requirement
@PostMapping("/")
public SLARequirement create(@RequestBody SLARequirement req) {
return service.createRequirement(req);
}

// PUT /{id} - Update requirement
@PutMapping("/{id}")
public SLARequirement update(@PathVariable Long id, @RequestBody SLARequirement req) {
return service.updateRequirement(id, req);
}

// GET /{id} - Get requirement
@GetMapping("/{id}")
public SLARequirement getById(@PathVariable Long id) {
return service.getRequirementById(id);
}

// GET / - List all requirements
@GetMapping("/")
public List<SLARequirement> getAll() {
return service.getAllRequirements();
}

// PUT /{id}/deactivate - Deactivate requirement
@PutMapping("/{id}/deactivate")
public String deactivate(@PathVariable Long id) {
service.deactivateRequirement(id);
return "SLA Requirement deactivated successfully";
}
}