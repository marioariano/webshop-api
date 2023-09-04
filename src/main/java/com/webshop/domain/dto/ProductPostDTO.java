package com.webshop.domain.dto;

import lombok.Data;

/**
 * Class PostDTO of Product
 */
@Data
public class ProductPostDTO {
    private String name;
    private String description;
    private Double price;
    private Long categoryId;
}
