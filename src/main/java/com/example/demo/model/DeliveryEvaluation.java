package com.example.demo.model;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class DeliveryEvaluation {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;

@ManyToOne
private Vendor vendor;

@ManyToOne
private SLARequirement slaRequirement;

private Integer actualDeliveryDays;
private Double qualityScore;
private Date evaluationDate;
private Boolean meetsDeliveryTarget;
private Boolean meetsQualityTarget;

public DeliveryEvaluation() {
}

public DeliveryEvaluation(Long id, Vendor vendor, SLARequirement slaRequirement,
Integer actualDeliveryDays, Double qualityScore,
Date evaluationDate, Boolean meetsDeliveryTarget,
Boolean meetsQualityTarget) {
this.id = id;
this.vendor = vendor;
this.slaRequirement = slaRequirement;
this.actualDeliveryDays = actualDeliveryDays;
this.qualityScore = qualityScore;
this.evaluationDate = evaluationDate;
this.meetsDeliveryTarget = meetsDeliveryTarget;
this.meetsQualityTarget = meetsQualityTarget;
}

public Long getId() {
return id;
}

public void setId(Long id) {
this.id = id;
}

public Vendor getVendor() {
return vendor;
}

public void setVendor(Vendor vendor) {
this.vendor = vendor;
}

public SLARequirement getSlaRequirement() {
return slaRequirement;
}

public void setSlaRequirement(SLARequirement slaRequirement) {
this.slaRequirement = slaRequirement;
}

public Integer getActualDeliveryDays() {
return actualDeliveryDays;
}

public void setActualDeliveryDays(Integer actualDeliveryDays) {
this.actualDeliveryDays = actualDeliveryDays;
}

public Double getQualityScore() {
return qualityScore;
}

public void setQualityScore(Double qualityScore) {
this.qualityScore = qualityScore;
}

public Date getEvaluationDate() {
return evaluationDate;
}

public void setEvaluationDate(Date evaluationDate) {
this.evaluationDate = evaluationDate;
}

public Boolean getMeetsDeliveryTarget() {
return meetsDeliveryTarget;
}

public void setMeetsDeliveryTarget(Boolean meetsDeliveryTarget) {
this.meetsDeliveryTarget = meetsDeliveryTarget;
}

public Boolean getMeetsQualityTarget() {
return meetsQualityTarget;
}

public void setMeetsQualityTarget(Boolean meetsQualityTarget) {
this.meetsQualityTarget = meetsQualityTarget;
}
}