package com.cdweb.service;

import com.cdweb.dto.CategoryDTO;

import java.util.List;

public interface ICategoryService {
    List<CategoryDTO> findAll();

    CategoryDTO save(CategoryDTO categoryDTO);

    CategoryDTO findCategory(String category);

    void delete(long id);

    CategoryDTO edit(CategoryDTO category);

    CategoryDTO findById(long id);
}
