package com.example.demo.service.impl;

import com.example.demo.model.VendorTier;
import com.example.demo.repository.VendorTierRepository;
import com.example.demo.service.VendorTierService;

public class VendorTierServiceImpl implements VendorTierService {

    private final VendorTierRepository repo;

    public VendorTierServiceImpl(VendorTierRepository repo) {
        this.repo = repo;
    }

    public VendorTier createTier(VendorTier t) {
        if (t.getMinScoreThreshold() < 0 || t.getMinScoreThreshold() > 100)
            throw new IllegalArgumentException("Score must be 0–100");
        if (repo.existsByTierName(t.getTierName()))
            throw new IllegalArgumentException("Tier name unique");

        return repo.save(t);
    }

    public void deactivateTier(Long id) {
        VendorTier t = repo.findById(id).orElseThrow();
        t.setActive(false);
        repo.save(t);
    }
}
