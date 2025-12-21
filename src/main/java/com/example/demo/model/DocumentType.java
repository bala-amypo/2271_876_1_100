package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "document_types")
public class DocumentType {

    // id Long (auto-generated, hidden from API)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;

    // typeName String, unique
    @Column(name = "type_name", unique = true, nullable = false)
    private String typeName;

    // description String
    private String description;

    // required Boolean
    private Boolean required;

    // weight Integer, ≥ 0 (validation handled in service)
    @Column(nullable = false)
    private Integer weight;

    // createdAt LocalDateTime (auto-set, hidden from API)
    @Column(name = "created_at", nullable = false, updatable = false)
    @JsonIgnore
    private LocalDateTime createdAt;

    // vendors Set<Vendor> many-to-many inverse side (hidden from API)
    @ManyToMany(mappedBy = "supportedDocumentTypes")
    @JsonIgnore
    private Set<Vendor> vendors = new HashSet<>();

    // no-arg constructor
    public DocumentType() {
    }

    // parameterized constructor
    public DocumentType(String typeName, String description, Boolean required, Integer weight) {
        this.typeName = typeName;
        this.description = description;
        this.required = required;
        this.weight = weight;
    }

    // createdAt set in @PrePersist
    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    // -------- getters & setters --------

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
        this.weight = weight;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public Set<Vendor> getVendors() {
        return vendors;
    }
}
