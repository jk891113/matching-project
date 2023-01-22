package com.dbzz.matchingproject.service;

import com.dbzz.matchingproject.dto.request.CustomerProfileRequestDto;
import com.dbzz.matchingproject.dto.request.ProfileRequestDto;
import com.dbzz.matchingproject.dto.request.SellerProfileRequestDto;
import com.dbzz.matchingproject.dto.response.CustomerProfileResponseDto;
import com.dbzz.matchingproject.dto.response.PermissionResponseDto;
import com.dbzz.matchingproject.dto.response.ProfileResponseDto;
import com.dbzz.matchingproject.entity.Form;
import com.dbzz.matchingproject.entity.Profile;
import com.dbzz.matchingproject.entity.User;
import com.dbzz.matchingproject.enums.UserRoleEnum;
import com.dbzz.matchingproject.repository.FormRepository;
import com.dbzz.matchingproject.repository.ProfileRepository;
import com.dbzz.matchingproject.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProfileServiceImpl implements ProfileService {
    private final UserRepository userRepository;
    private final ProfileRepository profileRepository;
    private final FormRepository formRepository;

    @Override
    public CustomerProfileResponseDto createCustomerProfile(String userId, CustomerProfileRequestDto requestDto) {
        // 입력한 아이디의 회원이 존재하는지 확인
        userRepository.findByUserId(userId).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 회원입니다.")
        );
        // 입력한 아이디의 회원의 프로필이 존재하는지 확인
        Optional<Profile> found = profileRepository.findByUserId(userId);
        if (found.isPresent()) throw new IllegalArgumentException("프로필이 존재합니다.");
        // Dto 의 image 값이 null 이면 이미지를 제외하고 객체 생성
        Profile profile;
        if (requestDto.getImage() == null) {
            profile = new Profile(userId, requestDto.getNickname());
        } else {
            profile = new Profile(userId, requestDto.getNickname(), requestDto.getImage());
        }
        profileRepository.save(profile);
        return new CustomerProfileResponseDto(userId, profile);
    }

    @Override
    public PermissionResponseDto createSellerProfile(String userId, SellerProfileRequestDto requestDto) {
        userRepository.findByUserId(userId).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 회원입니다.")
        );
        Optional<Profile> found = profileRepository.findByUserId(userId);
        if (found.isEmpty()) throw new IllegalArgumentException("고객 프로필부터 작성해 주세요.");
        Optional<Form> foundForm = formRepository.findByUserId(userId);
        if (foundForm.isPresent()) throw new IllegalArgumentException("판매자 요청은 한번만 가능합니다.");
        Form form = new Form(userId, requestDto.getIntro(), requestDto.getItem());
        formRepository.save(form);
        return new PermissionResponseDto(form);
    }

    @Override
    public CustomerProfileResponseDto getCustomerProfileByUserId(String userId) {
        // 입력한 아이디의 회원의 프로필이 존재하는지 확인
        Profile profile = profileRepository.findByUserId(userId).orElseThrow(
                () -> new IllegalArgumentException("프로필을 작성해 주세요.")
        );
        return new CustomerProfileResponseDto(userId, profile);
    }

    @Override
    public ProfileResponseDto getSellerProfileByUserId(String userId) {
        // 입력한 아이디의 회원이 존재하는지 확인
        User user = userRepository.findByUserId(userId).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 회원입니다.")
        );
        // 입력한 아이디의 회원이 판매자인지 확인
        if (user.getRole() != UserRoleEnum.SELLER)
            throw new IllegalArgumentException("해당 유저는 판매자가 아닙니다.");
        // 입력한 아이디의 회원의 프로필이 존재하는지 확인
        Profile profile = profileRepository.findByUserId(userId).orElseThrow(
                () -> new IllegalArgumentException("해당 유저의 프로필이 존재하지 않습니다.")
        );
        // 해당 판매자의 프로필을 반환
        return new ProfileResponseDto(userId, profile);
    }

    @Override
    @Transactional
    public ProfileResponseDto updateProfile(String userId, ProfileRequestDto requestDto) {
        // 입력한 아이디의 회원의 프로필이 존재하는지 확인
        Profile profile = profileRepository.findByUserId(userId).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 프로필입니다.")
        );
        // Dto 의 image 값이 null 이면 이미지를 제외하고 객체 생성
        if (requestDto.getImage() == null) {
            profile.updateWithoutImage(requestDto);
        } else {
            profile.updateWithImage(requestDto);
        }
        return new ProfileResponseDto(userId, profile);
    }
}
