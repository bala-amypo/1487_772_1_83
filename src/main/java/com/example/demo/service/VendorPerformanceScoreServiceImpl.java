package com.example.demo.service.impl;

import com.example.demo.model.*;
import com.example.demo.repository.*;
import com.example.demo.service.VendorPerformanceScoreService;

import java.util.List;

public class VendorPerformanceScoreServiceImpl implements VendorPerformanceScoreService {

    private final VendorPerformanceScoreRepository repo;
    private final DeliveryEvaluationRepository evalRepo;
    private final VendorRepository vendorRepo;
    private final VendorTierRepository tierRepo;

    public VendorPerformanceScoreServiceImpl(
            VendorPerformanceScoreRepository repo,
            DeliveryEvaluationRepository evalRepo,
            VendorRepository vendorRepo,
            VendorTierRepository tierRepo) {
        this.repo = repo;
        this.evalRepo = evalRepo;
        this.vendorRepo = vendorRepo;
        this.tierRepo = tierRepo;
    }

    public VendorPerformanceScore calculateScore(Long vendorId) {
        Vendor v = vendorRepo.findById(vendorId).orElseThrow();
        List<DeliveryEvaluation> evals = evalRepo.findByVendorId(vendorId);

        long total = evals.size();
        long onTime = evals.stream().filter(DeliveryEvaluation::getMeetsDeliveryTarget).count();
        long quality = evals.stream().filter(DeliveryEvaluation::getMeetsQualityTarget).count();

        double onTimePct = total == 0 ? 0 : (onTime * 100.0 / total);
        double qualityPct = total == 0 ? 0 : (quality * 100.0 / total);
        double overall = (onTimePct + qualityPct) / 2;

        VendorPerformanceScore score =
                new VendorPerformanceScore(v, onTimePct, qualityPct, overall);

        return repo.save(score);
    }

    public VendorPerformanceScore getLatestScore(Long vendorId) {
        return repo.findByVendorOrderByCalculatedAtDesc(vendorId).get(0);
    }

    public List<VendorPerformanceScore> getScoresForVendor(Long vendorId) {
        return repo.findByVendorOrderByCalculatedAtDesc(vendorId);
    }
}
