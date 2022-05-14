package com.getair.bookStore.model;

import lombok.*;

import javax.persistence.Entity;
import java.math.BigDecimal;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Statistic {
    private Long customerId;
    private int totalOrder;
    private double totalAmount;
    private int totalPurchaseBookCount;
    private String month;
}
