package com.webshop.mapper;

import com.webshop.domain.Item;
import com.webshop.domain.Order;
import com.webshop.domain.Product;
import com.webshop.domain.dto.ItemPostDTO;
import com.webshop.domain.dto.ItemPutDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * Class for Item Mapping
 *
 * @author Mario Ariano
 */
@Mapper(componentModel = "spring")
public abstract class ItemMapper {

    /**
     * INSTANCE
     */
    public static final ItemMapper INSTANCE = Mappers.getMapper(ItemMapper.class);

    /**
     * Pass the attributes of ItemPostDTO to Item
     *
     * @param itemPostDTO ItemPostDTO
     * @return Item
     */
    public Item toItem(ItemPostDTO itemPostDTO) {
        if ( itemPostDTO == null ) {
            return null;
        }

        Item.ItemBuilder item = Item.builder();

        item.quantity( itemPostDTO.getQuantity() );
        item.order(Order.builder().id(itemPostDTO.getOrderId()).build());
        item.product(Product.builder().id(itemPostDTO.getProductId()).build());

        return item.build();
    }

    /**
     * Pass the attributes of ItemPutDTO to Item
     *
     * @param itemPutDTO ItemPutDTO
     * @return Item
     */
    public Item toItem(ItemPutDTO itemPutDTO) {
        if ( itemPutDTO == null ) {
            return null;
        }

        Item.ItemBuilder item = Item.builder();

        item.id( itemPutDTO.getId() );
        item.quantity( itemPutDTO.getQuantity() );
        item.order(Order.builder().id(itemPutDTO.getOrderId()).build());
        item.product(Product.builder().id(itemPutDTO.getProductId()).build());

        return item.build();
    }

}
