package com.example.demo.service.impl;

import com.example.demo.model.DocumentType;
import com.example.demo.repository.DocumentTypeRepository;
import com.example.demo.service.DocumentTypeService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DocumentTypeServiceImpl implements DocumentTypeService {

    private final DocumentTypeRepository repo;

    public DocumentTypeServiceImpl(DocumentTypeRepository repo) {
        this.repo = repo;
    }

    public DocumentType create(DocumentType type) {
        if (repo.existsByTypeName(type.getTypeName())) {
            throw new RuntimeException("Document Type already exists");
        }
        return repo.save(type);
    }

    public List<DocumentType> getAll() {
        return repo.findAll();
    }

    public DocumentType get(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("DocumentType not found"));
    }
}
