package com.example.demo.service;

import com.example.demo.model.VendorTier;
import java.util.List;

public interface VendorTierService {
    VendorTier createTier(VendorTier tier);
    List<VendorTier> getAllTiers();
    VendorTier updateTier(Long id, VendorTier tier);
    void deactivateTier(Long id);
}