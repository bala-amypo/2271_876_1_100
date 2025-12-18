package com.example.demo.service;

import com.example.demo.model.VendorDocument;
import java.util.List;

public interface VendorDocumentService {
    VendorDocument upload(Long vendorId, Long typeId, VendorDocument doc);
    List<VendorDocument> getByVendor(Long vendorId);
    VendorDocument get(Long id);
}
