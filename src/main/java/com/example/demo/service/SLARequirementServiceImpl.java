package com.example.demo.service.impl;

import com.example.demo.model.SLARequirement;
import com.example.demo.repository.SLARequirementRepository;
import com.example.demo.service.SLARequirementService;

public class SLARequirementServiceImpl implements SLARequirementService {

    private final SLARequirementRepository repo;

    public SLARequirementServiceImpl(SLARequirementRepository repo) {
        this.repo = repo;
    }

    public SLARequirement createRequirement(SLARequirement r) {
        if (r.getMaxDeliveryDays() <= 0)
            throw new IllegalArgumentException("Max delivery days must be > 0");
        if (r.getMinQualityScore() < 0 || r.getMinQualityScore() > 100)
            throw new IllegalArgumentException("Quality score must be between 0 and 100");
        if (repo.existsByRequirementName(r.getRequirementName()))
            throw new IllegalArgumentException("Requirement name must be unique");

        return repo.save(r);
    }

    public SLARequirement updateRequirement(Long id, SLARequirement r) {
        SLARequirement existing = repo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Not found"));

        if (r.getRequirementName() != null &&
            repo.existsByRequirementName(r.getRequirementName()))
            throw new IllegalArgumentException("Requirement name must be unique");

        return existing;
    }

    public void deactivateRequirement(Long id) {
        SLARequirement r = repo.findById(id).orElseThrow();
        r.setActive(false);
        repo.save(r);
    }
}
