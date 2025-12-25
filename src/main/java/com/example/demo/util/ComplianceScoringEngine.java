package com.example.demo.util;

import com.example.demo.model.DocumentType;
import com.example.demo.model.VendorDocument;

import java.util.List;

public class ComplianceScoringEngine {

    /**
     * Tests rely on raw List usage due to type inference.
     * DO NOT change method signature.
     */
    @SuppressWarnings("unchecked")
    public double calculateScore(List requiredTypes, List vendorDocuments) {

        List<DocumentType> required = (List<DocumentType>) requiredTypes;
        List<VendorDocument> documents = (List<VendorDocument>) vendorDocuments;

        // Edge case: no required types → full compliance
        if (required == null || required.isEmpty()) {
            return 100.0;
        }

        long fulfilledCount = required.stream()
                .filter(type ->
                        documents.stream().anyMatch(doc ->
                                doc.getDocumentType() != null &&
                                doc.getDocumentType().getId().equals(type.getId()) &&
                                Boolean.TRUE.equals(doc.getIsValid())
                        )
                )
                .count();

        return ((double) fulfilledCount / required.size()) * 100.0;
    }

    public String deriveRating(double score) {
        if (score >= 90.0) return "A";
        if (score >= 75.0) return "B";
        if (score >= 60.0) return "C";
        return "D";
    }
}
