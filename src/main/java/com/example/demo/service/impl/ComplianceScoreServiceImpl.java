package com.example.demo.service.impl;

import com.example.demo.model.*;
import com.example.demo.repository.*;
import com.example.demo.service.ComplianceScoreService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComplianceScoreServiceImpl implements ComplianceScoreService {

    private final VendorRepository vendorRepo;
    private final DocumentTypeRepository typeRepo;
    private final VendorDocumentRepository docRepo;
    private final ComplianceScoreRepository scoreRepo;

    public ComplianceScoreServiceImpl(VendorRepository vendorRepo,
                                      DocumentTypeRepository typeRepo,
                                      VendorDocumentRepository docRepo,
                                      ComplianceScoreRepository scoreRepo) {
        this.vendorRepo = vendorRepo;
        this.typeRepo = typeRepo;
        this.docRepo = docRepo;
        this.scoreRepo = scoreRepo;
    }

    @Override
    public ComplianceScore evaluate(Long vendorId) {

        Vendor vendor = vendorRepo.findById(vendorId)
                .orElseThrow(() -> new RuntimeException("Vendor not found"));

        List<DocumentType> allTypes = typeRepo.findAll();
        List<VendorDocument> vendorDocs = docRepo.findByVendor_Id(vendorId);

        int totalWeight = 0;
        int earnedWeight = 0;

        for (DocumentType type : allTypes) {
            if (Boolean.TRUE.equals(type.getRequired())) {
                totalWeight += type.getWeight();

                for (VendorDocument doc : vendorDocs) {
                    if (doc.getDocumentType().getId().equals(type.getId())
                            && Boolean.TRUE.equals(doc.getIsValid())) {
                        earnedWeight += type.getWeight();
                        break;
                    }
                }
            }
        }

        double score = totalWeight == 0 ? 0 : (earnedWeight * 100.0) / totalWeight;

        String rating;
        if (score >= 80) rating = "EXCELLENT";
        else if (score >= 60) rating = "GOOD";
        else if (score >= 40) rating = "POOR";
        else rating = "NON_COMPLIANT";

        ComplianceScore complianceScore = scoreRepo.findByVendor_Id(vendorId);

        if (complianceScore == null) {
            complianceScore = new ComplianceScore();
            complianceScore.setVendor(vendor);
        }

        complianceScore.setScoreValue(score);
        complianceScore.setRating(rating);

        return scoreRepo.save(complianceScore);
    }

    @Override
    public ComplianceScore get(Long vendorId) {
        return scoreRepo.findByVendor_Id(vendorId);
    }

    @Override
    public List<ComplianceScore> getAll() {
        return scoreRepo.findAll();
    }
}
