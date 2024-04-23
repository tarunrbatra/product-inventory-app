package com.tarun.productinventory.service;

import com.tarun.productinventory.entity.Product;
import com.tarun.productinventory.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;


    public Product saveProduct (Product product) {
        return productRepository.save(product);
    }

    public Product getProductById (int id) {
        //TODO: create an empty product if not there?
        return productRepository.findById(id).orElse(null);
    }

    public Product updateProduct (Product product) {
        Optional<Product> existingProductOptional = productRepository.findById(product.getId());
        // create a new one if product not present for update
        if(existingProductOptional.isPresent()) {
            existingProductOptional.get().setName(product.getName());
            existingProductOptional.get().setQuantity(product.getQuantity());
            existingProductOptional.get().setPrice(product.getPrice());
            return productRepository.save(existingProductOptional.get());
        }
        else {
            return productRepository.save(product);
        }
    }

    public String deleteProduct (int id) {
        productRepository.deleteById(id);
        return "Product with id " + id + " removed successfully!";
    }

    //TODO:to be implemented
    public String getProductsByCategory (String category) {

        return "";
    }

    public int getProductsStockByName (String name) {
        List<Product> productList = productRepository.findByName(name);
        return productList.size();
    }

    public List<Product> getAllProducts () {
        List<Product> productList = productRepository.findAll();
        return productList;
    }
}
