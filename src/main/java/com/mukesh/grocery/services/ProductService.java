package com.mukesh.grocery.services;

import com.mukesh.grocery.model.Product;
import java.util.List;

public interface ProductService {

    List<Product> getAllProducts();

    Product deleteProductById(long productId);
    Product findProductById(long productId);
    Product addProduct(Product product);
    List<Product> bookOrder(List<Product> productList, long userId);

    List<Product> addAllProducts(List<Product> products);
}
