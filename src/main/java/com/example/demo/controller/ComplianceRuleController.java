package com.example.demo.controller;

import com.example.demo.model.ComplianceRule;
import com.example.demo.service.ComplianceRuleService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/compliance-rules")
public class ComplianceRuleController {

    private final ComplianceRuleService service;

    public ComplianceRuleController(ComplianceRuleService service) {
        this.service = service;
    }

    @PostMapping
    public ComplianceRule create(@RequestBody ComplianceRule rule) {
        return service.create(rule);
    }

    @GetMapping
    public List<ComplianceRule> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public ComplianceRule get(@PathVariable Long id) {
        return service.get(id);
    }
}
