package com.mukesh.grocery.services;

import com.mukesh.grocery.model.Orders;
import com.mukesh.grocery.model.Product;
import com.mukesh.grocery.model.User;
import com.mukesh.grocery.model.dto.OrderDto;
import com.mukesh.grocery.model.dto.ProductDto;
import com.mukesh.grocery.repository.OrderRepository;
import com.mukesh.grocery.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private UserService userService;

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public OrderDto userOrderById(long userId) {
        User user = userService.findByUserId(userId);
        List<Orders> userOrderList = orderRepository.findOrderByUser(user);
            OrderDto orderDto = new OrderDto();
            orderDto.setOrderId(userOrderList.get(0).getOrderId());
            orderDto.setProducts(getProductData(userOrderList));
            orderDto.setTotalAmount(getTotalAmount(userOrderList));
            return orderDto;
    }

    private List<ProductDto> getProductData(List<Orders> userOrderList) {
      return userOrderList.stream()
              .map(orders -> {
            ProductDto productDto = new ProductDto();
            Product product = orders.getProduct();
            productDto.setProductId(product.getId());
            productDto.setProductAmount(orders.getTotalAmount());
            productDto.setProductname(product.getProductName());
            productDto.setProductcategory(product.getCategory());
            productDto.setOrderDate(orders.getDate().toLocalDate());
            productDto.setTotalQuantity(orders.getQuantity()+" "+product.getUnit());
            return productDto;
        }).collect(Collectors.toList());
    }

    private double getTotalAmount(List<Orders> userOrderList) {
        return userOrderList.stream().map(order -> order.getTotalAmount())
                .mapToInt(el -> el.intValue())
                .sum();
    }
}
