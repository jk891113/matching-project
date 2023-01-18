package com.dbzz.matchingproject.service;

import com.dbzz.matchingproject.dto.request.PermissionRequestDto;
import com.dbzz.matchingproject.dto.response.PermissionResponseDto;
import com.dbzz.matchingproject.dto.response.SellerListResponseDto;
import com.dbzz.matchingproject.dto.response.UserResponseDto;
import com.dbzz.matchingproject.entity.Profile;
import com.dbzz.matchingproject.entity.User;
import com.dbzz.matchingproject.enums.UserRoleEnum;
import com.dbzz.matchingproject.repository.ProfileRepository;
import com.dbzz.matchingproject.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService{
    private final ProfileRepository profileRepository;
    private final UserRepository userRepository;

    @Override
    @Transactional(readOnly = true)
    public List<UserResponseDto> getAllCustomers() {
        List<User> userList = userRepository.findAllByOrderByCreatedAtDesc();
        List<UserResponseDto> customerList = new ArrayList<>();
        for(User user : userList){
            customerList.add(new UserResponseDto(user));
        }
        return customerList;
    }

    @Override
    @Transactional(readOnly = true)
    public List<SellerListResponseDto> getAllSellers() {
        List<Profile> profileList = profileRepository.findAll();
        List<SellerListResponseDto> sellerList = new ArrayList<>();
        for(Profile profile : profileList){
            sellerList.add(new SellerListResponseDto(profile));
        }
        return sellerList;
    }

    @Override
    @Transactional(readOnly = true)
    public List<PermissionResponseDto> getPermissionRequestForms() {
        List<User> userList = userRepository.findAllByOrderByCreatedAtDesc();


        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public PermissionResponseDto permitAuth(String userId, PermissionRequestDto requestDto) {
        User user = userRepository.findByUserId(userId).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 회원입니다.")
        );
        user.update(requestDto);
        return new PermissionResponseDto(userId, user);
    }

    @Override
    @Transactional(readOnly = true)
    public PermissionResponseDto removeAuth(String userId, PermissionRequestDto requestDto) {
        User user = userRepository.findByUserId(userId).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 회원입니다.")
        );
        if (user.getRole() != UserRoleEnum.SELLER)
            throw new IllegalArgumentException("해당 유저는 판매자가 아닙니다.");
        user.update(requestDto);
        return new PermissionResponseDto(userId, user);

    }
}
