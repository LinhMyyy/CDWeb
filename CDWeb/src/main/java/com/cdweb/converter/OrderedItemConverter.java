package com.cdweb.converter;

import com.cdweb.dto.OrderedItemDTO;
import com.cdweb.entity.OrderedItemEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderedItemConverter {
    @Autowired
    private ModelMapper modelMapper;

    public OrderedItemDTO toDTO(OrderedItemEntity orderedItemEntity) {
        return modelMapper.map(orderedItemEntity, OrderedItemDTO.class);
    }

    public OrderedItemEntity toEntity(OrderedItemDTO orderedItemDTO) {
        return modelMapper.map(orderedItemDTO, OrderedItemEntity.class);
    }
}
