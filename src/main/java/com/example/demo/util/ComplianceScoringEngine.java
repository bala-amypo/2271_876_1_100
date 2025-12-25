package com.example.demo.util;

import com.example.demo.model.DocumentType;
import com.example.demo.model.VendorDocument;

import java.util.List;

public class ComplianceScoringEngine {

    // ===== ORIGINAL LOGIC (UNCHANGED) =====
    public double calculateScore(
            List<DocumentType> requiredTypes,
            List<VendorDocument> vendorDocuments
    ) {
        if (requiredTypes == null || requiredTypes.isEmpty()) {
            return 100.0;
        }

        long validCount = vendorDocuments.stream()
                .filter(VendorDocument::getIsValid)
                .count();

        return ((double) validCount / requiredTypes.size()) * 100.0;
    }

    // ===== TEST-COMPATIBILITY OVERLOAD =====
    // DO NOT REMOVE — REQUIRED FOR GENERIC INFERENCE
    @SuppressWarnings("unchecked")
    public double calculateScore(List<?> requiredTypes, List<?> vendorDocuments) {
        return calculateScore(
                (List<DocumentType>) requiredTypes,
                (List<VendorDocument>) vendorDocuments
        );
    }

    public String deriveRating(double score) {
        if (score >= 90) return "A";
        if (score >= 75) return "B";
        if (score >= 60) return "C";
        return "D";
    }
}
