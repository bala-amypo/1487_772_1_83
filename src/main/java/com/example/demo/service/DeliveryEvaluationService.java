package com.example.demo.service.impl;

import com.example.demo.model.*;
import com.example.demo.repository.*;
import com.example.demo.service.DeliveryEvaluationService;

import java.util.List;

public class DeliveryEvaluationServiceImpl implements DeliveryEvaluationService {

    private final DeliveryEvaluationRepository repo;
    private final VendorRepository vendorRepo;
    private final SLARequirementRepository slaRepo;

    public DeliveryEvaluationServiceImpl(
            DeliveryEvaluationRepository repo,
            VendorRepository vendorRepo,
            SLARequirementRepository slaRepo) {
        this.repo = repo;
        this.vendorRepo = vendorRepo;
        this.slaRepo = slaRepo;
    }

    public DeliveryEvaluation createEvaluation(DeliveryEvaluation e) {
        Vendor v = vendorRepo.findById(e.getVendor().getId()).orElseThrow();
        SLARequirement s = slaRepo.findById(e.getSlaRequirement().getId()).orElseThrow();

        if (!v.getActive())
            throw new IllegalStateException("Only active vendors allowed");
        if (e.getActualDeliveryDays() < 0)
            throw new IllegalArgumentException("Days must be >= 0");
        if (e.getQualityScore() < 0 || e.getQualityScore() > 100)
            throw new IllegalArgumentException("Quality score must be between 0 and 100");

        e.setMeetsDeliveryTarget(e.getActualDeliveryDays() <= s.getMaxDeliveryDays());
        e.setMeetsQualityTarget(e.getQualityScore() >= s.getMinQualityScore());

        return repo.save(e);
    }

    public List<DeliveryEvaluation> getEvaluationsForVendor(Long vendorId) {
        return repo.findByVendorId(vendorId);
    }

    public List<DeliveryEvaluation> getEvaluationsForRequirement(Long slaId) {
        return repo.findBySlaRequirementId(slaId);
    }
}
