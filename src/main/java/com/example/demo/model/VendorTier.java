package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Column;

@Entity
public class VendorTier {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;

@Column(unique = true)
private String tierName;

private Double minScoreThreshold;

private String description;

private Boolean active = true; // default true

public VendorTier() {
}

public VendorTier(Long id, String tierName, Double minScoreThreshold, String description, Boolean active) {
this.id = id;
this.tierName = tierName;
this.minScoreThreshold = minScoreThreshold;
this.description = description;
this.active = active;
}

public Long getId() {
return id;
}

public void setId(Long id) {
this.id = id;
}

public String getTierName() {
return tierName;
}

public void setTierName(String tierName) {
this.tierName = tierName;
}

public Double getMinScoreThreshold() {
return minScoreThreshold;
}

public void setMinScoreThreshold(Double minScoreThreshold) {
this.minScoreThreshold = minScoreThreshold;
}

public String getDescription() {
return description;
}

public void setDescription(String description) {
this.description = description;
}

public Boolean getActive() {
return active;
}

public void setActive(Boolean active) {
this.active = active;
}
}