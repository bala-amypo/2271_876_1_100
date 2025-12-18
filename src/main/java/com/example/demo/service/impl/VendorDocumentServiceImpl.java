package com.example.demo.service.impl;

import com.example.demo.model.VendorDocument;
import com.example.demo.repository.*;
import com.example.demo.service.VendorDocumentService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VendorDocumentServiceImpl implements VendorDocumentService {

    private final VendorRepository vendorRepo;
    private final DocumentTypeRepository typeRepo;
    private final VendorDocumentRepository docRepo;

    public VendorDocumentServiceImpl(VendorRepository v,
                                     DocumentTypeRepository t,
                                     VendorDocumentRepository d) {
        vendorRepo = v;
        typeRepo = t;
        docRepo = d;
    }

    public VendorDocument upload(Long vendorId, Long typeId, VendorDocument doc) {
        doc.setVendor(vendorRepo.findById(vendorId).orElseThrow());
        doc.setDocumentType(typeRepo.findById(typeId).orElseThrow());
        return docRepo.save(doc);
    }

    public List<VendorDocument> getByVendor(Long vendorId) {
        return docRepo.findByVendor_Id(vendorId);
    }

    public VendorDocument get(Long id) {
        return docRepo.findById(id).orElseThrow();
    }
}
