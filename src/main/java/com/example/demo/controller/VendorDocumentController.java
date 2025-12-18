package com.example.demo.controller;

import com.example.demo.model.VendorDocument;
import com.example.demo.service.VendorDocumentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vendor-documents")
public class VendorDocumentController {

    private final VendorDocumentService service;

    public VendorDocumentController(VendorDocumentService service) {
        this.service = service;
    }

    @PostMapping("/{vendorId}/{typeId}")
    public VendorDocument upload(@PathVariable Long vendorId,
                                 @PathVariable Long typeId,
                                 @RequestBody VendorDocument doc) {
        return service.upload(vendorId, typeId, doc);
    }

    @GetMapping("/vendor/{vendorId}")
    public List<VendorDocument> getByVendor(@PathVariable Long vendorId) {
        return service.getByVendor(vendorId);
    }

    @GetMapping("/{id}")
    public VendorDocument get(@PathVariable Long id) {
        return service.get(id);
    }
}
