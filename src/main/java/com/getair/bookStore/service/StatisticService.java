package com.getair.bookStore.service;

import com.getair.bookStore.exception.BookStoreException;
import com.getair.bookStore.exception.ErrorCodeEnum;
import com.getair.bookStore.model.Customer;
import com.getair.bookStore.model.OrderDetail;
import com.getair.bookStore.model.Statistic;
import com.getair.bookStore.repository.OrderDetailRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class StatisticService {
    private final OrderDetailRepository orderDetailRepository;
    private final ProductService productService;
    private final CustomerService customerService;

    @Transactional
    public List<Statistic> getMonthlyStatistic(Long customerId) {
        if(customerId == null){
            throw new BookStoreException(ErrorCodeEnum.CUSTOMER_NOT_FOUND);
        }
        List<OrderDetail> orderDetails= orderDetailRepository.findByCustomerId(customerId);
        List<Statistic> statisticList = new ArrayList<>();
        for(OrderDetail orderDetail:orderDetails) {
         Statistic statistic = new Statistic();
         statistic.setMonth(String.valueOf(orderDetail.getCreateDateTime().getMonth()));
         statistic.setTotalAmount(orderDetail.getTotalPrice());
         statistic.setTotalOrder(1);
         statistic.setCustomerId(orderDetail.getCustomerId());
         statistic.setTotalPurchaseBookCount(orderDetail.getProducts().size());
         statisticList.add(statistic);
        }
        log.info("******** Incoming Customer Create Start ********"+ orderDetails.toString());
//        log.info("createCustomer-start {}", kv("Customer", customer));
        log.info("******** Incoming  Customer Create End ********");
        return statisticList;
    }
}
