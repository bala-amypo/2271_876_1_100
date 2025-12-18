package com.example.demo.entity;
import jakarta.persistence.Entity;
@Entity
public class ComplianceRule{
    @id
    private long id;
    private String fullname;
    private String email;
    private LocalDate createdAt;
}