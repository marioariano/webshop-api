package com.webshop.repository;

import com.webshop.domain.Customer;
import com.webshop.domain.Item;
import com.webshop.domain.Order;
import com.webshop.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Class Repository of Item
 *
 * @author Mario Ariano
 */
@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

    /**
     * Search a product by parameters
     *
     * @param id ID of Order
     * @param quantity quantity of item
     * @param product ID of Product from Item
     * @param order ID of Order from Item
     * @return List of Item
     */
    List<Item> findByIdOrQuantityOrProductOrOrder(
            Long id,
            Integer quantity,
            Product product,
            Order order);
}
