package com.cdweb.service;

import com.cdweb.dto.ProductDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IProductService {

    List<ProductDTO> findAll();

    ProductDTO save(ProductDTO productDTO);

    void delete(long[] ids);

    public List<ProductDTO> findAll(Pageable pageable);

    public List<ProductDTO> findByCategory(String category_code, Pageable pageable);


    public ProductDTO findById(Long id);

    public List<ProductDTO> findByHot(Pageable pageable);

    public List<ProductDTO> findByNew(Pageable pageable);

    public List<ProductDTO> findByDiscount(Pageable pageable);


    public int countByCategory(String category);

    public int countByHot();

    public int countByNew();

    public int countAll();

    public int countByDiscount();

    List<ProductDTO> findByTitle(String title, Pageable pageable);

    int countByTitle(String title);

    List<String> autoCompleteTitle(String title);
}
