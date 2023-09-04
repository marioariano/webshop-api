package com.webshop.domain.dto;

import lombok.Data;

/**
 * Class PostDTO of Item
 */
@Data
public class ItemPostDTO {
    private Integer quantity;
    private Long orderId;
    private Long productId;
}
