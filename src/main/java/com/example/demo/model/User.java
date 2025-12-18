package com.example.demo.entity;
import jakarta.persistence.Entity;
@Entity
public class User{
    @id
    private long id;
    @Column(name="email",)
    private String fullname;
    private String email;
    private LocalDate createdAt;
}