package com.example.demo.service;

import java.util.List;
import com.example.demo.model.Vendor;

public interface VendorService {

Vendor createVendor(Vendor vendor);

Vendor updateVendor(Long id, Vendor vendor);

Vendor getVendorById(Long id);

List<Vendor> getAllVendors();

void deactivateVendor(Long id);
}