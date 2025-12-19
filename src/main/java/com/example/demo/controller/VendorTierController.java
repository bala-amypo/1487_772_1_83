package com.example.demo.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.demo.model.VendorTier;
import com.example.demo.service.VendorTierService;

@RestController
@RequestMapping("/api/tiers")
public class VendorTierController {

@Autowired
VendorTierService vendorTierService;

// POST - Create Tier
@PostMapping
public VendorTier createTier(@RequestBody VendorTier tier) {
return vendorTierService.createTier(tier);
}

// PUT - Update Tier
@PutMapping("/{id}")
public VendorTier updateTier(@PathVariable Long id, @RequestBody VendorTier tier) {
return vendorTierService.updateTier(id, tier);
}

// GET - Get Tier by ID
@GetMapping("/{id}")
public VendorTier getTierById(@PathVariable Long id) {
return vendorTierService.getTierById(id);
}

// GET - List all tiers
@GetMapping
public List<VendorTier> getAllTiers() {
return vendorTierService.getAllTiers();
}

// PUT - Deactivate Tier
@PutMapping("/{id}/deactivate")
public VendorTier deactivateTier(@PathVariable Long id) {
return vendorTierService.deactivateTier(id);
}
}