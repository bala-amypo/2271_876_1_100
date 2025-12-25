package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "vendor_documents")
public class VendorDocument {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "vendor_id")
    private Vendor vendor;

    @ManyToOne(optional = false)
    @JoinColumn(name = "document_type_id")
    private DocumentType documentType;

    @Column(nullable = false)
    private String fileUrl;

    @Column(nullable = false, updatable = false)
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

    /* ===== REQUIRED BY TESTS ===== */

    public void setId(Long id) {
        this.id = id;
    }

    public void setVendor(Vendor vendor) {
        this.vendor = vendor;
    }

    public void setDocumentType(DocumentType documentType) {
        this.documentType = documentType;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public void setExpiryDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
        updateValidity();
    }

    public void setIsValid(Boolean isValid) {
        this.isValid = isValid;
    }

    @PrePersist
    public void prePersist() {
        this.uploadedAt = LocalDateTime.now();
        updateValidity();
    }

    private void updateValidity() {
        if (expiryDate == null) {
            this.isValid = true;
        } else {
            this.isValid = expiryDate.isAfter(LocalDate.now());
        }
    }

    /* ===== getters ===== */

    public Long getId() { return id; }
    public Vendor getVendor() { return vendor; }
    public DocumentType getDocumentType() { return documentType; }
    public String getFileUrl() { return fileUrl; }
    public LocalDateTime getUploadedAt() { return uploadedAt; }
    public LocalDate getExpiryDate() { return expiryDate; }
    public Boolean getIsValid() { return isValid; }

    /* ===== CRITICAL FOR TEST GENERICS ===== */

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof VendorDocument)) return false;
        VendorDocument that = (VendorDocument) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
