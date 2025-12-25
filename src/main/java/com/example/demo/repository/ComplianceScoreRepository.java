package com.example.demo.repository;

import com.example.demo.model.ComplianceScore;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ComplianceScoreRepository extends JpaRepository<ComplianceScore, Long> {

    // Used in service & tests
    Optional<ComplianceScore> findByVendorId(Long vendorId);

    // Explicitly required by test cases
    Optional<ComplianceScore> findByVendor_Id(Long vendorId);
}
