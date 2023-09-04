package com.webshop.mapper;

import com.webshop.domain.Category;
import com.webshop.domain.Product;
import com.webshop.domain.dto.ProductPostDTO;
import com.webshop.domain.dto.ProductPutDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

/**
 * Class for Product Mapping
 *
 * @author Mario Ariano
 */
@Mapper(componentModel = "spring")
public abstract class ProductMapper {

    /**
     * INSTANCE
     */
    public static final ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    /**
     * Pass the attributes of ProductPostDTO to Product
     *
     * @param productPostDTO ProductPostDTO
     * @return Product
     */
    public Product toProduct(ProductPostDTO productPostDTO) {
        if ( productPostDTO == null ) {
            return null;
        }

        Product product = new Product();

        product.setName( productPostDTO.getName() );
        product.setDescription( productPostDTO.getDescription() );
        product.setPrice( productPostDTO.getPrice() );
        product.setCategory(Category.builder().id(productPostDTO.getCategoryId()).build());
        product.setRegisterDate(LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES));

        return product;
    }

    /**
     * Pass the attributes of ProductPutDTO to Product
     *
     * @param productPutDTO ProductPutDTO
     * @return Product
     */
    public Product toProduct(ProductPutDTO productPutDTO) {
        if ( productPutDTO == null ) {
            return null;
        }

        Product.ProductBuilder product = Product.builder();

        product.id( productPutDTO.getId() );
        product.name( productPutDTO.getName() );
        product.description( productPutDTO.getDescription() );
        product.price( productPutDTO.getPrice() );
        product.category(Category.builder().id(productPutDTO.getCategoryId()).build());

        return product.build();
    }
}
