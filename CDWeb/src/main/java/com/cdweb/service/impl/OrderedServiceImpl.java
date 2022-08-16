package com.cdweb.service.impl;

import com.cdweb.converter.OrderedConverter;
import com.cdweb.dto.OrderedDTO;
import com.cdweb.entity.OrderedEntity;
import com.cdweb.entity.UserEntity;
import com.cdweb.repository.OrderedRepository;
import com.cdweb.repository.UserRepository;
import com.cdweb.service.IOrderedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderedServiceImpl implements IOrderedService {
    @Autowired
    private OrderedConverter orderedConverter;
    @Autowired
    private OrderedRepository orderedRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public OrderedDTO save(OrderedDTO orderedDTO) {
        OrderedEntity entity = this.orderedConverter.toEntity(orderedDTO);
        entity = this.orderedRepository.save(entity);
        return this.orderedConverter.toDTO(entity);
    }

    @Override
    public List<OrderedDTO> findAllOrder(String email) {
        UserEntity user = this.userRepository.findByEmailIgnoreCaseAndIsEnabled(email, true);
        List<OrderedEntity> orderedList = this.orderedRepository.findAllByUser(user);
        List<OrderedDTO> listResult = new ArrayList<>();
        for (OrderedEntity entity : orderedList) {
            listResult.add(this.orderedConverter.toDTO(entity));
        }
        return listResult;
    }

    @Override
    public List<OrderedDTO> findAll() {
        List<OrderedEntity> entityList = orderedRepository.findAll();
        List<OrderedDTO> results = new ArrayList<>();
        for (OrderedEntity entity : entityList) {
            results.add(this.orderedConverter.toDTO(entity));
        }
        return results;
    }

    @Override
    public OrderedDTO findOrder(long id) {
        OrderedEntity entity = this.orderedRepository.getOne(id);
        return this.orderedConverter.toDTO(entity);
    }

    @Override
    public OrderedDTO edit(OrderedDTO orderedDTO) {
        OrderedEntity orderedEntity = this.orderedRepository.findId(orderedDTO.getId());
        orderedEntity.setStatus(orderedDTO.getStatus());
        return this.orderedConverter.toDTO(this.orderedRepository.save(orderedEntity));
    }


}
