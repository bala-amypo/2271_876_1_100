package com.example.demo.controller;

import com.example.demo.model.VendorDocument;
import com.example.demo.service.VendorDocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/vendor-documents")
public class VendorDocumentController {
    
    @Autowired
    private VendorDocumentService vendorDocumentService;
    
    @PreAuthorize("isAuthenticated()")
    @PostMapping
    public ResponseEntity<VendorDocument> uploadDocument(
            @RequestParam Long vendorId,
            @RequestParam Long typeId,
            @RequestBody VendorDocument document) {
        VendorDocument uploaded = vendorDocumentService.uploadDocument(vendorId, typeId, document);
        return ResponseEntity.ok(uploaded);
    }
    
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/vendor/{vendorId}")
    public ResponseEntity<List<VendorDocument>> getVendorDocuments(@PathVariable Long vendorId) {
        List<VendorDocument> documents = vendorDocumentService.getDocumentsForVendor(vendorId);
        return ResponseEntity.ok(documents);
    }
    
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/{id}")
    public ResponseEntity<VendorDocument> getDocument(@PathVariable Long id) {
        VendorDocument document = vendorDocumentService.getDocument(id);
        return ResponseEntity.ok(document);
    }
}
