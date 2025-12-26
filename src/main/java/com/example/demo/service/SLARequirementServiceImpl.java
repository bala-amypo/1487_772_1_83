package com.example.demo.service.impl;

import com.example.demo.model.SLARequirement;
import com.example.demo.repository.SLARequirementRepository;
import com.example.demo.service.SLARequirementService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class SLARequirementServiceImpl implements SLARequirementService {
    
    private final SLARequirementRepository slaRequirementRepository;
    
    public SLARequirementServiceImpl(SLARequirementRepository slaRequirementRepository) {
        this.slaRequirementRepository = slaRequirementRepository;
    }
    
    @Override
    public SLARequirement createRequirement(SLARequirement requirement) {
        if (requirement.getMaxDeliveryDays() <= 0) {
            throw new IllegalArgumentException("Max delivery days must be positive");
        }
        if (requirement.getQualityTargetScore() < 0 || requirement.getQualityTargetScore() > 100) {
            throw new IllegalArgumentException("Quality target score must be between 0 and 100");
        }
        if (slaRequirementRepository.existsByRequirementName(requirement.getRequirementName())) {
            throw new IllegalArgumentException("Requirement name must be unique: " + requirement.getRequirementName());
        }
        
        requirement.setActive(true);
        return slaRequirementRepository.save(requirement);
    }
    
    @Override
    @Transactional(readOnly = true)
    public SLARequirement getRequirementById(Long id) {
        Optional<SLARequirement> requirement = slaRequirementRepository.findById(id);
        return requirement.orElseThrow(() -> 
            new IllegalArgumentException("SLA Requirement not found with id: " + id));
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<SLARequirement> getAllRequirements() {
        return slaRequirementRepository.findAll();
    }
    
    @Override
    public SLARequirement updateRequirement(Long id, SLARequirement requirementUpdates) {
        SLARequirement requirement = getRequirementById(id);
        
        if (requirementUpdates.getRequirementName() != null && 
            !requirementUpdates.getRequirementName().equals(requirement.getRequirementName())) {
            if (slaRequirementRepository.existsByRequirementName(requirementUpdates.getRequirementName())) {
                throw new IllegalArgumentException("Requirement name must be unique: " + requirementUpdates.getRequirementName());
            }
            requirement.setRequirementName(requirementUpdates.getRequirementName());
        }
        
        if (requirementUpdates.getDescription() != null) {
            requirement.setDescription(requirementUpdates.getDescription());
        }
        
        if (requirementUpdates.getMaxDeliveryDays() != null) {
            if (requirementUpdates.getMaxDeliveryDays() <= 0) {
                throw new IllegalArgumentException("Max delivery days must be positive");
            }
            requirement.setMaxDeliveryDays(requirementUpdates.getMaxDeliveryDays());
        }
        
        if (requirementUpdates.getQualityTargetScore() != null) {
            if (requirementUpdates.getQualityTargetScore() < 0 || 
                requirementUpdates.getQualityTargetScore() > 100) {
                throw new IllegalArgumentException("Quality target score must be between 0 and 100");
            }
            requirement.setQualityTargetScore(requirementUpdates.getQualityTargetScore());
        }
        
        return slaRequirementRepository.save(requirement);
    }
    
    @Override
    public void deactivateRequirement(Long id) {
        SLARequirement requirement = getRequirementById(id);
        requirement.setActive(false);
        slaRequirementRepository.save(requirement);
    }
}