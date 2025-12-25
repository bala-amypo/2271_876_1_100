package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "compliance_scores")
public class ComplianceScore {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "vendor_id", nullable = false, unique = true)
    private Vendor vendor;

    @Column(nullable = false)
    private Double score;

    @Column(nullable = false)
    private String rating;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    public ComplianceScore() {}

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        if (this.score == null) {
            this.score = 0.0;
        }
        if (this.rating == null) {
            this.rating = "POOR";
        }
    }

    // ===== Getters & Setters =====

    public Long getId() {
        return id;
    }

    public Vendor getVendor() {
        return vendor;
    }

    public void setVendor(Vendor vendor) {
        this.vendor = vendor;
    }

    public Double getScore() {
        return score;
    }

    // ✅ REQUIRED BY TESTS
    public Double getScoreValue() {
        return this.score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
