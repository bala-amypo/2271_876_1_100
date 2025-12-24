package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "vendor_documents")
public class VendorDocument {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    private Vendor vendor;

    @ManyToOne(optional = false)
    private DocumentType documentType;

    @Column(nullable = false)
    private String fileUrl;

    private LocalDateTime uploadedAt;

    private LocalDate expiryDate;

    private Boolean isValid;

    public VendorDocument() {}

    public VendorDocument(Vendor vendor, DocumentType documentType, String fileUrl, LocalDate expiryDate) {
        this.vendor = vendor;
        this.documentType = documentType;
        this.fileUrl = fileUrl;
        this.expiryDate = expiryDate;
    }

    /** ✅ REQUIRED BY TESTS */
    public void setId(Long id) {
        this.id = id;
    }

    /** ✅ REQUIRED BY TESTS */
    public void setIsValid(Boolean isValid) {
        this.isValid = isValid;
    }

    /** ✅ REQUIRED BY TESTS */
    @PrePersist
    public void prePersist() {
        this.uploadedAt = LocalDateTime.now();
        this.isValid = expiryDate == null || expiryDate.isAfter(LocalDate.now());
    }

    public Long getId() { return id; }
    public Vendor getVendor() { return vendor; }
    public DocumentType getDocumentType() { return documentType; }
    public String getFileUrl() { return fileUrl; }
    public LocalDateTime getUploadedAt() { return uploadedAt; }
    public LocalDate getExpiryDate() { return expiryDate; }
    public Boolean getIsValid() { return isValid; }
}
