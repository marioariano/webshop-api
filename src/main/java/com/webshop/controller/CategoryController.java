package com.webshop.controller;

import com.webshop.domain.Category;
import com.webshop.domain.dto.CategoryPostDTO;
import com.webshop.domain.dto.CategoryPutDTO;
import com.webshop.service.CategoryService;
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
 * Class to manage category routes
 *
 * @author Mario Ariano
 */
@RestController
@Log4j2
@RequiredArgsConstructor
@RequestMapping("categories")
public class CategoryController {

    private final CategoryService categoryService;

    /**
     * Insert a new Category
     *
     * @param categoryPostDTO Body PostDTO of Category
     * @return Category
     */
    @ApiOperation(value = "Insert a new Category.")
    @PostMapping
    public ResponseEntity<Category> save(@RequestBody CategoryPostDTO categoryPostDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(categoryService.save(categoryPostDTO));
    }

    /**
     * Search all categories
     *
     * @return List of Category
     */
    @ApiOperation(value = "Search all categories.")
    @GetMapping
    public ResponseEntity<List<Category>> list() {
        return ResponseEntity.ok(categoryService.listAll());
    }

    /**
     * Search a category by ID
     *
     * @param id ID of category
     * @return Category
     */
    @ApiOperation(value = "Search a category by id.")
    @GetMapping(path = "/{id}")
    public ResponseEntity<Category> findById(@PathVariable long id) {
        return ResponseEntity.ok(categoryService.findById(id));
    }

    /**
     * Search a category by parameters
     *
     * @param id ID of the category
     * @param name Name of the category
     * @return List of the Category
     */
    @ApiOperation(value = "Search a category by parameters.")
    @GetMapping(path = "/find")
    public ResponseEntity<List<Category>> findByParameter(
            @RequestParam(required = false, defaultValue = "0") Long id,
            @RequestParam(required = false) String name) {

        List<Category> list = categoryService.findByParameter(id, name);
        return ResponseEntity.ok(list);
    }

    /**
     * Search for all categories using pagination
     * You can use "size", "page" and "sort" parameters
     *
     * @param pageable Pageable
     * @return Page of Category
     */
    @ApiOperation(value = "Search for all categories using pagination" +
            "You can use \"size\", \"page\" and \"sort\" parameters")
    @GetMapping(path = "/page")
    public ResponseEntity<Page<Category>> listPage(Pageable pageable) {
        return ResponseEntity.ok(categoryService.listPage(pageable));
    }

    /**
     * Edit a category by id
     *
     * @param categoryPutDTO Body PutDTO of Category
     * @return List of the Category
     */
    @ApiOperation(value = "Edit a category by id.")
    @PutMapping
    public ResponseEntity<Category> updateById(@RequestBody CategoryPutDTO categoryPutDTO) {
        return ResponseEntity.ok(categoryService.update(categoryPutDTO));
    }

    /**
     * Delete a category by id
     *
     * @param id ID of the Category
     * @return NULL
     */
    @ApiOperation(value = "Delete a category by id.")
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable long id) {
        categoryService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
