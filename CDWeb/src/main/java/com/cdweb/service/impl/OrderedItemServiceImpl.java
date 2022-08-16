package com.cdweb.service.impl;

import com.cdweb.converter.OrderedItemConverter;
import com.cdweb.dto.OrderedItemDTO;
import com.cdweb.entity.OrderedItemEntity;
import com.cdweb.repository.OrderedItemRepository;
import com.cdweb.service.IOrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderedItemServiceImpl implements IOrderItemService {
    @Autowired
    private OrderedItemRepository orderedItemRepository;
    @Autowired
    private OrderedItemConverter orderedItemConverter;


    @Override
    public OrderedItemDTO save(OrderedItemDTO orderedItem) {
        OrderedItemEntity entity = this.orderedItemRepository.save(this.orderedItemConverter.toEntity(orderedItem));
        return this.orderedItemConverter.toDTO(entity);
    }
}
