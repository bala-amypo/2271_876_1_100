package com.example.demo.service;

import com.example.demo.model.ComplianceRule;
import java.util.List;

public interface ComplianceRuleService {
    ComplianceRule create(ComplianceRule rule);
    List<ComplianceRule> getAll();
    ComplianceRule get(Long id);
}
