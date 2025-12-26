package com.example.demo.service.impl;

import com.example.demo.model.Vendor;
import com.example.demo.repository.VendorRepository;
import com.example.demo.service.VendorService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class VendorServiceImpl implements VendorService {
    
    private final VendorRepository vendorRepository;
    
    public VendorServiceImpl(VendorRepository vendorRepository) {
        this.vendorRepository = vendorRepository;
    }
    
    @Override
    public Vendor createVendor(Vendor vendor) {
        if (vendorRepository.existsByName(vendor.getName())) {
            throw new IllegalArgumentException("Vendor name must be unique: " + vendor.getName());
        }
        vendor.setActive(true);
        return vendorRepository.save(vendor);
    }
    
    @Override
    @Transactional(readOnly = true)
    public Vendor getVendorById(Long id) {
        Optional<Vendor> vendor = vendorRepository.findById(id);
        return vendor.orElseThrow(() -> 
            new IllegalArgumentException("Vendor not found with id: " + id));
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<Vendor> getAllVendors() {
        return vendorRepository.findAll();
    }
    
    @Override
    public Vendor updateVendor(Long id, Vendor vendorUpdates) {
        Vendor vendor = getVendorById(id);
        
        if (vendorUpdates.getName() != null && !vendorUpdates.getName().equals(vendor.getName())) {
            if (vendorRepository.existsByName(vendorUpdates.getName())) {
                throw new IllegalArgumentException("Vendor name must be unique: " + vendorUpdates.getName());
            }
            vendor.setName(vendorUpdates.getName());
        }
        
        if (vendorUpdates.getContactEmail() != null) {
            vendor.setContactEmail(vendorUpdates.getContactEmail());
        }
        
        if (vendorUpdates.getContactPhone() != null) {
            vendor.setContactPhone(vendorUpdates.getContactPhone());
        }
        
        return vendorRepository.save(vendor);
    }
    
    @Override
    public void deactivateVendor(Long id) {
        Vendor vendor = getVendorById(id);
        vendor.setActive(false);
        vendorRepository.save(vendor);
    }
}