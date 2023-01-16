package com.dbzz.matchingproject.service;

import com.dbzz.matchingproject.dto.request.LoginRequestDto;
import com.dbzz.matchingproject.dto.request.SignupRequestDto;
import com.dbzz.matchingproject.entity.User;
import com.dbzz.matchingproject.enums.UserRoleEnum;
import com.dbzz.matchingproject.jwt.AuthenticatedUserInfoDto;
import com.dbzz.matchingproject.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    private static final String ADMIN_TOKEN = "AAABnvxRVklrnYxKZ0aHgTBcXukeZygoC";
    @Override
    public void signup(SignupRequestDto requestDto) {
        Optional<User> found = userRepository.findByUserId(requestDto.getUserId());
        if (found.isPresent()) {
            throw new IllegalArgumentException("중복된 아이디가 존재합니다.");
        }
        UserRoleEnum role = UserRoleEnum.CUSTOMER;
        if (requestDto.getAdminToken() != null) {
            if (!requestDto.getAdminToken().equals(ADMIN_TOKEN)) {
                throw new IllegalArgumentException("관리자 암호가 일치하지 않아 등록할 수 없습니다.");
            }
            role = UserRoleEnum.ADMIN;
        }
        User user = new User(requestDto.getUserId(), requestDto.getPassword(), role);
        userRepository.save(user);
    }

    @Override
    public AuthenticatedUserInfoDto login(LoginRequestDto requestDto) {
        User user = userRepository.findByUserId(requestDto.getUserId()).orElseThrow(
                () -> new IllegalArgumentException("등록된 아이디가 없습니다.")
        );
        if (!user.getPassword().equals(requestDto.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }
        return new AuthenticatedUserInfoDto(user.getRole(), user.getUserId());
    }

    @Override
    public void logout() {

    }
}
