package com.example.demo.service.impl;

import com.example.demo.model.ComplianceRule;
import com.example.demo.repository.ComplianceRuleRepository;
import com.example.demo.service.ComplianceRuleService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComplianceRuleServiceImpl implements ComplianceRuleService {

    private final ComplianceRuleRepository repo;

    public ComplianceRuleServiceImpl(ComplianceRuleRepository repo) {
        this.repo = repo;
    }

    public ComplianceRule create(ComplianceRule rule) {
        return repo.save(rule);
    }

    public List<ComplianceRule> getAll() {
        return repo.findAll();
    }

    public ComplianceRule get(Long id) {
        return repo.findById(id).orElseThrow();
    }
}
