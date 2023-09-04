package com.webshop.service;

import com.webshop.domain.Customer;
import com.webshop.domain.Order;
import com.webshop.domain.dto.OrderPostDTO;
import com.webshop.domain.dto.OrderPutDTO;
import com.webshop.mapper.OrderMapper;
import com.webshop.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * Class Service of Order
 *
 * @author Mario Ariano
 */
@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    /**
     * Insert an Order into the database
     *
     * @param orderPostDTO OrderPostDTO
     * @return Order
     */
    public Order save(OrderPostDTO orderPostDTO) {
        Order order = OrderMapper.INSTANCE.toOrder(orderPostDTO);
        return orderRepository.save(order);
    }

    /**
     * List all orders
     *
     * @return List of Product
     */
    public List<Order> listAll() {
        return orderRepository.findAll();
    }

    /**
     * Search an Order by ID
     *
     * @param id ID of Order
     * @return Order
     */
    public Order findById(long id) {
        Optional<Order> optional = orderRepository.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        }
        throw new RuntimeException();
    }

    /**
     * Search orders by parameters
     *
     * @param id ID of Order
     * @param registerDate Register Date of Order
     * @param customer ID of Customer from Order
     * @return List of Order
     */
    public List<Order> findByParameter(Long id, LocalDateTime registerDate, Customer customer) {
        return orderRepository.findByIdOrRegisterDateOrCustomer(id, registerDate, customer);
    }

    /**
     * Search all orders with pagination
     *
     * @param pageable Pageable
     * @return Page of Order
     */
    public Page<Order> listPage(Pageable pageable) {
        return orderRepository.findAll(pageable);
    }

    /**
     * Edit an order by ID in the database
     *
     * @param orderPutDTO Body PutDTO of Order
     * @return Order
     */
    public Order update(OrderPutDTO orderPutDTO) {
        Order savedOrder = findById(orderPutDTO.getId());
        Order order = OrderMapper.INSTANCE.toOrder(orderPutDTO);
        order.setId(savedOrder.getId()); // Double check if ID is correct
        order.setRegisterDate(savedOrder.getRegisterDate()); // fill registerDate
        return orderRepository.save(order);
    }

    /**
     * Delete an order by ID in the database
     *
     * @param id ID of Order
     */
    public void delete(long id) {
        orderRepository.delete(findById(id));
    }
}
