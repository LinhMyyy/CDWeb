package com.cdweb.service;

import com.cdweb.dto.OrderedItemDTO;

import java.security.Principal;

public interface IOrderItemService {
    public OrderedItemDTO save(OrderedItemDTO orderedItem);
}
