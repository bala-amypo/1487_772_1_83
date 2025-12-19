package com.example.demo.service;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Vendor;
import com.example.demo.repository.VendorRepository;

@Service
public class VendorServiceImpl implements VendorService {

@Autowired
VendorRepository vendorRepository;

@Override
public Vendor createVendor(Vendor vendor) {

if (vendorRepository.findByName(vendor.getName()) != null) {
throw new RuntimeException("Vendor name already exists");
}

vendor.setActive(true);
vendor.setCreatedAt(new Timestamp(System.currentTimeMillis()));
vendor.setUpdatedAt(new Timestamp(System.currentTimeMillis()));

return vendorRepository.save(vendor);
}

@Override
public Vendor updateVendor(Long id, Vendor vendor) {

Vendor existingVendor = vendorRepository.findById(id)
.orElseThrow(() -> new RuntimeException("Vendor not found"));

existingVendor.setName(vendor.getName());
existingVendor.setContactEmail(vendor.getContactEmail());
existingVendor.setContactPhone(vendor.getContactPhone());
existingVendor.setUpdatedAt(new Timestamp(System.currentTimeMillis()));

return vendorRepository.save(existingVendor);
}

@Override
public Vendor getVendorById(Long id) {
return vendorRepository.findById(id)
.orElseThrow(() -> new RuntimeException("Vendor not found"));
}

@Override
public List<Vendor> getAllVendors() {
return vendorRepository.findAll();
}

@Override
public void deactivateVendor(Long id) {

Vendor vendor = vendorRepository.findById(id)
.orElseThrow(() -> new RuntimeException("Vendor not found"));

vendor.setActive(false);
vendor.setUpdatedAt(new Timestamp(System.currentTimeMillis()));

vendorRepository.save(vendor);
}
}