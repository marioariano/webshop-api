package com.webshop.controller;

import com.webshop.domain.Category;
import com.webshop.domain.Product;
import com.webshop.domain.dto.ProductPostDTO;
import com.webshop.domain.dto.ProductPutDTO;
import com.webshop.service.ProductService;
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
 * Class to manage product routes
 *
 * @author Mario Ariano
 */
@RestController
@Log4j2
@RequiredArgsConstructor
@RequestMapping("products")
public class ProductController {

    private final ProductService productService;

    /**
     * Insert a new Product
     *
     * @param productPostDTO Body PostDTO of Product
     * @return Product
     */
    @ApiOperation(value = "Insert a new Product.")
    @PostMapping
    public ResponseEntity<Product> save(@RequestBody ProductPostDTO productPostDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.save(productPostDTO));
    }

    /**
     * Search for all Products
     *
     * @return List of Product
     */
    @ApiOperation(value = "Search for all products.")
    @GetMapping
    public ResponseEntity<List<Product>> list() {
        return ResponseEntity.ok(productService.listAll());
    }

    /**
     * Search a product by ID
     *
     * @param id Product ID
     * @return Product
     */
    @ApiOperation(value = "Search a product by ID.")
    @GetMapping(path = "/{id}")
    public ResponseEntity<Product> findById(@PathVariable long id) {
        return ResponseEntity.ok(productService.findById(id));
    }

    /**
     * Search a product by parameters
     *
     * @param id Product ID
     * @param name Product Name
     * @param description Product Description
     * @param price Price of Product
     * @param registerDate Register Date of Product
     * @param category Category ID of Product
     * @return List of Product
     */
    @ApiOperation(value = "Search a product by parameters.")
    @GetMapping(path = "/find")
    public ResponseEntity<List<Product>> findByParameter(
            @RequestParam(required = false) Long id,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String description,
            @RequestParam(required = false) Double price,
            @DateTimeFormat(pattern = "yyyy/MM/dd HH:mm")
            @Pattern(regexp = "([0-9]{4}[/][0-1][0-9][/][0-3][0-9][ ][0-2][0-9][:][0-5][0-9])")
            @RequestParam(required = false) LocalDateTime registerDate,
            @RequestParam(name = "categoryId", required = false) Category category) {

        List<Product> list = productService.findByParameter(id, name, description, price, registerDate, category);
        return ResponseEntity.ok(list);
    }

    /**
     * Search for all products using pagination
     * You can use "size", "page" e "sort" parameters
     *
     * @param pageable Pageable
     * @return Page of Product
     */
    @ApiOperation(value = "Search for all products using pagination." +
            "You can use \"size\", \"page\" e \"sort\" parameters")
    @GetMapping(path = "/page")
    public ResponseEntity<Page<Product>> listPage(Pageable pageable) {
        return ResponseEntity.ok(productService.listPage(pageable));
    }

    /**
     * Edit a product by ID
     *
     * @param productPutDTO Body PutDTO of Product
     * @return Product
     */
    @ApiOperation(value = "Edit a product by id.")
    @PutMapping
    public ResponseEntity<Product> updateById(@RequestBody ProductPutDTO productPutDTO) {
        return ResponseEntity.ok(productService.update(productPutDTO));
    }

    /**
     * Delete a product by ID
     *
     * @param id ID of Product
     * @return NULL
     */
    @ApiOperation(value = "Delete a product by ID.")
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable long id) {
        productService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
