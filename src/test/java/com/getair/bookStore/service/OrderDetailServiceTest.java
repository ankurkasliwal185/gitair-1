package com.getair.bookStore.service;



import com.getair.bookStore.exception.BookStoreException;
import com.getair.bookStore.model.CreateOrderRequest;
import com.getair.bookStore.model.Customer;
import com.getair.bookStore.model.OrderDetail;
import com.getair.bookStore.model.Product;
import com.getair.bookStore.repository.OrderDetailRepository;
import com.getair.bookStore.utils.Utils;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class OrderDetailServiceTest {
    @InjectMocks
    private OrderDetailService orderDetailService;

    @Mock
    OrderDetailRepository orderDetailRepository;

    @Mock
    CustomerService customerService;

    @Mock
    ProductService productService;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();


    @Test
    public void createNewOrder_shouldBeSuccess() {
        Customer customer = Utils.createCustomer();
        Product product = Utils.createProduct();
        when(customerService.getCustomerById(customer.getId())).thenReturn(customer);
        when(productService.getProductById(product.getId())).thenReturn(product);
        OrderDetail result = orderDetailService.createNewOrder(Utils.createOrderRequest());
        Assert.assertNotNull(result);
    }

    @Test
    public void createNewOrder_shouldBeError_productEmpty() {
        expectedException.expect(BookStoreException.class);
        Customer customer = Utils.createCustomer();
        when(customerService.getCustomerById(customer.getId())).thenReturn(customer);
        CreateOrderRequest request = Utils.createOrderRequest();
        request.setProducts(null);
        orderDetailService.createNewOrder(request);

    }

    @Test
    public void getCustomerOrder_shouldBeSuccess() {
        Customer customer = Utils.createCustomer();
        when(orderDetailRepository.findByCustomerId(customer.getId())).thenReturn((List<OrderDetail>) Utils.createOrderDetail());
        List<OrderDetail> result = orderDetailService.getCustomerOrder(customer.getId());
        Assert.assertNotNull(result);
    }

    @Test
    public void getCustomerOrder_shouldBeError_orderNotFound() {
        expectedException.expect(BookStoreException.class);
        Customer customer = Utils.createCustomer();
        when(orderDetailRepository.findByCustomerId(customer.getId())).thenReturn(null);
        orderDetailService.getCustomerOrder(customer.getId());
    }

}
