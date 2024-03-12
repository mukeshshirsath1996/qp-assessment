package com.mukesh.grocery.repository;

import com.mukesh.grocery.model.Orders;
import com.mukesh.grocery.model.Product;
import com.mukesh.grocery.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Orders,Long> {

    List<Orders> findByProductId(long productid);

    List<Orders> findOrderByUser(User userId);
}
