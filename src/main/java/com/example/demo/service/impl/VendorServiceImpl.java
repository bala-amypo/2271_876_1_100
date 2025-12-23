package com.example.demo.service.impl;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.exception.ValidationException;
import com.example.demo.model.Vendor;
import com.example.demo.repository.VendorRepository;
import com.example.demo.service.VendorService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VendorServiceImpl implements VendorService {

    private final VendorRepository vendorRepository;

    // ✅ Constructor injection (MANDATORY)
    public VendorServiceImpl(VendorRepository vendorRepository) {
        this.vendorRepository = vendorRepository;
    }

    @Override
    public Vendor createVendor(Vendor vendor) {

        // Business rule: vendorName must be unique
        if (vendorRepository.existsByVendorName(vendor.getVendorName())) {
            throw new ValidationException("Duplicate vendor name");
        }

        try {
            return vendorRepository.save(vendor);
        } catch (DataIntegrityViolationException ex) {
            // DB constraint issues must return 400, not 500
            throw new ValidationException("Invalid vendor data");
        }
    }

    @Override
    public Vendor getVendor(Long id) {
        return vendorRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Vendor not found"));
    }

    @Override
    public List<Vendor> getAllVendors() {
        return vendorRepository.findAll();
    }
}

