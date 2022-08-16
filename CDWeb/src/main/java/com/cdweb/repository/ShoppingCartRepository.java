package com.cdweb.repository;

import com.cdweb.entity.ShoppingCartEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShoppingCartRepository extends JpaRepository<ShoppingCartEntity,Long> {
    @Query(value = "select s.* from shopping_cart s where s.product_id=:book_id and user_id=:user_id",nativeQuery = true)
    public ShoppingCartEntity findCart(@Param("book_id") long book_id,@Param("user_id") long user_id);

    @Query(value = "select count(s.id) from shopping_cart s where user_id=:user_id ",nativeQuery = true)
    public int countBook(@Param("user_id")long user_id);

    @Query(value = "select s.* from shopping_cart s where user_id=:user_id ORDER BY s.id desc",nativeQuery = true)
    public List<ShoppingCartEntity> findAllByUser(@Param("user_id") long user_id);
}
