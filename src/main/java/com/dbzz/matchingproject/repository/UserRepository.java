package com.dbzz.matchingproject.repository;

import com.dbzz.matchingproject.dto.response.ProfileResponseDto;
import com.dbzz.matchingproject.entity.User;
import com.dbzz.matchingproject.enums.UserRoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findByUserId(String userId);

    List<User> findAllByOrderByCreatedAtDesc();

    List<User> findAllByRole(UserRoleEnum roleEnum);


}
