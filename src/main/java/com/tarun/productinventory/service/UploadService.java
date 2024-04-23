package com.tarun.productinventory.service;

import com.tarun.productinventory.entity.Product;
import com.tarun.productinventory.repository.ProductRepository;
import com.tarun.productinventory.util.CSVUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public class UploadService {

    @Autowired
    private ProductRepository productRepository;

    public void save (MultipartFile file) {

        List<Product> productList= CSVUtils.parseCSV(file);
        productRepository.saveAll(productList);
    }
}

