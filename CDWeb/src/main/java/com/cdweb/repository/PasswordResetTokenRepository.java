package com.cdweb.repository;

import com.cdweb.entity.PasswordResetToken;
import com.cdweb.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PasswordResetTokenRepository extends CrudRepository<PasswordResetToken, String> {
    PasswordResetToken findByToken(String token);

    List<PasswordResetToken> findByUser(UserEntity user);
}
