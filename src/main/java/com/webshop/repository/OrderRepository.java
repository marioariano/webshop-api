package com.webshop.repository;

import com.webshop.domain.Customer;
import com.webshop.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Class Repository of Order
 *
 * @author Mario Ariano
 */
@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    /**
     * Search orders by parameters
     *
     * @param id Order ID
     * @param registerDate Register Date of Order
     * @param customer ID of Customer from Order
     * @return List of Order
     */
    List<Order> findByIdOrRegisterDateOrCustomer(
            Long id,
            LocalDateTime registerDate,
            Customer customer);
}
