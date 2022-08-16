package com.cdweb.service;

import com.cdweb.dto.OrderedDTO;

import java.util.List;

public interface IOrderedService {
    public OrderedDTO save(OrderedDTO orderedDTO);
    public List<OrderedDTO> findAllOrder(String email);

    List<OrderedDTO> findAll();
    public OrderedDTO findOrder(long id);
    public OrderedDTO edit(OrderedDTO orderedDTO);
}
