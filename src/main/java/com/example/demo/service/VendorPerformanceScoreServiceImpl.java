package com.example.demo.service.impl;

import com.example.demo.model.*;
import com.example.demo.repository.*;
import com.example.demo.service.VendorPerformanceScoreService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class VendorPerformanceScoreServiceImpl implements VendorPerformanceScoreService {
    
    private final VendorPerformanceScoreRepository vendorPerformanceScoreRepository;
    private final DeliveryEvaluationRepository deliveryEvaluationRepository;
    private final VendorRepository vendorRepository;
    private final VendorTierRepository vendorTierRepository;
    
    public VendorPerformanceScoreServiceImpl(
            VendorPerformanceScoreRepository vendorPerformanceScoreRepository,
            DeliveryEvaluationRepository deliveryEvaluationRepository,
            VendorRepository vendorRepository,
            VendorTierRepository vendorTierRepository) {
        this.vendorPerformanceScoreRepository = vendorPerformanceScoreRepository;
        this.deliveryEvaluationRepository = deliveryEvaluationRepository;
        this.vendorRepository = vendorRepository;
        this.vendorTierRepository = vendorTierRepository;
    }
    
    @Override
    public VendorPerformanceScore calculateScore(Long vendorId) {
        Vendor vendor = vendorRepository.findById(vendorId)
            .orElseThrow(() -> new IllegalArgumentException("Vendor not found"));
        
        List<DeliveryEvaluation> evaluations = deliveryEvaluationRepository.findByVendorId(vendorId);
        
        if (evaluations.isEmpty()) {
            throw new IllegalStateException("No evaluations found for vendor");
        }
        
        long onTimeCount = evaluations.stream()
            .filter(eval -> Boolean.TRUE.equals(eval.getMeetsDeliveryTarget()))
            .count();
        
        long qualityCount = evaluations.stream()
            .filter(eval -> Boolean.TRUE.equals(eval.getMeetsQualityTarget()))
            .count();
        
        double onTimePercentage = (double) onTimeCount / evaluations.size() * 100;
        double qualityCompliancePercentage = (double) qualityCount / evaluations.size() * 100;
        double overallScore = (onTimePercentage + qualityCompliancePercentage) / 2;
        
        VendorPerformanceScore score = new VendorPerformanceScore(
            vendor, onTimePercentage, qualityCompliancePercentage, overallScore);
        
        return vendorPerformanceScoreRepository.save(score);
    }
    
    @Override
    @Transactional(readOnly = true)
    public VendorPerformanceScore getLatestScore(Long vendorId) {
        List<VendorPerformanceScore> scores = vendorPerformanceScoreRepository
            .findByVendorOrderByCalculatedAtDesc(vendorId);
        
        if (scores.isEmpty()) {
            throw new IllegalArgumentException("No scores found for vendor");
        }
        
        return scores.get(0);
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<VendorPerformanceScore> getScoresForVendor(Long vendorId) {
        return vendorPerformanceScoreRepository.findByVendorOrderByCalculatedAtDesc(vendorId);
    }
}