package com.mukesh.grocery.controller;

import com.mukesh.grocery.model.Orders;
import com.mukesh.grocery.model.Product;
import com.mukesh.grocery.model.dto.OrderDto;
import com.mukesh.grocery.services.OrderService;
import com.mukesh.grocery.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private ProductService productService;

    @Autowired
    private OrderService orderService;

    /*View the list of available grocery items*/
    @GetMapping("/product")
    public ResponseEntity<List<Product>> findAll(){
        return ResponseEntity.ok(productService.getAllProducts());
    }

    /*View the grocery item by product Id*/
    @GetMapping("/product/{productId}")
    public ResponseEntity<Product> findProductById(@PathVariable("productId") int productId){
        return ResponseEntity.ok(productService.findProductById(productId));
    }

    /*Ability to book multiple grocery items in a single order*/
    @PostMapping("/book/{userId}")
    public ResponseEntity<List<Product>> bookOrder(@RequestBody List<Product> productList, @PathVariable("userId") long userId){
        return ResponseEntity.ok(productService.bookOrder(productList,userId));
    }

    @GetMapping("/book/{userId}")
    public ResponseEntity<OrderDto> userOrderById(@PathVariable("userId") long userId){
        return ResponseEntity.ok(orderService.userOrderById(userId));
    }

}
