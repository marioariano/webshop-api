package com.webshop.mapper;

import com.webshop.domain.Category;
import com.webshop.domain.dto.CategoryPostDTO;
import com.webshop.domain.dto.CategoryPutDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * Class for Category Mapping
 *
 * @author Mario Ariano
 */
@Mapper(componentModel = "spring")
public abstract class CategoryMapper {

    /**
     * INSTANCE
     */
    public static final CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

    /**
     * Pass the attributes of CategoryPostDTO to Category
     *
     * @param categoryPostDTO CategoryPostDTO
     * @return Category
     */
    public abstract Category toCategory(CategoryPostDTO categoryPostDTO);

    /**
     Pass the attributes of CategoryPutDTO to Category
     *
     * @param categoryPutDTO CategoryPutDTO
     * @return Category
     */
    public abstract Category toCategory(CategoryPutDTO categoryPutDTO);
}
