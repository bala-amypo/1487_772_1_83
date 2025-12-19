package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.DeliveryEvaluation;

public interface DeliveryEvaluationRepository
extends JpaRepository<DeliveryEvaluation, Long> {

List<DeliveryEvaluation> findByVendorId(Long vendorId);

List<DeliveryEvaluation> findBySlaRequirementId(Long requirementId);
}