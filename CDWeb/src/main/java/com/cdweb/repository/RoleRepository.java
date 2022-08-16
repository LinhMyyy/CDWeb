package com.cdweb.repository;

import com.cdweb.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, Long> {

    @Query(value = "select role.name from role join user_role on role.id=user_role.role_id where user_role.user_id=:userId", nativeQuery = true)
    List<String> getRoleNames(@Param("userId") Long userId);

    RoleEntity findByName(String name);
}
