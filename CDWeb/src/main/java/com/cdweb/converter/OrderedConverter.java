package com.cdweb.converter;

import com.cdweb.dto.OrderedDTO;
import com.cdweb.entity.OrderedEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderedConverter {
    @Autowired
    private ModelMapper modelMapper;

    public OrderedDTO toDTO(OrderedEntity orderedEntity) {
        return modelMapper.map(orderedEntity, OrderedDTO.class);
    }

    public OrderedEntity toEntity(OrderedDTO orderedDTO) {
        return modelMapper.map(orderedDTO, OrderedEntity.class);
    }
}
