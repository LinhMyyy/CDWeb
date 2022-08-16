package com.cdweb.converter;

import com.cdweb.dto.CommentDTO;
import com.cdweb.entity.CommentEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CommentConverter {
    @Autowired
    private ModelMapper modelMapper;

    public CommentDTO toDTO(CommentEntity commentEntity) {
        return modelMapper.map(commentEntity, CommentDTO.class);
    }

    public CommentEntity toEntity(CommentDTO commentDTO) {
        return modelMapper.map(commentDTO, CommentEntity.class);
    }
}
