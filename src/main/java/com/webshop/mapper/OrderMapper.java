package com.webshop.mapper;

import com.webshop.domain.Customer;
import com.webshop.domain.Order;
import com.webshop.domain.dto.OrderPostDTO;
import com.webshop.domain.dto.OrderPutDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

/**
 * Class for Order Mapping
 *
 * @author Mario Ariano
 */
@Mapper(componentModel = "spring")
public abstract class OrderMapper {

    /**
     * INSTANCE
     */
    public static final OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

    /**
     * Pass the attributes of OrderPostDTO to Order
     *
     * @param orderPostDTO OrderPostDTO
     * @return Order
     */
    public Order toOrder(OrderPostDTO orderPostDTO) {
        if ( orderPostDTO == null ) {
            return null;
        }

        Order.OrderBuilder order = Order.builder();
        order.customer( Customer.builder().id(orderPostDTO.getCustomerId()).build());
        order.registerDate(LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES));

        return order.build();
    }

    /**
     * Pass the attributes of OrderPutDTO to Order
     *
     * @param orderPutDTO OrderPutDTO
     * @return Order
     */
    public Order toOrder(OrderPutDTO orderPutDTO) {
        if ( orderPutDTO == null ) {
            return null;
        }

        Order.OrderBuilder order = Order.builder();

        order.id( orderPutDTO.getId() );
        order.customer( Customer.builder().id(orderPutDTO.getCustomerId()).build() );

        return order.build();
    }
}
