package com.tarun.productinventory.util;

import com.tarun.productinventory.entity.Product;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CSVUtils {

    public static boolean isCSVFile(MultipartFile file) {
        //TODO: check the type properly
        return true;
    }

    public static List<Product> parseCSV(MultipartFile file) {
        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(file.getInputStream(), "UTF-8"));
             CSVParser csvParser = new CSVParser(fileReader, CSVFormat.valueOf("UTF-8"))) {

            List<Product> products = new ArrayList<>();
            Iterable<CSVRecord> csvRecords = csvParser.getRecords();
            for (CSVRecord csvRecord : csvRecords) {
                Product product = new Product(
                        Integer.parseInt(csvRecord.get("id")),
                        csvRecord.get("name"),
                        csvRecord.get("category"),
                        Integer.parseInt(csvRecord.get("quantity")),
                        Double.parseDouble(csvRecord.get("price")));
                products.add(product);
            }
            return products;
        } catch (IOException e) {
            throw new RuntimeException("Failed to parse CSV file: " + e.getMessage());
        }
    }

}
