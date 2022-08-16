package com.cdweb.repository;

import com.cdweb.entity.OrderedItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderedItemRepository extends JpaRepository<OrderedItemEntity, Long> {
    @Query(value = "select item.* from ordered_item item join ordered on item.ordered_id=ordered.id where item.book_id=:book_id and ordered.user_id=:user_id",nativeQuery = true)
    public OrderedItemEntity findItem(@Param("book_id") long book_id,@Param("user_id")long user_id);
}
