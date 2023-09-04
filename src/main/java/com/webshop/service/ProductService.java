package com.webshop.service;

import com.webshop.domain.Category;
import com.webshop.domain.Product;
import com.webshop.domain.dto.ProductPostDTO;
import com.webshop.domain.dto.ProductPutDTO;
import com.webshop.mapper.ProductMapper;
import com.webshop.repository.CategoryRepository;
import com.webshop.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * Class Service of Product
 *
 * @author Mario Ariano
 */
@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    /**
     * Insert a category in the database
     *
     * @param productPostDTO OrderPostDTO
     * @return Order
     */
    public Product save(ProductPostDTO productPostDTO) {
        //Category category = categoryRepository.
        Product product = ProductMapper.INSTANCE.toProduct(productPostDTO);
        return productRepository.save(product);
    }

    /**
     * List all products
     *
     * @return List of Product
     */
    public List<Product> listAll() {
        return productRepository.findAll();
    }

    /**
     * Search a Product by ID
     *
     * @param id ID of Product
     * @return Product
     */
    public Product findById(long id) {
        Optional<Product> optional = productRepository.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        }
        throw new RuntimeException();
    }

    /**
     * Search products by parameters
     *
     * @param id ID of Product
     * @param fullName Full Name of Product
     * @param description Description of Product
     * @param price Price of Product
     * @param registerDate Register Date of Product
     * @param category ID of Category of Product
     * @return List of Product
     */
    public List<Product> findByParameter(Long id, String name, String description, Double price, LocalDateTime registerDate, Category category) {
        return productRepository.findByIdOrNameOrDescriptionOrPriceOrRegisterDateOrCategory(id, name, description, price, registerDate, category);
    }

    /**
     * Search all products with pagination
     *
     * @param pageable Pageable
     * @return Page of Product
     */
    public Page<Product> listPage(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    /**
     * Edit a product by id in the database
     *
     * @param productPutDTO Body PutDTO of Product
     * @return Product
     */
    public Product update(ProductPutDTO productPutDTO) {
        Product savedProduct = findById(productPutDTO.getId());
        Product product = ProductMapper.INSTANCE.toProduct(productPutDTO);
        product.setId(savedProduct.getId()); // Double check if ID is correct
        product.setRegisterDate(savedProduct.getRegisterDate()); // Fill registerDate
        return productRepository.save(product);
    }

    /**
     * Delete a product by ID in the database
     *
     * @param id ID of Product
     */
    public void delete(long id) {
        productRepository.delete(findById(id));
    }
}
