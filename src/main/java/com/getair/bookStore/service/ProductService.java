package com.getair.bookStore.service;


import com.getair.bookStore.exception.BookStoreException;
import com.getair.bookStore.exception.ErrorCodeEnum;
import com.getair.bookStore.model.Product;
import com.getair.bookStore.model.UpdateProductQuantityRequest;
import com.getair.bookStore.repository.ProductRepository;
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
public class ProductService {
    private final ProductRepository productRepository;


    @Transactional
    public Product createProduct(Product product) {
        if(product == null){
            throw new BookStoreException(ErrorCodeEnum.CONTENT_NOT_FOUND_ERROR);
        }
        log.info("******** Incoming ProductCreateRequest Start ********");
//        log.info("createProduct-start {}", kv("product", product));
        log.info("******** Incoming ProductCreateRequest End ********");
        return productRepository.save(product);

    }

    @Transactional
    public Product updateProductStock(Long productId, Integer quantity) {
        Product dbProduct = this.getProductById(productId);
        if(dbProduct == null){
            throw new BookStoreException(ErrorCodeEnum.CONTENT_NOT_FOUND_ERROR);
        }
        Integer oldQuantity= dbProduct.getQuantity();
        Integer newQuantity=oldQuantity+quantity;
        dbProduct.setQuantity(newQuantity);

        log.info("******** Incoming ProductCreateRequest Start ********");
//        log.info("createProduct-start {}", kv("product", product));
        log.info("******** Incoming ProductCreateRequest End ********");
        return productRepository.save(dbProduct);

    }

    @Transactional
    public void updateProductQuantity(UpdateProductQuantityRequest request) {
        log.info("******** Incoming updateProductQuantity Start ********");
 //       log.info("UpdateProductRequest-start {}", kv("request", request));
        log.info("******** Incoming updateProductQuantity End ********");
        if (request.getId() != null) {
            Product dbProduct = this.getProductById(request.getId());
            Integer differance = dbProduct.getQuantity() - request.getQuantity();
            if (differance > 0) {
                dbProduct.setQuantity(differance);
                productRepository.save(dbProduct);
            } else {
                throw new BookStoreException(ErrorCodeEnum.PRODUCT_NOT_AVAIBLE);
            }
        } else {
            throw new BookStoreException(ErrorCodeEnum.PRODUCT_ID_MUST_NOT_BLANK);
        }
    }

    public Product getProductById(Long id) {
        log.info("******** Incoming  getProductById Start ********");
        Optional<Product> dbProductEntity = productRepository.findById(id);
        if (dbProductEntity.isPresent()) {
            log.info("******** Incoming getCustomerById End ********");
            return dbProductEntity.get();
        } else {
            throw new BookStoreException(ErrorCodeEnum.PRODUCT_NOT_FOUND);
        }
    }

    public Page<Product> getAllProducts(int pageIndex, int pageSize) {
        log.info("******** Incoming  getAllProducts Start ********");
   //     log.info("getAllEvents {} {}", kv("pageIndex", pageIndex), kv("pageSize", pageSize));
        Page<Product> response = productRepository.findAll(PageRequest.of(pageIndex, pageSize));
        log.info("******** Incoming getAllProducts End ********");
        return response;
    }
}
