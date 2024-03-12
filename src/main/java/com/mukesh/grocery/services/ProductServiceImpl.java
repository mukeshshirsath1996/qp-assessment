package com.mukesh.grocery.services;

import com.mukesh.grocery.exceptions.ProductNotAvailable;
import com.mukesh.grocery.exceptions.ProductNotFoundException;
import com.mukesh.grocery.exceptions.UserNotFound;
import com.mukesh.grocery.model.Orders;
import com.mukesh.grocery.model.Product;
import com.mukesh.grocery.model.User;
import com.mukesh.grocery.repository.OrderRepository;
import com.mukesh.grocery.repository.ProductRepository;
import com.mukesh.grocery.repository.UserRepository;
import com.mukesh.grocery.utility.OfferUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;
    
    @Autowired
    private OrderRepository orderRepository;
    
    @Autowired
    private UserService userService;

    @Override
    public List<Product> getAllProducts() {
        List<Product> productList = productRepository.findAll();
        if(CollectionUtils.isEmpty(productList)) {
            throw new ProductNotFoundException("Product list is empty");
        }else {
            return productList;
        }
    }

    @Override
    public Product deleteProductById(long productId) {
        
        Product product = findProductById(productId);
        List<Orders> ordersList = orderRepository.findByProductId(productId);
        //change it
        for(Orders order : ordersList){
            orderRepository.deleteById(order.getOrderId());
        }
        if(!ObjectUtils.isEmpty(product)){
            productRepository.deleteById(productId);
        }
        return product;
    }

    @Override
    public Product findProductById(long productId) {
            return productRepository.findById(productId)
                    .orElseThrow(()-> new ProductNotFoundException(String.format("Product ID %s not found",productId)));
    }

    @Override
    public Product addProduct(Product product){
        product.setDiscountPercentage(OfferUtil.calculateDiscount(product.getOriginalPrice(), product.getOfferPrice()));
        return productRepository.save(product);
    }

    @Override
    public List<Product> bookOrder(List<Product> products, long userId) {

        isProductQuantityAvailable(products);

        User user = userService.findByUserId(userId);
        List<Product> productList = setProductListWithDiscountedPrice(products);

        for(Product product : productList){
            Orders order = new Orders();
            order.setProduct(product);
            order.setUser(user);
            order.setDate(LocalDateTime.now());
            order.setQuantity(product.getQuantity());
            order.setTotalAmount(product.getOfferPrice()*product.getQuantity());

            orderRepository.save(order);
            Product product1 = findProductById(product.getId());
            product1.setQuantity(product1.getQuantity()-product.getQuantity());
            productRepository.save(product1);
        }
        return productList;
    }

    private boolean isProductQuantityAvailable(List<Product> userProductList) {
        for(Product userproduct :userProductList){
            Product availableproduct = findProductById(userproduct.getId());
            if(userproduct.getQuantity()>availableproduct.getQuantity()){
                throw new ProductNotAvailable(String.format("Quantity - %s %s is not availble in inventory",userproduct.getQuantity(),userproduct.getProductName()));
            }
        }
        return true;
    }

    private List<Product> setProductListWithDiscountedPrice(List<Product> products) {
        return products.stream()
                .map(product -> {
                    product.setDiscountPercentage(OfferUtil.calculateDiscount(product.getOriginalPrice(), product.getOfferPrice()));
                    return product;
                }).collect(Collectors.toList());
    }

    @Override
    public List<Product> addAllProducts(List<Product> products) {
        productRepository.saveAll(setProductListWithDiscountedPrice(products));
        return products;
    }
}
