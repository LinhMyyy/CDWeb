package com.cdweb.repository;

import com.cdweb.entity.ProductEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
    ProductEntity findOneById(Long id);

    Page<ProductEntity> findAll(Pageable pageable);

    Page<ProductEntity> findAllByActive(boolean active, Pageable pageable);

    @Query(value = "select b from ProductEntity b where b.active = ?1 and b.hotProduct = ?2")
    Page<ProductEntity> findAllByActiveAndHotProduct(boolean active, boolean hotProduct, Pageable pageable);

    Page<ProductEntity> findAllByActiveAndNewProduct(boolean active, boolean newProduct, Pageable pageable);

    @Query("select b from ProductEntity b where b.active = ?1 and b.category.code = ?2")
    Page<ProductEntity> findAllByActiveAndCategoryCode(boolean active, String category, Pageable pageable);


    public Page<ProductEntity> findByActiveAndDiscountGreaterThan(boolean active, double discount, Pageable pageable);


    @Query("select count(b) from ProductEntity b where b.active = ?1 and b.category.code = ?2")
    public int countAllByActiveAndCategoryCode(boolean active, String category_code);

    public int countAllByActiveAndHotProduct(boolean active, boolean hot_book);


    public int countAllByActiveAndNewProduct(boolean active, boolean new_book);

    public int countAllByActive(boolean active);

    public int countAllByActiveAndDiscountGreaterThan(boolean active, double discount);

    public Page<ProductEntity> findByActiveAndTitleContains(boolean b, String title, Pageable pageable);

    public int countAllByActiveAndTitleContains(boolean active, String title);

    public List<ProductEntity> findByActiveAndTitleContains(boolean active, String title);

}
