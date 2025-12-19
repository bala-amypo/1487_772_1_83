package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class SLARequirement {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;

private String requirementName;
private String description;
private Integer maxDeliveryDays;
private Double minQualityScore;
private Boolean active = true;

public SLARequirement() {
}

public SLARequirement(Long id, String requirementName, String description,
Integer maxDeliveryDays, Double minQualityScore, Boolean active) {
this.id = id;
this.requirementName = requirementName;
this.description = description;
this.maxDeliveryDays = maxDeliveryDays;
this.minQualityScore = minQualityScore;
this.active = active;
}

public Long getId() {
return id;
}

public void setId(Long id) {
this.id = id;
}

public String getRequirementName() {
return requirementName;
}

public void setRequirementName(String requirementName) {
this.requirementName = requirementName;
}

public String getDescription() {
return description;
}

public void setDescription(String description) {
this.description = description;
}

public Integer getMaxDeliveryDays() {
return maxDeliveryDays;
}

public void setMaxDeliveryDays(Integer maxDeliveryDays) {
this.maxDeliveryDays = maxDeliveryDays;
}

public Double getMinQualityScore() {
return minQualityScore;
}

public void setMinQualityScore(Double minQualityScore) {
this.minQualityScore = minQualityScore;
}

public Boolean getActive() {
return active;
}

public void setActive(Boolean active) {
this.active = active;
}
}