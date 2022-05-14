package com.getair.bookStore.service;

import com.getair.bookStore.exception.BookStoreException;
import com.getair.bookStore.model.Product;
import com.getair.bookStore.repository.ProductRepository;
import com.getair.bookStore.utils.Utils;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.awt.print.Book;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ProductServiceTest {
    @InjectMocks
    private ProductService productService;

    @Mock
    ProductRepository productRepository;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();


    @Test
    public void createProduct_shouldBeSuccess() {
        Product product = Utils.createProduct();
        when(productRepository.save(any(Product.class))).thenReturn(product);
        Product result = productService.createProduct(product);
        Assert.assertNotNull(result);
    }

    @Test
    public void createProduct_shouldBeError_productNull() {
        expectedException.expect(BookStoreException.class);
        productService.createProduct(null);
    }

    @Test
    public void getProductById_shouldBeSuccess() {
        Product product = Utils.createProduct();
        when(productRepository.findById(product.getId())).thenReturn(java.util.Optional.of(product));
        Product result = productService.getProductById(product.getId());
        Assert.assertNotNull(result);
    }

    @Test
    public void getProductById_shouldBeError_productNotFound() {
        expectedException.expect(BookStoreException.class);
        productService.getProductById(2l);
    }
}
