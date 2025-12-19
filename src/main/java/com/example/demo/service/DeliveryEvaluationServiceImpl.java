package com.example.demo.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.DeliveryEvaluation;
import com.example.demo.model.SLARequirement;
import com.example.demo.model.Vendor;
import com.example.demo.repository.DeliveryEvaluationRepository;

@Service
public class DeliveryEvaluationServiceImpl implements DeliveryEvaluationService {

@Autowired
private DeliveryEvaluationRepository repo;

@Override
public DeliveryEvaluation createEvaluation(DeliveryEvaluation evaluation) {

Vendor vendor = evaluation.getVendor();
SLARequirement sla = evaluation.getSlaRequirement();

if (!vendor.getActive()) {
throw new RuntimeException("active vendors only");
}

if (evaluation.getActualDeliveryDays() < 0) {
throw new RuntimeException("Actual delivery days must be >= 0");
}

if (evaluation.getQualityScore() < 0 || evaluation.getQualityScore() > 100) {
throw new RuntimeException("Quality score must be between 0 and 100");
}

// Calculate delivery target
boolean deliveryTarget =
evaluation.getActualDeliveryDays() <= sla.getMaxDeliveryDays();

// Calculate quality target
boolean qualityTarget =
evaluation.getQualityScore() >= sla.getMinQualityScore();

evaluation.setMeetsDeliveryTarget(deliveryTarget);
evaluation.setMeetsQualityTarget(qualityTarget);
evaluation.setEvaluationDate(new Date());

return repo.save(evaluation);
}

@Override
public DeliveryEvaluation getEvaluationById(Long id) {
return repo.findById(id)
.orElseThrow(() -> new RuntimeException("Evaluation not found"));
}

@Override
public List<DeliveryEvaluation> getEvaluationsForVendor(Long vendorId) {
return repo.findByVendorId(vendorId);
}

@Override
public List<DeliveryEvaluation> getEvaluationsForRequirement(Long requirementId) {
return repo.findBySlaRequirementId(requirementId);
}
}