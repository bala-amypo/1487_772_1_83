package com.example.demo.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.model.VendorTier;
import com.example.demo.repository.VendorTierRepository;

@Service
public class VendorTierServiceImpl implements VendorTierService {

@Autowired
VendorTierRepository vendorTierRepository;

@Override
public VendorTier createTier(VendorTier tier) {
validateThreshold(tier.getMinScoreThreshold());
tier.setActive(true);
return vendorTierRepository.save(tier);
}

@Override
public VendorTier updateTier(Long id, VendorTier tier) {
VendorTier existingTier = vendorTierRepository.findById(id).orElse(null);

if (existingTier != null) {
validateThreshold(tier.getMinScoreThreshold());
existingTier.setTierName(tier.getTierName());
existingTier.setMinScoreThreshold(tier.getMinScoreThreshold());
existingTier.setDescription(tier.getDescription());
return vendorTierRepository.save(existingTier);
}
return null;
}

@Override
public VendorTier getTierById(Long id) {
return vendorTierRepository.findById(id).orElse(null);
}

@Override
public List<VendorTier> getAllTiers() {
return vendorTierRepository.findAll();
}

@Override
public VendorTier deactivateTier(Long id) {
VendorTier tier = vendorTierRepository.findById(id).orElse(null);
if (tier != null) {
tier.setActive(false);
return vendorTierRepository.save(tier);
}
return null;
}

private void validateThreshold(Double threshold) {
if (threshold == null || threshold < 0 || threshold > 100) {
throw new IllegalArgumentException("Min Score Threshold must be between 0 and 100");
}
}
}