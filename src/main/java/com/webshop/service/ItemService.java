package com.webshop.service;

import com.webshop.domain.Item;
import com.webshop.domain.Order;
import com.webshop.domain.Product;
import com.webshop.domain.dto.ItemPostDTO;
import com.webshop.domain.dto.ItemPutDTO;
import com.webshop.mapper.ItemMapper;
import com.webshop.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Class Service of Item
 *
 * @author Mario Ariano
 */
@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;
    private final ProductService productService;
    private final OrderService orderService;

    /**
     * Insert an Item into the database
     *
     * @param itemPostDTO ItemPostDTO
     * @return Item
     */
    public Item save(ItemPostDTO itemPostDTO) {
        Item item = ItemMapper.INSTANCE.toItem(itemPostDTO);
        orderService.findById(itemPostDTO.getOrderId()); // Checking if the order exists
        Product product = productService.findById(itemPostDTO.getProductId()); // Checking if product exists
        item.setUnitPrice(product.getPrice()); // Set the price of item based on the price of product
        return itemRepository.save(item);
    }

    /**
     * List all items
     *
     * @return List of Item
     */
    public List<Item> listAll() {
        return itemRepository.findAll();
    }

    /**
     * Search an item by parameters
     *
     * @param id ID of Order
     * @param quantity Quantity of item
     * @param product ID of Product of Item
     * @param order ID of Order of Item
     * @return List of Item
     */
    public List<Item> findByParameter(Long id, Integer quantity, Product product, Order order) {
        return itemRepository.findByIdOrQuantityOrProductOrOrder(id, quantity, product, order);
    }

    /**
     * Search for an Item by ID in the database
     *
     * @param id ID of Item
     * @return Item
     */
    public Item findById(long id) {
        Optional<Item> optional = itemRepository.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        }
        throw new RuntimeException();
    }

    /**
     * Search for all items with pagination
     *
     * @param pageable Pageable
     * @return Page of Order
     */
    public Page<Item> listPage(Pageable pageable) {
        return itemRepository.findAll(pageable);
    }

    /**
     * Edit an item by ID in the database
     *
     * @param itemPutDTO Body PutDTO of Item
     * @return Item
     */
    public Item update(ItemPutDTO itemPutDTO) {
        findById(itemPutDTO.getId()); // Checking if ID of Item exists
        orderService.findById(itemPutDTO.getOrderId()); // Check if ID of order exists
        Product product = productService.findById(itemPutDTO.getProductId()); // Check if ID of product exists
        Item item = ItemMapper.INSTANCE.toItem(itemPutDTO);
        item.setUnitPrice(product.getPrice());
        return itemRepository.save(item);
    }

    /**
     * Delete an Item by ID in the database
     *
     * @param id ID of Item
     */
    public void delete(long id) {
        itemRepository.delete(findById(id));
    }
}
