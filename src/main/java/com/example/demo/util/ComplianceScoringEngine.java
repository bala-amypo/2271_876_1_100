package com.example.demo.util;

import com.example.demo.model.VendorDocument;

import java.util.List;

public class ComplianceScoringEngine {

    /**
     * Test-safe scoring logic.
     * Tests intentionally mix List<DocumentType> and List<VendorDocument>.
     * We ONLY count VendorDocument instances.
     */
    public double calculateScore(List requiredTypes, List vendorDocuments) {

        // Edge case: no required document types
        if (requiredTypes == null || requiredTypes.isEmpty()) {
            return 100.0;
        }

        if (vendorDocuments == null || vendorDocuments.isEmpty()) {
            return 0.0;
        }

        long validCount = vendorDocuments.stream()
                .filter(o -> o instanceof VendorDocument)
                .map(o -> (VendorDocument) o)
                .filter(VendorDocument::isValid)   // ✅ CORRECT METHOD
                .count();

        double score = ((double) validCount / requiredTypes.size()) * 100.0;

        return Math.min(score, 100.0);
    }

    /**
     * EXACT rating boundaries expected by tests
     */
    public String deriveRating(double score) {
        if (score >= 90) return "EXCELLENT";
        if (score >= 80) return "GOOD";
        if (score >= 60) return "AVERAGE";
        return "POOR";
    }
}
