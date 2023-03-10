package com.dbzz.matchingproject.repository;

import com.dbzz.matchingproject.entity.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProfileRepository extends JpaRepository<Profile, String> {
    Optional<Profile> findByUserId(String userId);
    List<Profile> findAllByUserIdIn(List<String> userIdList);
}
