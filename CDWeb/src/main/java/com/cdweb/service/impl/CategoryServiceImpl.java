package com.cdweb.service.impl;

import com.cdweb.converter.CategoryConverter;
import com.cdweb.dto.CategoryDTO;
import com.cdweb.entity.CategoryEntity;
import com.cdweb.repository.CategoryRepository;
import com.cdweb.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryServiceImpl implements ICategoryService {
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private CategoryConverter categoryConverter;

    @Override
    public List<CategoryDTO> findAll() {
        List<CategoryDTO> categoryList = new ArrayList<>();
        List<CategoryEntity> list = categoryRepository.findAll();
        for (CategoryEntity category : list) {
            categoryList.add(this.categoryConverter.toDTO(category));
        }
        return categoryList;
    }

    @Override
    public CategoryDTO save(CategoryDTO categoryDTO) {
        return this.categoryConverter.toDTO(categoryRepository.save(this.categoryConverter.toEntity(categoryDTO)));
    }

    @Override
    public CategoryDTO findCategory(String category) {
        return this.categoryConverter.toDTO(categoryRepository.findByCode(category));
    }

    @Override
    public void delete(long id) {
        this.categoryRepository.deleteById(id);
    }

    @Override
    public CategoryDTO edit(CategoryDTO category) {
        CategoryEntity categoryEntity = this.categoryRepository.findCategoryById(category.getId());
        categoryEntity.setCode(category.getCode());
        categoryEntity.setName(category.getName());
        return this.categoryConverter.toDTO(this.categoryRepository.save(categoryEntity));
    }

    @Override
    public CategoryDTO findById(long id) {

        CategoryEntity categoryEntity = this.categoryRepository.findOneById(id);
        return this.categoryConverter.toDTO(categoryEntity);
    }


}
