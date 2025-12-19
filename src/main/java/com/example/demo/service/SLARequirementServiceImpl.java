package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.SLARequirement;
import com.example.demo.repository.SLARequirementRepository;

@Service
public class SLARequirementServiceImpl implements SLARequirementService {

@Autowired
private SLARequirementRepository repo;

@Override
public SLARequirement createRequirement(SLARequirement req) {

if (req.getMaxDeliveryDays() <= 0) {
throw new RuntimeException("Max delivery days must be greater than 0");
}

if (req.getMinQualityScore() < 0 || req.getMinQualityScore() > 100) {
throw new RuntimeException("Quality score must be between 0 and 100");
}

if (repo.existsByRequirementName(req.getRequirementName())) {
throw new RuntimeException("Requirement name already exists");
}

req.setActive(true);
return repo.save(req);
}

@Override
public SLARequirement updateRequirement(Long id, SLARequirement req) {

SLARequirement existing = repo.findById(id)
.orElseThrow(() -> new RuntimeException("Requirement not found"));

if (!existing.getRequirementName().equals(req.getRequirementName())
&& repo.existsByRequirementName(req.getRequirementName())) {
throw new RuntimeException("Duplicate requirement name");
}

existing.setRequirementName(req.getRequirementName());
existing.setDescription(req.getDescription());
existing.setMaxDeliveryDays(req.getMaxDeliveryDays());
existing.setMinQualityScore(req.getMinQualityScore());

return repo.save(existing);
}

@Override
public SLARequirement getRequirementById(Long id) {
return repo.findById(id)
.orElseThrow(() -> new RuntimeException("Requirement not found"));
}

@Override
public List<SLARequirement> getAllRequirements() {
return repo.findAll();
}

@Override
public void deactivateRequirement(Long id) {
SLARequirement req = repo.findById(id)
.orElseThrow(() -> new RuntimeException("Requirement not found"));
req.setActive(false);
repo.save(req);
}
}