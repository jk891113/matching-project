package com.dbzz.matchingproject.repository;

import com.dbzz.matchingproject.entity.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepository extends JpaRepository<Profile, String> {

}
