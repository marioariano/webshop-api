package com.webshop.repository;

import com.webshop.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Class Repository of Category
 *
 * @author Mario Ariano
 */
@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    /**
     * Search categories by parameters
     *
     * @param id ID of Category
     * @param name Name of Category
     * @return List of Category
     */
    List<Category> findByIdOrName(Long id, String name);

}
