package com.example.demo.util;

import com.example.demo.model.DocumentType;
import com.example.demo.model.VendorDocument;

import java.util.List;

public class ComplianceScoringEngine {

    public double calculateScore(List<?> requiredTypes, List<?> vendorDocuments) {

        if (requiredTypes == null || requiredTypes.isEmpty()) {
            return 100.0;
        }

        long validDocs = vendorDocuments.stream()
                .filter(o -> o instanceof VendorDocument)
                .map(o -> (VendorDocument) o)
                .filter(VendorDocument::getIsValid)
                .count();

        return ((double) validDocs / requiredTypes.size()) * 100.0;
    }

    public String deriveRating(double score) {
        if (score == 100.0) return "EXCELLENT";
        if (score >= 75) return "GOOD";
        if (score >= 50) return "AVERAGE";
        return "POOR";
    }
}
