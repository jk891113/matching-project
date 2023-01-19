package com.dbzz.matchingproject.service;

import com.dbzz.matchingproject.dto.request.LoginRequestDto;
import com.dbzz.matchingproject.dto.request.SellerAuthRequestDto;
import com.dbzz.matchingproject.dto.request.SignupRequestDto;
import com.dbzz.matchingproject.dto.response.ProfileResponseDto;
import com.dbzz.matchingproject.entity.Form;
import com.dbzz.matchingproject.entity.Profile;
import com.dbzz.matchingproject.entity.User;
import com.dbzz.matchingproject.enums.UserRoleEnum;
import com.dbzz.matchingproject.jwt.AuthenticatedUserInfoDto;
import com.dbzz.matchingproject.repository.FormRepository;
import com.dbzz.matchingproject.repository.ProfileRepository;
import com.dbzz.matchingproject.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    private static final String ADMIN_TOKEN = "AAABnvxRVklrnYxKZ0aHgTBcXukeZygoC";
    private final FormRepository formRepository;

    @Override
    public void signup(SignupRequestDto requestDto) {
//        String userId = requestDto.getUserId();
//        String password = passwordEncoder.encode(requestDto.getPassword());

        String userId = requestDto.getUserId();
        String password = requestDto.getPassword();

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
        User user = new User(userId, password, role);
        userRepository.save(user);
    }

    @Override
    public AuthenticatedUserInfoDto signin(LoginRequestDto requestDto) {
        User user = userRepository.findByUserId(requestDto.getUserId()).orElseThrow(
                () -> new IllegalArgumentException("등록된 아이디가 없습니다.")
        );
        if (!user.getPassword().equals(requestDto.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }
//        if (!passwordEncoder.matches(requestDto.getPassword(), user.getPassword())) {
//            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
//        }
        return new AuthenticatedUserInfoDto(user.getRole(), user.getUserId());
    }

//    @Override
//    public List<ProfileResponseDto> getAllSellerList() {
////        List<User> sellerList = userRepository.findAllByRole(UserRoleEnum.SELLER);
//        List<String> userIdList = userRepository.findAllByRole(UserRoleEnum.SELLER).stream()
//                .map(User::getUserId)
//                .collect(Collectors.toList());
////        List<Profile> sellerProfileList = profileRepository.findAllByUserIdIn(userIdList);
//        List<ProfileResponseDto> responseDtos = profileRepository.findAllByUserIdIn(userIdList).stream()
//                .map(profile -> new ProfileResponseDto(profile.getUserId(), profile))
//                .collect(Collectors.toList());
//        return responseDtos;
//    }

    @Override
    public void signout() {

    }

    @Override
    public void sellerAuth(String userId, SellerAuthRequestDto requestDto) {
        User user = userRepository.findByUserId(userId).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 회원입니다.")
        );
        Form form = new Form(userId, requestDto.getIntro(), requestDto.getItem());
        formRepository.save(form);

    }
}
