package com.dbzz.matchingproject.repository;

import com.dbzz.matchingproject.entity.Form;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FormRepository extends JpaRepository<Form, String> {
    Optional<Form> findByUserId(String userId);

}
