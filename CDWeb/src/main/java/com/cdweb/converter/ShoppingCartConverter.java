package com.cdweb.converter;

import com.cdweb.dto.ShoppingCartDTO;
import com.cdweb.entity.ShoppingCartEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ShoppingCartConverter {
    @Autowired
    private ModelMapper modelMapper;

    public ShoppingCartDTO toDTO(ShoppingCartEntity shoppingCartEntity) {
        return modelMapper.map(shoppingCartEntity, ShoppingCartDTO.class);
    }

    public ShoppingCartEntity toEntity(ShoppingCartDTO shoppingCartDTO) {
        return modelMapper.map(shoppingCartDTO, ShoppingCartEntity.class);
    }
}
