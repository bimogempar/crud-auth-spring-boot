package com.crud_spring_boot.crud_spring_boot.application.service;

import com.crud_spring_boot.crud_spring_boot.domain.model.Product;
import com.crud_spring_boot.crud_spring_boot.domain.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }

    public Optional<Product> getProductById(UUID id) {
        return productRepository.findById(id);
    }

    public Product createProduct(Product product){
        if (product.getId() == null) {
            product.setId(UUID.randomUUID());
        }
        return productRepository.save(product);
    }

    public Product updateProduct(UUID id, Product productDetails) {
        Product product = productRepository.findById(id).orElseThrow();
        product.setName(productDetails.getName());
        product.setPrice(productDetails.getPrice());
        return productRepository.save(product);
    }

    public void deleteProduct(UUID id){
        Product product = productRepository.findById(id).orElseThrow();
        productRepository.delete(product);
    }

}
