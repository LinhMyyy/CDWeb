package com.cdweb.converter;

import com.cdweb.dto.ProductDTO;
import com.cdweb.entity.ProductEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductConverter {

    @Autowired
    private ModelMapper modelMapper;

    public ProductDTO toDTO(ProductEntity productEntity) {
        return modelMapper.map(productEntity, ProductDTO.class);
    }

    public ProductEntity toEntity(ProductDTO productDTO) {
        return modelMapper.map(productDTO, ProductEntity.class);
    }

    public ProductEntity toEntity(ProductDTO productDTO, ProductEntity productEntity) {
        productEntity.setId(productDTO.getId());
        productEntity.setTitle(productDTO.getTitle());
        productEntity.setActive(productDTO.isActive());
        productEntity.setDescription(productDTO.getDescription());
        productEntity.setPrice(productDTO.getPrice());
        productEntity.setPublisher(productDTO.getPublisher());
        productEntity.setQuantity(productDTO.getQuantity());
        productEntity.setMediaList(productEntity.getMediaList());
        return productEntity;
    }
}
