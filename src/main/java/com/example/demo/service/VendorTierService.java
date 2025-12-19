package com.example.demo.service;

import java.util.List;
import com.example.demo.model.VendorTier;

public interface VendorTierService {

VendorTier createTier(VendorTier tier);

VendorTier updateTier(Long id, VendorTier tier);

VendorTier getTierById(Long id);

List<VendorTier> getAllTiers();

VendorTier deactivateTier(Long id);
}
