package com.example.demo.service;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.DeliveryEvaluation;
import com.example.demo.model.Vendor;
import com.example.demo.model.VendorPerformanceScore;
import com.example.demo.repository.DeliveryEvaluationRepository;
import com.example.demo.repository.VendorPerformanceScoreRepository;

@Service
public class VendorPerformanceScoreServiceImpl
implements VendorPerformanceScoreService {

@Autowired
private VendorPerformanceScoreRepository scoreRepo;

@Autowired
private DeliveryEvaluationRepository evaluationRepo;

@Override
public VendorPerformanceScore calculateScore(Long vendorId) {

List<DeliveryEvaluation> evaluations =
evaluationRepo.findByVendorId(vendorId);

if (evaluations.isEmpty()) {
throw new RuntimeException("No evaluations found for vendor");
}

int total = evaluations.size();
int onTimeCount = 0;
int qualityCount = 0;

Vendor vendor = evaluations.get(0).getVendor();

for (DeliveryEvaluation eval : evaluations) {
if (eval.getMeetsDeliveryTarget()) {
onTimeCount++;
}
if (eval.getMeetsQualityTarget()) {
qualityCount++;
}
}

double onTimePercentage = (onTimeCount * 100.0) / total;
double qualityCompliancePercentage = (qualityCount * 100.0) / total;

// predefined weights
double overallScore =
(onTimePercentage * 0.5) + (qualityCompliancePercentage * 0.5);

VendorPerformanceScore score = new VendorPerformanceScore();
score.setVendor(vendor);
score.setOnTimePercentage(onTimePercentage);
score.setQualityCompliancePercentage(qualityCompliancePercentage);
score.setOverallScore(overallScore);
score.setCalculatedAt(new Timestamp(System.currentTimeMillis()));

return scoreRepo.save(score);
}

@Override
public VendorPerformanceScore getLatestScore(Long vendorId) {
return scoreRepo.findByVendorIdOrderByCalculatedAtDesc(vendorId)
.stream()
.findFirst()
.orElseThrow(() -> new RuntimeException("No score found"));
}

@Override
public List<VendorPerformanceScore> getScoresForVendor(Long vendorId) {
return scoreRepo.findByVendorIdOrderByCalculatedAtDesc(vendorId);
}
}