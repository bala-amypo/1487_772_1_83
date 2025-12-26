package com.example.demo.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "sla_requirements")
public class SLARequirement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "requirement_name", nullable = false, unique = true)
    private String requirementName;
    
    @Column(name = "description")
    private String description;
    
    @Column(name = "max_delivery_days", nullable = false)
    private Integer maxDeliveryDays;
    
    @Column(name = "quality_target_score", nullable = false)
    private Double qualityTargetScore;
    
    @Column(nullable = false)
    private Boolean active = true;
    
    @OneToMany(mappedBy = "slaRequirement", cascade = CascadeType.ALL)
    private List<DeliveryEvaluation> evaluations;
    
    public SLARequirement() {}
    
    public SLARequirement(String requirementName, String description, 
                         Integer maxDeliveryDays, Double qualityTargetScore) {
        this.requirementName = requirementName;
        this.description = description;
        this.maxDeliveryDays = maxDeliveryDays;
        this.qualityTargetScore = qualityTargetScore;
        this.active = true;
    }
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getRequirementName() { return requirementName; }
    public void setRequirementName(String requirementName) { this.requirementName = requirementName; }
    
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    
    public Integer getMaxDeliveryDays() { return maxDeliveryDays; }
    public void setMaxDeliveryDays(Integer maxDeliveryDays) { this.maxDeliveryDays = maxDeliveryDays; }
    
    public Double getQualityTargetScore() { return qualityTargetScore; }
    public void setQualityTargetScore(Double qualityTargetScore) { 
        this.qualityTargetScore = qualityTargetScore; 
    }
    
    public Boolean getActive() { return active; }
    public void setActive(Boolean active) { this.active = active; }
    
    public List<DeliveryEvaluation> getEvaluations() { return evaluations; }
    public void setEvaluations(List<DeliveryEvaluation> evaluations) { this.evaluations = evaluations; }
}