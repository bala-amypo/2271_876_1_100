package com.example.demo.repository;

import com.example.demo.model.Vendor;
import com.example.demo.model.VendorDocument;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface VendorDocumentRepository extends JpaRepository<VendorDocument, Long> {

    // ✅ REQUIRED by ComplianceScoreServiceImpl
    List<VendorDocument> findByVendor(Vendor vendor);

    // ✅ REQUIRED by VendorDocumentServiceImpl + tests
    List<VendorDocument> findByVendorId(Long vendorId);

    // ✅ REQUIRED by tests
    List<VendorDocument> findExpiredDocuments(LocalDate date);
}
