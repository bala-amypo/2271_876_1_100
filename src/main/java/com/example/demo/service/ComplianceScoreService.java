package com.example.demo.service;

import com.example.demo.model.ComplianceScore;
import java.util.List;

public interface ComplianceScoreService {
    ComplianceScore evaluate(Long vendorId);
    ComplianceScore get(Long vendorId);
    List<ComplianceScore> getAll();
}
