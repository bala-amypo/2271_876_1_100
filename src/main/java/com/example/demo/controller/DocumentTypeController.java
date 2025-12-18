package com.example.demo.controller;

import com.example.demo.model.DocumentType;
import com.example.demo.service.DocumentTypeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/document-types")
public class DocumentTypeController {

    private final DocumentTypeService service;

    public DocumentTypeController(DocumentTypeService service) {
        this.service = service;
    }

    @PostMapping
    public DocumentType create(@RequestBody DocumentType type) {
        return service.create(type);
    }

    @GetMapping
    public List<DocumentType> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public DocumentType get(@PathVariable Long id) {
        return service.get(id);
    }
}
