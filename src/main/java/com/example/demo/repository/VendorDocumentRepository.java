package com.example.demo.repository;

import com.example.demo.model.Vendor;
import com.example.demo.model.VendorDocument;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.util.List;

public interface VendorDocumentRepository extends JpaRepository<VendorDocument, Long> {

    // REQUIRED by VendorDocumentService & tests
    List<VendorDocument> findByVendorId(Long vendorId);

    // REQUIRED by ComplianceScoreServiceImpl
    List<VendorDocument> findByVendor(Vendor vendor);

    // REQUIRED by test cases
    List<VendorDocument> findExpiredDocuments(LocalDate cutoffDate);
}
