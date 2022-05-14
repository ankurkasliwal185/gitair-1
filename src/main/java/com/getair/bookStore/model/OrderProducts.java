package com.getair.bookStore.model;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderProducts {
    private Long productId;
    private Integer quantity;
}
