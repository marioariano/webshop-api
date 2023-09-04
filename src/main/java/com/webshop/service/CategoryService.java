package com.webshop.service;

import com.webshop.domain.Category;
import com.webshop.domain.dto.CategoryPostDTO;
import com.webshop.domain.dto.CategoryPutDTO;
import com.webshop.mapper.CategoryMapper;
import com.webshop.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

/**
 * Class Service of Category
 *
 * @author Mario Ariano
 */
@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    /**
     * Insert a new Category into the database
     *
     * @param categoryPostDTO CategoryPostDTO
     * @return Category
     */
    @Transactional
    public Category save(CategoryPostDTO categoryPostDTO) {
        Category category = CategoryMapper.INSTANCE.toCategory(categoryPostDTO);
        return categoryRepository.save(category);
    }

    /**
     * List all categories
     *
     * @return List of Product
     */
    public List<Category> listAll() {
        return categoryRepository.findAll();
    }

    /**
     * Search a Product by ID
     *
     * @param id ID of Product
     * @return Product
     */
    public Category findById(long id) {
        Optional<Category> optional = categoryRepository.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        }
        throw new RuntimeException();
    }

    /**
     * Search categories by parameters
     *
     * @param id ID of Category
     * @param name Name of Category
     * @return List of Category
     */
    public List<Category> findByParameter(Long id, String name) {
        return categoryRepository.findByIdOrName(id, name);
    }

    /**
     * Search all categories with pagination
     *
     * @param pageable Pageable
     * @return Page of Category
     */
    public Page<Category> listPage(Pageable pageable) {
        return categoryRepository.findAll(pageable);
    }

    /**
     * Edit a category by id in the database
     *
     * @param categoryPutDTO Body PutDTO of Category
     * @return List of Category
     */
    public Category update(CategoryPutDTO categoryPutDTO) {
        Category savedCategory = findById(categoryPutDTO.getId());
        Category category = CategoryMapper.INSTANCE.toCategory(categoryPutDTO);
        category.setId(savedCategory.getId()); // Double check for the ID
        return categoryRepository.save(category);
    }

    /**
     * Delete category by ID in the database
     *
     * @param id ID of Category
     */
    public void delete(long id) {
        categoryRepository.delete(findById(id));
    }
}
