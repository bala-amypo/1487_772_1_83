public interface DeliveryEvaluationRepository extends JpaRepository<DeliveryEvaluation, Long> {
    List<DeliveryEvaluation> findByVendorId(Long vendorId);
    List<DeliveryEvaluation> findBySlaRequirementId(Long slaId);

    List<DeliveryEvaluation> findHighQualityDeliveries(Vendor vendor, Double score);
    List<DeliveryEvaluation> findOnTimeDeliveries(SLARequirement sla);
}
