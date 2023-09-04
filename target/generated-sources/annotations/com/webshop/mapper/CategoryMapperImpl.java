package com.webshop.mapper;

import com.webshop.domain.Category;
import com.webshop.domain.Category.CategoryBuilder;
import com.webshop.domain.dto.CategoryPostDTO;
import com.webshop.domain.dto.CategoryPutDTO;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-09-04T18:36:41-0300",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 17.0.7 (Amazon.com Inc.)"
)
@Component
public class CategoryMapperImpl extends CategoryMapper {

    @Override
    public Category toCategory(CategoryPostDTO categoryPostDTO) {
        if ( categoryPostDTO == null ) {
            return null;
        }

        CategoryBuilder category = Category.builder();

        category.name( categoryPostDTO.getName() );

        return category.build();
    }

    @Override
    public Category toCategory(CategoryPutDTO categoryPutDTO) {
        if ( categoryPutDTO == null ) {
            return null;
        }

        CategoryBuilder category = Category.builder();

        category.id( categoryPutDTO.getId() );
        category.name( categoryPutDTO.getName() );

        return category.build();
    }
}
