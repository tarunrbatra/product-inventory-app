package com.tarun.productinventory.controller;

import com.tarun.productinventory.entity.Product;
import com.tarun.productinventory.service.UploadService;
import com.tarun.productinventory.util.CSVUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/upload")
public class UploadInventoryController {

    @Autowired
    private UploadService uploadService;

    @PostMapping("/csv")
    public String uploadCSV (@RequestParam("csvFile") MultipartFile file) {

        if(!CSVUtils.isCSVFile(file)) {
            return "Not a valid csv file: " + file.getName();
        }
        uploadService.save(file);
        return "CSV file saved successfully: " + file.getName();
    }

}
