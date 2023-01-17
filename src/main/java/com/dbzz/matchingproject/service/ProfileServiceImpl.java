package com.dbzz.matchingproject.service;

import com.dbzz.matchingproject.dto.request.CustomerProfileRequestDto;
import com.dbzz.matchingproject.entity.Profile;
import com.dbzz.matchingproject.entity.User;
import com.dbzz.matchingproject.repository.ProfileRepository;
import com.dbzz.matchingproject.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProfileServiceImpl implements ProfileService {
    private final UserRepository userRepository;
    private final ProfileRepository profileRepository;

    @Override
    public void createCustomerProfile(String userId, CustomerProfileRequestDto requestDto) {
        userRepository.findByUserId(userId).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 회원입니다.")
        );
        Profile profile = new Profile(userId, requestDto.getNickname(), requestDto.getImage());
        profileRepository.save(profile);
    }

    @Override
    public void getProfileByUserId() {

    }

    @Override
    public void getSellerProfileByUserId() {

    }

    @Override
    public void updateProfile() {

    }
}
