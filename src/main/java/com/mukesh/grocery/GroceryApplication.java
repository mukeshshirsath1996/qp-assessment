package com.mukesh.grocery;

import com.mukesh.grocery.enums.Category;
import com.mukesh.grocery.enums.Role;
import com.mukesh.grocery.enums.Units;
import com.mukesh.grocery.model.Orders;
import com.mukesh.grocery.model.Product;
import com.mukesh.grocery.model.User;
import com.mukesh.grocery.repository.OrderRepository;
import com.mukesh.grocery.repository.ProductRepository;
import com.mukesh.grocery.repository.UserRepository;
import com.mukesh.grocery.utility.OfferUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class GroceryApplication implements CommandLineRunner {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrderRepository orderRepository;

    public static void main(String[] args) {
        SpringApplication.run(GroceryApplication.class, args);
    }


    @Override
    public void run(String... args) throws Exception {
        List<Product> productList = productRepository.findAll();
        if (CollectionUtils.isEmpty(productList)) {
            Product product = new Product();
            product.setProductName("India Gate Basmati Rice");
            product.setQuantity(5);
            product.setCategory(Category.BASMATI_RICE.getCategory());
            product.setOriginalPrice(406);
            product.setOfferPrice(369);
            product.setDiscountPercentage(OfferUtil.calculateDiscount(product.getOriginalPrice(), product.getOfferPrice()));
            product.setUnit(Units.GRAM.getUnit());
            productRepository.save(product);

            User user = new User();
            user.setUserName("Mukesh");
            user.setUserPassword("123");
            user.setRole(Role.USER.name());
            userRepository.save(user);

            Orders order = new Orders();
            order.setUser(user);
            order.setProduct(product);
            orderRepository.save(order);

        }
    }
}
