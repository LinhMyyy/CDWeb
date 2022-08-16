package com.cdweb.service.impl;

import com.cdweb.converter.ProductConverter;
import com.cdweb.dto.ProductDTO;
import com.cdweb.entity.ProductEntity;
import com.cdweb.entity.CategoryEntity;
import com.cdweb.entity.MediaEntity;
import com.cdweb.repository.ProductRepository;
import com.cdweb.repository.CategoryRepository;
import com.cdweb.repository.MediaRepository;
import com.cdweb.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements IProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ProductConverter productConverter;
    @Autowired
    private MediaRepository mediaRepository;

    @Override
    public List<ProductDTO> findAll() {
        Sort sort = null;
        sort = Sort.by("id").descending();
        List<ProductDTO> results = new ArrayList<>();
        List<ProductEntity> list = productRepository.findAll(sort);

        for (ProductEntity book : list) {
            results.add(this.productConverter.toDTO(book));
        }
        return results;
    }

    @Override
    public ProductDTO save(ProductDTO productDTO) {
        ProductEntity productEntity = new ProductEntity();


        if (productDTO.getId() != 0) {
            ProductEntity oldProductEntity = productRepository.findOneById(productDTO.getId());
            productEntity = productConverter.toEntity(productDTO, oldProductEntity);
        } else {
            productEntity = productConverter.toEntity(productDTO);
        }
//        if (productDTO.getAuthor() != null) {
//            AuthorEntity author = this.authorRepository.findAuthorById(productDTO.getAuthor().getId());
//            productEntity.setAuthor(author);
//        }
        CategoryEntity category = this.categoryRepository.findCategoryById(productDTO.getCategory().getId());
        productEntity.setCategory(category);
        productEntity = productRepository.save(productEntity);
        MediaEntity mediaEntity = new MediaEntity();
        mediaEntity.setPath(productEntity.getMediaList().get(0).getPath());
        mediaEntity.setBook(productEntity);
        mediaRepository.save(mediaEntity);
        return productConverter.toDTO(this.productRepository.findOneById(productEntity.getId()));
    }


    @Override
    public void delete(long[] ids) {

        for (long item : ids) {
            productRepository.deleteById(item);
        }
    }


    @Override
    public List<ProductDTO> findAll(Pageable pageable) {
        List<ProductDTO> bookList = new ArrayList<>();
        List<ProductEntity> productEntityList = this.productRepository.findAllByActive(true, pageable).getContent();
        for (ProductEntity book : productEntityList) {
            bookList.add(this.productConverter.toDTO(book));
        }
        return bookList;
    }

    @Override
    public List<ProductDTO> findByCategory(String category_code, Pageable pageable) {
        List<ProductEntity> booklist = productRepository.findAllByActiveAndCategoryCode(true, category_code, pageable).getContent();
        List<ProductDTO> bookResult = new ArrayList<>();
        for (ProductEntity book : booklist) {
            bookResult.add(this.productConverter.toDTO(book));
        }
        return bookResult;
    }

    @Override
    public int countByCategory(String category_code) {
        if ("null".equalsIgnoreCase(category_code)) {
            return this.productRepository.countAllByActive(true);
        } else {
            return this.productRepository.countAllByActiveAndCategoryCode(true, category_code);
        }
    }

    @Override
    public ProductDTO findById(Long id) {
        return this.productConverter.toDTO(this.productRepository.findOneById(id));
    }

    @Override
    public List<ProductDTO> findByHot(Pageable pageable) {
        List<ProductDTO> bookList = new ArrayList<>();
        List<ProductEntity> productEntityList = this.productRepository.findAllByActiveAndHotProduct(true, true, pageable).getContent();
        for (ProductEntity book : productEntityList) {
            bookList.add(this.productConverter.toDTO(book));
        }
        return bookList;
    }

    @Override
    public List<ProductDTO> findByNew(Pageable pageable) {
        List<ProductDTO> bookList = new ArrayList<>();
        List<ProductEntity> productEntityList = this.productRepository.findAllByActiveAndNewProduct(true, true, pageable).getContent();
        for (ProductEntity book : productEntityList) {
            bookList.add(this.productConverter.toDTO(book));
        }
        return bookList;
    }

    @Override
    public List<ProductDTO> findByDiscount(Pageable pageable) {
        List<ProductDTO> bookList = new ArrayList<>();
        List<ProductEntity> productEntityList = this.productRepository.findByActiveAndDiscountGreaterThan(true, 0, pageable).getContent();
        for (ProductEntity book : productEntityList) {
            bookList.add(this.productConverter.toDTO(book));
        }
        return bookList;
    }

    @Override
    public int countByHot() {
        return this.productRepository.countAllByActiveAndHotProduct(true, true);
    }

    @Override
    public int countByNew() {
        return this.productRepository.countAllByActiveAndNewProduct(true, true);
    }

    @Override
    public int countAll() {
        return this.productRepository.countAllByActive(true);
    }

    @Override
    public int countByDiscount() {
        return this.productRepository.countAllByActiveAndDiscountGreaterThan(true, 0);
    }

    @Override
    public List<ProductDTO> findByTitle(String title, Pageable pageable) {
        List<ProductDTO> bookList = new ArrayList<>();
        List<ProductEntity> productEntityList = this.productRepository.findByActiveAndTitleContains(true, title, pageable).getContent();
        for (ProductEntity book : productEntityList) {
            bookList.add(this.productConverter.toDTO(book));
        }
        return bookList;
    }

    @Override
    public int countByTitle(String title) {
        return this.productRepository.countAllByActiveAndTitleContains(true, title);
    }

    @Override
    public List<String> autoCompleteTitle(String title) {
        List<ProductEntity> productEntityList = this.productRepository.findByActiveAndTitleContains(true, title);
        List<String> list = new ArrayList<>();
        for (ProductEntity book : productEntityList) {
            list.add(book.getTitle());
        }
        return list;
    }
}
