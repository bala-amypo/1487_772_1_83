package com.example.demo.service.impl;

import com.example.demo.model.VendorTier;
import com.example.demo.repository.VendorTierRepository;
import com.example.demo.service.VendorTierService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class VendorTierServiceImpl implements VendorTierService {
    
    private final VendorTierRepository vendorTierRepository;
    
    public VendorTierServiceImpl(VendorTierRepository vendorTierRepository) {
        this.vendorTierRepository = vendorTierRepository;
    }
    
    @Override
    public VendorTier createTier(VendorTier tier) {
        if (tier.getMinScoreThreshold() < 0 || tier.getMinScoreThreshold() > 100) {
            throw new IllegalArgumentException("Min score threshold must be between 0–100");
        }
        if (vendorTierRepository.existsByTierName(tier.getTierName())) {
            throw new IllegalArgumentException("Tier name must be unique: " + tier.getTierName());
        }
        
        tier.setActive(true);
        return vendorTierRepository.save(tier);
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<VendorTier> getAllTiers() {
        return vendorTierRepository.findAll();
    }
    
    @Override
    @Transactional(readOnly = true)
    public VendorTier getTierById(Long id) {
        Optional<VendorTier> tier = vendorTierRepository.findById(id);
        return tier.orElseThrow(() -> 
            new IllegalArgumentException("Vendor Tier not found with id: " + id));
    }
    
    @Override
    public VendorTier updateTier(Long id, VendorTier tierUpdates) {
        VendorTier tier = getTierById(id);
        
        if (tierUpdates.getTierName() != null && 
            !tierUpdates.getTierName().equals(tier.getTierName())) {
            if (vendorTierRepository.existsByTierName(tierUpdates.getTierName())) {
                throw new IllegalArgumentException("Tier name must be unique: " + tierUpdates.getTierName());
            }
            tier.setTierName(tierUpdates.getTierName());
        }
        
        if (tierUpdates.getDescription() != null) {
            tier.setDescription(tierUpdates.getDescription());
        }
        
        if (tierUpdates.getMinScoreThreshold() != null) {
            if (tierUpdates.getMinScoreThreshold() < 0 || 
                tierUpdates.getMinScoreThreshold() > 100) {
                throw new IllegalArgumentException("Min score threshold must be between 0–100");
            }
            tier.setMinScoreThreshold(tierUpdates.getMinScoreThreshold());
        }
        
        return vendorTierRepository.save(tier);
    }
    
    @Override
    public void deactivateTier(Long id) {
        VendorTier tier = getTierById(id);
        tier.setActive(false);
        vendorTierRepository.save(tier);
    }
}