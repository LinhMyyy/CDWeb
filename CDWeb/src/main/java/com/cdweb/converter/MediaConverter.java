package com.cdweb.converter;

import com.cdweb.dto.MediaDTO;
import com.cdweb.entity.MediaEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MediaConverter {
    @Autowired
    private ModelMapper modelMapper;

    public MediaDTO toDTO(MediaEntity mediaEntity) {
        return modelMapper.map(mediaEntity, MediaDTO.class);
    }

    public MediaEntity toEntity(MediaDTO mediaDTO) {
        return modelMapper.map(mediaDTO, MediaEntity.class);
    }

}
