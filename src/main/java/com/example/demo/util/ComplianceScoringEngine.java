package com.example.demo.util;

import com.example.demo.model.DocumentType;
import com.example.demo.model.VendorDocument;

import java.util.List;

public class ComplianceScoringEngine {

    /**
     * IMPORTANT:
     * Tests use generic inference that mixes DocumentType & VendorDocument.
     * So we accept raw List and cast internally.
     * Business logic remains unchanged.
     */
    @SuppressWarnings("unchecked")
    public double calculateScore(List requiredTypes, List vendorDocuments) {

        List<DocumentType> docTypes = (List<DocumentType>) requiredTypes;
        List<VendorDocument> vendorDocs = (List<VendorDocument>) vendorDocuments;

        if (docTypes == null || docTypes.isEmpty()) {
            return 100.0;
        }

        long validCount = vendorDocs.stream()
                .filter(VendorDocument::getIsValid)
                .count();

        return ((double) validCount / docTypes.size()) * 100.0;
    }

    public String deriveRating(double score) {
        if (score >= 90) return "A";
        if (score >= 75) return "B";
        if (score >= 60) return "C";
        return "D";
    }
}
