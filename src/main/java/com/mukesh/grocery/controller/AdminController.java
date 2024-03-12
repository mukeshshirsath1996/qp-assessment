package com.mukesh.grocery.controller;

import com.mukesh.grocery.model.Product;
import com.mukesh.grocery.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private ProductService productService;

    /*update new grocery items to the system*/
    @PutMapping("/product")
    public ResponseEntity<Product> addProduct(@RequestBody Product product){
        return ResponseEntity.ok(productService.addProduct(product));
    }

    /*Add list of grocery items to the system*/
    @PostMapping("/product")
    public ResponseEntity<List<Product>> addAllProduct(@RequestBody List<Product> products){
        return ResponseEntity.ok(productService.addAllProducts(products));
    }

    /*View existing grocery items*/
    @GetMapping("/product")
    public ResponseEntity<List<Product>> findAll(){
        return ResponseEntity.ok(productService.getAllProducts());
    }

    /*View existing grocery item by Id*/
    @GetMapping("/product/{productId}")
    public ResponseEntity<Product> findProductById(@PathVariable("productId") int productId){
        return ResponseEntity.ok(productService.findProductById(productId));
    }

    /*Remove grocery items from the system*/
    @DeleteMapping("/product/{productId}")
    public ResponseEntity<Product> deleteProductById(@PathVariable("productId") long productId){
        return ResponseEntity.ok(productService.deleteProductById(productId));
    }


}
