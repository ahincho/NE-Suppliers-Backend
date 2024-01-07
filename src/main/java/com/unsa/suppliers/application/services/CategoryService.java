package com.unsa.suppliers.application.services;

import static com.unsa.suppliers.application.services.StateService.ACTIVE_STATE;

import com.unsa.suppliers.domain.entities.CategoryEntity;
import com.unsa.suppliers.domain.entities.StateEntity;
import com.unsa.suppliers.domain.exceptions.categories.CategoryDuplicatedException;
import com.unsa.suppliers.domain.exceptions.categories.CategoryNotFoundException;
import com.unsa.suppliers.domain.exceptions.states.StateNotFoundException;
import com.unsa.suppliers.domain.repositories.CategoryRepository;
import com.unsa.suppliers.domain.repositories.StateRepository;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;
import java.util.List;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final StateRepository stateRepository;
    public CategoryService(CategoryRepository categoryRepository, StateRepository stateRepository) {
        this.categoryRepository = categoryRepository;
        this.stateRepository = stateRepository;
    }
    public List<CategoryEntity> getAllCategories() {
        return categoryRepository.findAll();
    }
    public CategoryEntity getCategoryById(Integer id) throws CategoryNotFoundException {
        return categoryRepository.findById(id).orElseThrow(CategoryNotFoundException::new);
    }
    @Transactional
    public CategoryEntity createCategory(CategoryEntity categoryEntity) throws CategoryDuplicatedException, StateNotFoundException {
        if (categoryRepository.existsByName(categoryEntity.getName())) { throw new CategoryDuplicatedException(); }
        StateEntity stateEntity = stateRepository.findByName(ACTIVE_STATE).orElseThrow(StateNotFoundException::new);
        categoryEntity.setState(stateEntity);
        return categoryRepository.save(categoryEntity);
    }
    @Transactional
    public void updateCategory(Integer id, CategoryEntity categoryEntity) throws CategoryNotFoundException, CategoryDuplicatedException {
        CategoryEntity optionalCategory = categoryRepository.findById(id).orElseThrow(CategoryNotFoundException::new);
        if (!optionalCategory.getName().equals(categoryEntity.getName()) && categoryRepository.existsByName(categoryEntity.getName())) {
            throw new CategoryDuplicatedException();
        }
        categoryEntity.setId(id);
        categoryEntity.setState(optionalCategory.getState());
        categoryRepository.save(categoryEntity);
    }
    @Transactional
    public void changeCategoryState(Integer id, String state) throws CategoryNotFoundException, StateNotFoundException {
        CategoryEntity categoryEntity = categoryRepository.findById(id).orElseThrow(CategoryNotFoundException::new);
        StateEntity stateEntity = stateRepository.findByName(state).orElseThrow(StateNotFoundException::new);
        categoryEntity.setState(stateEntity);
        categoryRepository.save(categoryEntity);
    }
}
