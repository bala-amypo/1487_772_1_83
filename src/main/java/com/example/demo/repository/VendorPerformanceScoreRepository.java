package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class VendorPerformanceScore {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Vendor vendor;

    private Double onTimePercentage;
    private Double qualityCompliancePercentage;
    private Double overallScore;

    private LocalDateTime calculatedAt = LocalDateTime.now();

    public VendorPerformanceScore() {}

    public VendorPerformanceScore(Vendor v, Double onTime, Double quality, Double overall) {
        this.vendor = v;
        this.onTimePercentage = onTime;
        this.qualityCompliancePercentage = quality;
        this.overallScore = overall;
    }

    // getters & setters
    public Long getId() { return id; }
    public Vendor getVendor() { return vendor; }
    public Double getOnTimePercentage() { return onTimePercentage; }
    public Double getQualityCompliancePercentage() { return qualityCompliancePercentage; }
    public Double getOverallScore() { return overallScore; }
    public LocalDateTime getCalculatedAt() { return calculatedAt; }
}
