package com.webshop.controller;

import com.webshop.domain.Item;
import com.webshop.domain.Order;
import com.webshop.domain.Product;
import com.webshop.domain.dto.ItemPostDTO;
import com.webshop.domain.dto.ItemPutDTO;
import com.webshop.domain.dto.OrderPutDTO;
import com.webshop.service.ItemService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Class to manage item routes
 *
 * @author Mario Ariano
 */
@RestController
@Log4j2
@RequiredArgsConstructor
@RequestMapping("items")
public class ItemController {

    private final ItemService itemService;

    /**
     * Insert a new Item
     *
     * @param itemPostDTO Body PostDTO of Item
     * @return Item
     */
    @ApiOperation(value = "Insert a new Item.")
    @PostMapping
    public ResponseEntity<Item> save(@RequestBody ItemPostDTO itemPostDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(itemService.save(itemPostDTO));
    }

    /**
     * Search an Item by ID
     *
     * @param id ID of Item
     * @return Item
     */
    @ApiOperation(value = "Search an Item by ID.")
    @GetMapping(path = "/{id}")
    public ResponseEntity<Item> findById(@PathVariable long id) {
        return ResponseEntity.ok(itemService.findById(id));
    }

    /**
     * Search all Items
     *
     * @return List of Item
     */
    @ApiOperation(value = "Search all Items.")
    @GetMapping
    public ResponseEntity<List<Item>> list() {
        return ResponseEntity.ok(itemService.listAll());
    }

    /**
     * Search an item by parameters
     *
     * @param id Order ID
     * @param quantity Item Quantity
     * @param product ID of Product of Item
     * @param order ID of Order of Item
     * @return List of Item
     */
    @ApiOperation(value = "Search item by parameters.")
    @GetMapping(path = "/find")
    public ResponseEntity<List<Item>> findByParameter(
            @RequestParam(required = false) Long id,
            @RequestParam(required = false) Integer quantity,
            @RequestParam(name = "productId", required = false) Product product,
            @RequestParam(name = "orderId", required = false) Order order) {

        List<Item> list = itemService.findByParameter(id, quantity, product, order);
        return ResponseEntity.ok(list);
    }

    /**
     * Search all items with pagination
     * You can use "size", "page" e "sort" parameters
     *
     * @param pageable Pageable
     * @return Page of Order
     */
    @ApiOperation(value = "Search all items with pagination." +
            " You can use \"size\", \"page\" e \"sort\" parameters")
    @GetMapping(path = "/page")
    public ResponseEntity<Page<Item>> listPage(Pageable pageable) {
        return ResponseEntity.ok(itemService.listPage(pageable));
    }

    /**
     * Edit an Item by ID
     *
     * @param itemPutDTO Body PutDTO of Item
     * @return Item
     */
    @ApiOperation(value = "Edit an Item by ID.")
    @PutMapping
    public ResponseEntity<Item> updateById(@RequestBody ItemPutDTO itemPutDTO) {
        return ResponseEntity.ok(itemService.update(itemPutDTO));
    }

    /**
     * Delete an Item by ID
     *
     * @param id ID of Item
     * @return NULL
     */
    @ApiOperation(value = "Delete an Item by ID.")
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable long id) {
        itemService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
