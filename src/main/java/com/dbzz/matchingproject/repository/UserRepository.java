package com.dbzz.matchingproject.repository;

import com.dbzz.matchingproject.entity.User;
import com.dbzz.matchingproject.enums.UserRoleEnum;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findByUserId(String userId);

    List<User> findAllByOrderByCreatedAtDesc(Pageable pageable);

    List<User> findAllByRole(UserRoleEnum roleEnum, Pageable pageable);


}
