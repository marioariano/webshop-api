package com.webshop.controller;

import com.webshop.domain.Customer;
import com.webshop.domain.Order;
import com.webshop.domain.dto.OrderPostDTO;
import com.webshop.domain.dto.OrderPutDTO;
import com.webshop.service.OrderService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Class to manage order routes
 *
 * @author Mario Ariano
 */
@RestController
@Log4j2
@RequiredArgsConstructor
@RequestMapping("orders")
public class OrderController {
    private final OrderService orderService;

    /**
     * Insert a new Order
     *
     * @param orderPostDTO Body PostDTO of Order
     * @return Order
     */
    @ApiOperation(value = "Insert a new Order.")
    @PostMapping
    public ResponseEntity<Order> save(@RequestBody OrderPostDTO orderPostDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(orderService.save(orderPostDTO));
    }

    /**
     * Search all orders
     *
     * @return List of Order
     */
    @ApiOperation(value = "Search all orders.")
    @GetMapping
    public ResponseEntity<List<Order>> list() {
        return ResponseEntity.ok(orderService.listAll());
    }

    /**
     * Search order by id
     *
     * @param id ID of Order
     * @return Order
     */
    @ApiOperation(value = "Search order by id.")
    @GetMapping(path = "/{id}")
    public ResponseEntity<Order> findById(@PathVariable long id) {
        return ResponseEntity.ok(orderService.findById(id));
    }

    /**
     * Search a product by parameters
     *
     * @param id Order ID
     * @param registerDate Order Register Date
     * @param customer ID of the Customer that owns the Order
     * @return List of Order
     */
    @ApiOperation(value = "Search an order by parameters.")
    @GetMapping(path = "/find")
    public ResponseEntity<List<Order>> findByParameter(
            @RequestParam(required = false) Long id,
            @DateTimeFormat(pattern = "yyyy/MM/dd HH:mm")
            @Pattern(regexp = "([0-9]{4}[/][0-1][0-9][/][0-3][0-9][ ][0-2][0-9][:][0-5][0-9])")
            @RequestParam(required = false) LocalDateTime registerDate,
            @RequestParam(name = "customerId", required = false) Customer customer) {

        List<Order> list = orderService.findByParameter(id, registerDate, customer);
        return ResponseEntity.ok(list);
    }

    /**
     * Search for all orders with pagination
     * You can use "size", "page" and "sort" parameters
     *
     * @param pageable Pageable
     * @return Page of Order
     */
    @ApiOperation(value = "Search for all orders with pagination." +
            " You can use \"size\", \"page\" e \"sort\" parameters")
    @GetMapping(path = "/page")
    public ResponseEntity<Page<Order>> listPage(Pageable pageable) {
        return ResponseEntity.ok(orderService.listPage(pageable));
    }

    /**
     * Edit an Order by ID
     *
     * @param orderPutDTO Body PutDTO of Order
     * @return Order
     */
    @ApiOperation(value = "Edit an Order by ID.")
    @PutMapping
    public ResponseEntity<Order> updateById(@RequestBody OrderPutDTO orderPutDTO) {
        return ResponseEntity.ok(orderService.update(orderPutDTO));
    }

    /**
     * Delete an Order by ID
     *
     * @param id Order ID
     * @return NULL
     */
    @ApiOperation(value = "Delete an Order by ID.")
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable long id) {
        orderService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
