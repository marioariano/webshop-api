package com.webshop.domain.dto;

import lombok.Data;

/**
 * Class PutDTO of Item
 */
@Data
public class ItemPutDTO {
    private Long id;
    private Integer quantity;
    private Long orderId;
    private Long productId;
}
