package com.example.demo.repository;

import com.example.demo.model.ComplianceScore;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComplianceScoreRepository extends JpaRepository<ComplianceScore, Long> {
    ComplianceScore findByVendor_Id(Long vendorId);
}
