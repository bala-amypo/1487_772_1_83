package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.demo.model.Vendor;
import com.example.demo.service.VendorService;

@RestController
@RequestMapping("/vendors")
public class VendorController {

@Autowired
private VendorService vendorService;

// Create Vendor
@PostMapping
public Vendor createVendor(@RequestBody Vendor vendor) {
return vendorService.createVendor(vendor);
}

// Update Vendor
@PutMapping("/{id}")
public Vendor updateVendor(@PathVariable Long id,
@RequestBody Vendor vendor) {
return vendorService.updateVendor(id, vendor);
}

// Get Vendor by ID
@GetMapping("/{id}")
public Vendor getVendorById(@PathVariable Long id) {
return vendorService.getVendorById(id);
}

// Get All Vendors
@GetMapping
public List<Vendor> getAllVendors() {
return vendorService.getAllVendors();
}

// Deactivate Vendor
@DeleteMapping("/{id}")
public String deactivateVendor(@PathVariable Long id) {
vendorService.deactivateVendor(id);
return "Vendor deactivated successfully";
}
}
