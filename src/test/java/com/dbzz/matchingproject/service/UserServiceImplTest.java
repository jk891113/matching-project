package com.dbzz.matchingproject.service;

import com.dbzz.matchingproject.dto.request.LoginRequestDto;
import com.dbzz.matchingproject.dto.request.SignupRequestDto;
import com.dbzz.matchingproject.entity.Point;
import com.dbzz.matchingproject.entity.User;
import com.dbzz.matchingproject.enums.UserRoleEnum;
import com.dbzz.matchingproject.jwt.AuthenticatedUserInfoDto;
import com.dbzz.matchingproject.jwt.JwtUtil;
import com.dbzz.matchingproject.repository.PointRepository;
import com.dbzz.matchingproject.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {
    @Mock
    private UserRepository userRepository;

    @Mock
    private PointRepository pointRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @Spy
    private BCryptPasswordEncoder passwordEncoder;

    @Test
    @DisplayName("회원 가입")
    void signup() {
        // Given
        SignupRequestDto requestDto = SignupRequestDto.builder()
                .userId("aaaa")
                .password("12345678")
                .build();

        when(userRepository.findByUserId(any(String.class)))
                .thenReturn(Optional.empty());

        // When
        userService.signup(requestDto);

        // Then
        verify(userRepository, times(1))
                .save(any(User.class));
        verify(pointRepository, times(1))
                .save(any(Point.class));
    }

    @Test
    void signin() {
        // Given
        LoginRequestDto requestDto = LoginRequestDto.builder()
                .userId("aaaa")
                .password("12345678")
                .build();

        User user = new User("aaaa", passwordEncoder.encode("12345678"), UserRoleEnum.SELLER);

        when(userRepository.findByUserId(any(String.class)))
                .thenReturn(Optional.of(user));
        // When
        AuthenticatedUserInfoDto userInfoDto = userService.signin(requestDto);

        // Then
        assertThat(userInfoDto.getUsername()).isEqualTo("aaaa");
        assertThat(userInfoDto.getRole()).isEqualTo(UserRoleEnum.SELLER);
    }
}