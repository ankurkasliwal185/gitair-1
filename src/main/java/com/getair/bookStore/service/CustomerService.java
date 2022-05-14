package com.getair.bookStore.service;

import com.getair.bookStore.exception.BookStoreException;
import com.getair.bookStore.exception.ErrorCodeEnum;
import com.getair.bookStore.model.Customer;
import com.getair.bookStore.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;


    @Transactional
    public Customer createCustomer(Customer customer) {
        if(customer == null){
            throw new BookStoreException(ErrorCodeEnum.CONTENT_NOT_FOUND_ERROR);
        }
        log.info("******** Incoming Customer Create Start ********");
//        log.info("createCustomer-start {}", kv("Customer", customer));
        log.info("******** Incoming  Customer Create End ********");
        return customerRepository.save(customer);
    }

    public Customer getCustomerById(Long id) {
        log.info("******** Incoming  getCustomerById Start ********");
        Optional<Customer> dbCustomerEntity = customerRepository.findById(id);
        if (dbCustomerEntity.isPresent()) {
            log.info("******** Incoming getCustomerById End ********");
            return dbCustomerEntity.get();
        } else {
            throw new BookStoreException(ErrorCodeEnum.CUSTOMER_NOT_FOUND);
        }
    }

    public Page<Customer> getAllCustomer(int pageIndex, int pageSize) {
        log.info("******** Incoming  getAllCustomer Start ********");
//        log.info("getAllEvents {} {}", kv("pageIndex", pageIndex), kv("pageSize", pageSize));
        Page<Customer> response = customerRepository.findAll(PageRequest.of(pageIndex, pageSize));
        log.info("******** Incoming  getAllCustomer End ********");
        return response;
    }
}
