package com.dbzz.matchingproject.service;

import com.dbzz.matchingproject.dto.response.PermissionResponseDto;
import com.dbzz.matchingproject.dto.response.SellerListResponseDto;
import com.dbzz.matchingproject.dto.response.UserResponseDto;
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

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService{
    private final ProfileRepository profileRepository;
    private final UserRepository userRepository;
    private final FormRepository formRepository;

    @Override
    @Transactional(readOnly = true)
    public List<UserResponseDto> getAllCustomers() {
        List<User> userList = userRepository.findAllByOrderByCreatedAtDesc();
        if (userList.isEmpty()) throw new IllegalArgumentException("회원 목록이 없습니다.");
        List<UserResponseDto> customerList = new ArrayList<>();
            for(User user : userList){
                customerList.add(new UserResponseDto(user));
            }
            return customerList;
    }

    @Override
    @Transactional(readOnly = true)
    public List<SellerListResponseDto> getAllSellers() {
        List<User> userList = userRepository.findAllByRole(UserRoleEnum.SELLER);
        List<String> userIdList = new ArrayList<>();
        for(User user : userList){
            userIdList.add(user.getUserId());
        }
        List<Profile> profileList = profileRepository.findAllByUserIdIn(userIdList);
        if (profileList.isEmpty()) throw new IllegalArgumentException("판매자 목록이 없습니다.");
        List<SellerListResponseDto> sellerList = new ArrayList<>();
        for(Profile profile : profileList){
            sellerList.add(new SellerListResponseDto(profile));
        }
        return sellerList;
    }

    @Override
    @Transactional(readOnly = true)
    public List<PermissionResponseDto> getPermissionRequestForms() {
        List<Form> formList = formRepository.findAll();
        if (formList.isEmpty()) throw new IllegalArgumentException("판매자 권한 요청 목록이 없습니다.");
        List<PermissionResponseDto> permitList = new ArrayList<>();
            for(Form form : formList){
                permitList.add(new PermissionResponseDto(form));
            }
            return permitList;
    }

    @Override
    public void permitAuth(String userId) {
        Form form = formRepository.findByUserId(userId).get();
        User user = userRepository.findByUserId(form.getUserId()).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 회원입니다.")
        );
        user.changeRole(UserRoleEnum.SELLER);
        userRepository.save(user);
        formRepository.delete(form);
    }

    @Override
    public void removeAuth(String userId) {
        User user = userRepository.findByUserId(userId).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 회원입니다.")
        );
        if (user.getRole() != UserRoleEnum.SELLER)
            throw new IllegalArgumentException("해당 유저는 판매자가 아닙니다.");
        user.changeRole(UserRoleEnum.CUSTOMER);
        userRepository.save(user);
    }
}
