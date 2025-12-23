package com.example.demo.repository;

import com.example.demo.model.ComplianceScore;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ComplianceScoreRepository
        extends JpaRepository<ComplianceScore, Long> {

    // ✅ REQUIRED BY TESTS
    Optional<ComplianceScore> findByVendor_Id(Long vendorId);

    // ✅ REQUIRED BY SERVICE IMPLEMENTATION
    Optional<ComplianceScore> findByVendorId(Long vendorId);
}
