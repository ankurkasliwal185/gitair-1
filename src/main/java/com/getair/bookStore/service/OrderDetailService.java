package com.getair.bookStore.service;


import com.getair.bookStore.exception.BookStoreException;
import com.getair.bookStore.exception.ErrorCodeEnum;
import com.getair.bookStore.model.*;
import com.getair.bookStore.repository.OrderDetailRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderDetailService {
    private final OrderDetailRepository orderDetailRepository;
    private final ProductService productService;
    private final CustomerService customerService;


    @Transactional
    public OrderDetail createNewOrder(CreateOrderRequest request) {
        log.info("******** Incoming OrderCreateRequest Start ********");
//        log.info("createNewOrder-start {}", kv("order", request));
        OrderDetail detail = new OrderDetail();
        List<Product> productList = new ArrayList<>();
        Double totalPrice = 0.0;
        Customer customer = customerService.getCustomerById(request.getCustomerId());
        if (!CollectionUtils.isEmpty(request.getProducts())) {
            for (OrderProducts product : request.getProducts()) {
                Product dbProduct = productService.getProductById(product.getProductId());
                totalPrice += calculatePrice(product, dbProduct.getPrice());
                productList.add(dbProduct);
                updateStockRecords(product);
                dbProduct.setQuantity(product.getQuantity());
            }
            detail.setCustomerId(customer.getId());
            detail.setTotalPrice(totalPrice);
            detail.setProducts(productList);
            orderDetailRepository.save(detail);

            log.info("******** Incoming OrderCreateRequest End ********");
        } else {
            throw new BookStoreException(ErrorCodeEnum.PRODUCT_NOT_FOUND);
        }
        return detail;
    }



    private Double calculatePrice(OrderProducts product, Double price) {
        return product.getQuantity() * price;
    }

    private void updateStockRecords(OrderProducts product) {
        UpdateProductQuantityRequest request = new UpdateProductQuantityRequest();
        request.setId(product.getProductId());
        request.setQuantity(product.getQuantity());
        productService.updateProductQuantity(request);

    }


    public List<OrderDetail> getCustomerOrder(Long customerId) {
        List<OrderDetail> orderDetail = orderDetailRepository.findByCustomerId(customerId);
        if (orderDetail != null) {
            return orderDetail;
        } else {
            throw new BookStoreException(ErrorCodeEnum.ORDER_NOT_FOUND);
        }
    }


    public OrderDetail getOrderById(Long id) {
        log.info("******** Incoming  getOrderById Start ********");
        Optional<OrderDetail> dbCustomerEntity = orderDetailRepository.findById(id);
        if (dbCustomerEntity.isPresent()) {
            log.info("******** Incoming getOrderById End ********");
            return dbCustomerEntity.get();
        } else {
            throw new BookStoreException(ErrorCodeEnum.ORDER_NOT_FOUND);
        }
    }
}
