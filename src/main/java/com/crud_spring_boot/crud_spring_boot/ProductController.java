package com.crud_spring_boot.crud_spring_boot;

import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<Product>>> getAllProducts(){
        List<Product> products = productService.getAllProducts();
        ApiResponse<List<Product>> response = new ApiResponse<>(true, "Products retrieved successfullyy", products);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Product>> getProductById(@PathVariable UUID id){
        Product product = productService.getProductById(id).orElseThrow();
        ApiResponse<Product> response = new ApiResponse<>(true, "Product retrieved successfully", product);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Product>> createProduct(@RequestBody Product product){
        Product newProduct = productService.createProduct(product);
        ApiResponse<Product> response = new ApiResponse<>(true, "Succesfully created product", newProduct);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Product>> updateProduct(@PathVariable UUID id, @RequestBody Product productDetails){
        Product updatedProduct = productService.updateProduct(id, productDetails);
        ApiResponse<Product> response = new ApiResponse<>(true, "Successfully updated product", updatedProduct);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteProduct(@PathVariable UUID id){
        productService.deleteProduct(id);
        ApiResponse<Void> response = new ApiResponse<>(true, "Successfully delete product", null);
        return ResponseEntity.ok(response);
    }
}

