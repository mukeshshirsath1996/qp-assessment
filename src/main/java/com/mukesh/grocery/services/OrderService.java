package com.mukesh.grocery.services;

import com.mukesh.grocery.model.Orders;
import com.mukesh.grocery.model.dto.OrderDto;

import java.util.List;

public interface OrderService {
    OrderDto userOrderById(long userId);
}
