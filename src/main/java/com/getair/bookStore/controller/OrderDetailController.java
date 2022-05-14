package com.getair.bookStore.controller;


import com.getair.bookStore.model.CreateOrderRequest;
import com.getair.bookStore.model.Customer;
import com.getair.bookStore.model.OrderDetail;
import com.getair.bookStore.service.OrderDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/order")
@RequiredArgsConstructor
public class OrderDetailController {
    private final OrderDetailService orderService;

    @PostMapping("newOrder")
    public ResponseEntity<List<OrderDetail>> createNewOrder(@Valid @RequestBody CreateOrderRequest request) {
        return new ResponseEntity(orderService.createNewOrder(request), HttpStatus.OK);
    }


    @GetMapping("{id}")
    public ResponseEntity<OrderDetail> getOrderById(@PathVariable Long id) {
        return ResponseEntity.ok(orderService.getOrderById(id));
    }
    @GetMapping
    public ResponseEntity<List<OrderDetail>> getCustomerOrders(@RequestParam Long customerId) {
        return ResponseEntity.ok(orderService.getCustomerOrder(customerId));
    }
}
