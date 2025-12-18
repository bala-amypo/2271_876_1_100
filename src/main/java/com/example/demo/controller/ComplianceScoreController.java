package com.example.demo.controller;

import com.example.demo.model.ComplianceScore;
import com.example.demo.service.ComplianceScoreService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/compliance-scores")
public class ComplianceScoreController {

    private final ComplianceScoreService service;

    public ComplianceScoreController(ComplianceScoreService service) {
        this.service = service;
    }

    @PostMapping("/evaluate/{vendorId}")
    public ComplianceScore evaluate(@PathVariable Long vendorId) {
        return service.evaluate(vendorId);
    }

    @GetMapping("/{vendorId}")
    public ComplianceScore get(@PathVariable Long vendorId) {
        return service.get(vendorId);
    }

    @GetMapping
    public List<ComplianceScore> getAll() {
        return service.getAll();
    }
}
