package com.example.demo.repository;

import com.example.demo.model.Vendor;
import com.example.demo.model.VendorDocument;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface VendorDocumentRepository extends JpaRepository<VendorDocument, Long> {

    // Used by ComplianceScoreServiceImpl
    List<VendorDocument> findByVendor(Vendor vendor);

    // Used by VendorDocumentServiceImpl
    List<VendorDocument> findByVendorId(Long vendorId);

    // Used by TESTS (underscore version)
    List<VendorDocument> findByVendor_Id(Long vendorId);

    // Used by TESTS
    @Query("SELECT vd FROM VendorDocument vd WHERE vd.expiryDate < :date")
    List<VendorDocument> findExpiredDocuments(LocalDate date);
}
