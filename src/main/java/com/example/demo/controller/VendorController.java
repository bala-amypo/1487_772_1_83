package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.demo.model.Vendor;
import com.example.demo.service.VendorService;

@RestController
public class VendorController {

@Autowired
VendorService vendorService;

@PostMapping("/vendors")
public Vendor createVendor(@RequestBody Vendor vendor) {
return vendorService.createVendor(vendor);
}

@PutMapping("/vendors/{id}")
public Vendor updateVendor(@PathVariable Long id,
@RequestBody Vendor vendor) {
return vendorService.updateVendor(id, vendor);
}

@GetMapping("/vendors/{id}")
public Vendor getVendorById(@PathVariable Long id) {
return vendorService.getVendorById(id);
}

@GetMapping("/vendors")
public List<Vendor> getAllVendors() {
return vendorService.getAllVendors();
}

@PutMapping("/vendors/{id}/deactivate")
public String deactivateVendor(@PathVariable Long id) {
vendorService.deactivateVendor(id);
return "Vendor deactivated successfully";
}
}