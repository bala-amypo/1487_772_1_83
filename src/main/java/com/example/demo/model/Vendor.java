package com.example.demo.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "vendors")
public class Vendor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false, unique = true)
    private String name;
    
    @Column(name = "contact_email")
    private String contactEmail;
    
    @Column(name = "contact_phone")
    private String contactPhone;
    
    @Column(nullable = false)
    private Boolean active = true;
    
    @OneToMany(mappedBy = "vendor", cascade = CascadeType.ALL)
    private List<DeliveryEvaluation> evaluations;
    
    @OneToMany(mappedBy = "vendor", cascade = CascadeType.ALL)
    private List<VendorPerformanceScore> performanceScores;
    
    public Vendor() {}
    
    public Vendor(String name, String contactEmail, String contactPhone) {
        this.name = name;
        this.contactEmail = contactEmail;
        this.contactPhone = contactPhone;
        this.active = true;
    }
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public String getContactEmail() { return contactEmail; }
    public void setContactEmail(String contactEmail) { this.contactEmail = contactEmail; }
    
    public String getContactPhone() { return contactPhone; }
    public void setContactPhone(String contactPhone) { this.contactPhone = contactPhone; }
    
    public Boolean getActive() { return active; }
    public void setActive(Boolean active) { this.active = active; }
    
    public List<DeliveryEvaluation> getEvaluations() { return evaluations; }
    public void setEvaluations(List<DeliveryEvaluation> evaluations) { this.evaluations = evaluations; }
    
    public List<VendorPerformanceScore> getPerformanceScores() { return performanceScores; }
    public void setPerformanceScores(List<VendorPerformanceScore> performanceScores) { 
        this.performanceScores = performanceScores; 
    }
}