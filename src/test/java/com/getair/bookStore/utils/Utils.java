package com.getair.bookStore.utils;

import com.getair.bookStore.model.*;

import java.util.Arrays;
import java.util.UUID;

public class Utils {
    public static final int QUANTITY = 10;
    public static final double PRICE = 10.0;
    public static final double TOTAL_PRICE = 200.0;

    public static String generateRandomString() {
        String uuid = UUID.randomUUID().toString();
        return uuid;
    }

    public static Customer createCustomer() {
        Customer customer = new Customer();
        customer.setAddress(generateRandomString());
        customer.setEmail(generateRandomString());
        customer.setName(generateRandomString());
        return customer;
    }

    public static Product createProduct() {
        Product product = new Product();
        product.setQuantity(QUANTITY);
        product.setName(generateRandomString());
        product.setPrice(PRICE);
        return product;
    }

    public static OrderDetail createOrderDetail() {
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setProducts(Arrays.asList(createProduct()));
        orderDetail.setCustomerId(createCustomer().getId());
        orderDetail.setTotalPrice(TOTAL_PRICE);
        return orderDetail;
    }


    public static CreateOrderRequest createOrderRequest() {
        CreateOrderRequest createOrderRequest = new CreateOrderRequest();
        createOrderRequest.setCustomerId(createCustomer().getId());
        OrderProducts orderProducts = new OrderProducts();
        orderProducts.setProductId(createProduct().getId());
        orderProducts.setQuantity(QUANTITY);
        createOrderRequest.setProducts(Arrays.asList(orderProducts));
        return createOrderRequest;
    }
}
