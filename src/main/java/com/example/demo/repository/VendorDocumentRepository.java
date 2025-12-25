package com.example.demo.repository;

import com.example.demo.model.Vendor;
import com.example.demo.model.VendorDocument;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface VendorDocumentRepository extends JpaRepository<VendorDocument, Long> {

    // REQUIRED BY TESTS
    List<VendorDocument> findByVendor(Vendor vendor);

    // REQUIRED BY TESTS
    List<VendorDocument> findByVendorId(Long vendorId);

    // REQUIRED BY TESTS
    List<VendorDocument> findExpiredDocuments(LocalDate cutoffDate);
}
