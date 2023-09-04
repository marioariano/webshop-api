package com.webshop.repository;

import com.webshop.domain.Category;
import com.webshop.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Class Repository of Product
 *
 * @author Mario Ariano
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    /**
     * Search products by parameters
     *
     * @param id ID of Product
     * @param name Name of Product
     * @param description Description of Product
     * @param price Price of Product
     * @param registerDate Register Date of Product
     * @param category Category ID from Product
     * @return List of Product
     */
    List<Product> findByIdOrNameOrDescriptionOrPriceOrRegisterDateOrCategory(
            Long id,
            String name,
            String description,
            Double price,
            LocalDateTime registerDate,
            Category category);
}
