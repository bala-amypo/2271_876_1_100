package com.example.demo.util;

import com.example.demo.model.DocumentType;
import com.example.demo.model.VendorDocument;
import org.springframework.stereotype.Component;
import java.time.LocalDate;
import java.util.List;

@Component
public class ComplianceScoringEngine {
    
    public double calculateScore(List<DocumentType> requiredTypes, List<VendorDocument> vendorDocuments) {
        if (requiredTypes.isEmpty()) {
            return 100.0;
        }
        
        int totalWeight = requiredTypes.stream().mapToInt(DocumentType::getWeight).sum();
        if (totalWeight == 0) {
            return 100.0;
        }
        
        int achievedWeight = 0;
        for (DocumentType requiredType : requiredTypes) {
            boolean hasValidDocument = vendorDocuments.stream()
                .anyMatch(doc -> doc.getDocumentType().getId().equals(requiredType.getId()) 
                    && doc.getIsValid() 
                    && (doc.getExpiryDate() == null || doc.getExpiryDate().isAfter(LocalDate.now())));
            
            if (hasValidDocument) {
                achievedWeight += requiredType.getWeight();
            }
        }
        
        return (double) achievedWeight / totalWeight * 100.0;
    }
    
    public String deriveRating(double score) {
        if (score >= 90) {
            return "EXCELLENT";
        } else if (score >= 70) {
            return "GOOD";
        } else if (score >= 50) {
            return "POOR";
        } else {
            return "NONCOMPLIANT";
        }
    }
}
