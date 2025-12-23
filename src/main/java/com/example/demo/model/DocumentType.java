package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "document_types")
public class DocumentType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;

    @NotNull
    @Column(name = "type_name", unique = true, nullable = false)
    private String typeName;

    private String description;

    private Boolean required;

    @NotNull
    @Min(0)
    @Column(nullable = false)
    private Integer weight;

    // createdAt LocalDateTime (auto-set, hidden from API)
    @Column(name = "created_at", nullable = false, updatable = false)
    @JsonIgnore
    private LocalDateTime createdAt;

    @ManyToMany(mappedBy = "supportedDocumentTypes")
    @JsonIgnore
    private Set<Vendor> vendors = new HashSet<>();

    public DocumentType() {
    }

    public DocumentType(String typeName, String description, Boolean required, Integer weight) {
        this.typeName = typeName;
        setDescription(description);
        setRequired(required);
        setWeight(weight); 
    }
    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }


    public Long getId() {
        return id;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getRequired() {
        return required;
    }

    public void setRequired(Boolean required) {
        this.required = required;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        if (weight == null || weight < 0) {
            throw new IllegalArgumentException("Weight must be greater than or equal to 0");
        }
        this.weight = weight;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public Set<Vendor> getVendors() {
        return vendors;
    }
}
