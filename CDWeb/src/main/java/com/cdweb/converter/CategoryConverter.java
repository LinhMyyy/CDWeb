package com.cdweb.converter;

import com.cdweb.dto.CategoryDTO;
import com.cdweb.entity.CategoryEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CategoryConverter {
    @Autowired
    private ModelMapper modelMapper;
    public CategoryDTO toDTO(CategoryEntity categoryEntity){
        return modelMapper.map(categoryEntity,CategoryDTO.class);
    }
    public CategoryEntity toEntity(CategoryDTO categoryDTO){
        return modelMapper.map(categoryDTO, CategoryEntity.class);
    }
}
