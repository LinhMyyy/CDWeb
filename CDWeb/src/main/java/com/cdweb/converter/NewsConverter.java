package com.cdweb.converter;

import com.cdweb.dto.NewsDTO;
import com.cdweb.entity.NewsEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class NewsConverter {
    @Autowired
    private ModelMapper modelMapper;

    public NewsDTO toDTO(NewsEntity newsEntity) {
        return modelMapper.map(newsEntity, NewsDTO.class);
    }

    public NewsEntity toEntity(NewsDTO newsDTO) {
        return modelMapper.map(newsDTO, NewsEntity.class);
    }
}
