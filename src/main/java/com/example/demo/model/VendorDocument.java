package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "vendor_documents")
public class VendorDocument {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;

    @ManyToOne
    @JoinColumn(name = "vendor_id", nullable = false)
    @JsonIgnore
    private Vendor vendor;

    @ManyToOne
    @JoinColumn(name = "document_type_id", nullable = false)
    @JsonIgnore
    private DocumentType documentType;

    @Column(nullable = false)
    private String fileUrl;

    @Column(nullable = false, updatable = false)
    @JsonIgnore
    private LocalDateTime uploadedAt;

    private LocalDate expiryDate;

    @JsonIgnore
    private Boolean isValid;

    public VendorDocument() {}

    // 🔴 REQUIRED BY TESTS
    public void setId(Long id) {
        this.id = id;
    }

    // 🔴 REQUIRED BY TESTS
    public void setIsValid(Boolean isValid) {
        this.isValid = isValid;
    }

    // 🔴 REQUIRED BY TESTS
    public void prePersist() {
        this.uploadedAt = LocalDateTime.now();
        updateValidityStatus();
    }

    @PrePersist
    protected void onCreate() {
        prePersist();
    }

    private void updateValidityStatus() {
        if (expiryDate == null) {
            this.isValid = true;
        } else {
            this.isValid = expiryDate.isAfter(LocalDate.now());
        }
    }

    // getters & setters
    public Long getId() { return id; }

    public Vendor getVendor() { return vendor; }
    public void setVendor(Vendor vendor) { this.vendor = vendor; }

    public DocumentType getDocumentType() { return documentType; }
    public void setDocumentType(DocumentType documentType) { this.documentType = documentType; }

    public String getFileUrl() { return fileUrl; }
    public void setFileUrl(String fileUrl) { this.fileUrl = fileUrl; }

    public LocalDateTime getUploadedAt() { return uploadedAt; }

    public LocalDate getExpiryDate() { return expiryDate; }
    public void setExpiryDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
        updateValidityStatus();
    }

    public Boolean getIsValid() { return isValid; }
}
