package com.mukesh.grocery.model.dto;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public class OrderDto {
    private long orderId;
    private double totalAmount;
    private List<ProductDto> products;

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public List<ProductDto> getProducts() {
        return products;
    }

    public void setProducts(List<ProductDto> products) {
        this.products = products;
    }
}
