package com.dbzz.matchingproject;

import com.dbzz.matchingproject.entity.Profile;
import com.dbzz.matchingproject.entity.User;
import com.dbzz.matchingproject.enums.UserRoleEnum;
import com.dbzz.matchingproject.repository.ProfileRepository;
import com.dbzz.matchingproject.repository.UserRepository;
import com.dbzz.matchingproject.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableJpaAuditing
@SpringBootApplication
public class MatchingProjectApplication {
    public static void main(String[] args) {
        SpringApplication.run(MatchingProjectApplication.class, args);
    }

    @Bean
    public CommandLineRunner test(UserRepository userRepository,
                                  ProfileRepository profileRepository,
                                  UserService userService) {

        return (args) -> {
            userRepository.save(new User("customer1", "1234?5678", UserRoleEnum.CUSTOMER));
            userRepository.save(new User("customer2", "1234?5678", UserRoleEnum.CUSTOMER));
            userRepository.save(new User("customer3", "1234?5678", UserRoleEnum.CUSTOMER));
            userRepository.save(new User("customer4", "1234?5678", UserRoleEnum.CUSTOMER));
            userRepository.save(new User("customer5", "1234?5678", UserRoleEnum.CUSTOMER));
            userRepository.save(new User("seller1", "1234?5678", UserRoleEnum.SELLER));
            userRepository.save(new User("seller2", "1234?5678", UserRoleEnum.SELLER));
            userRepository.save(new User("seller3", "1234?5678", UserRoleEnum.SELLER));
            userRepository.save(new User("seller4", "1234?5678", UserRoleEnum.SELLER));
            userRepository.save(new User("seller5", "1234?5678", UserRoleEnum.SELLER));
            userRepository.save(new User("admin", "1234?5678", UserRoleEnum.ADMIN));

            profileRepository.save(new Profile("customer1", "고객1"));
            profileRepository.save(new Profile("customer2", "고객2"));
            profileRepository.save(new Profile("customer3", "고객3"));
            profileRepository.save(new Profile("customer4", "고객4"));
            profileRepository.save(new Profile("customer5", "고객5"));
            profileRepository.save(new Profile("seller1", "판매자1", "판매자1입니다.", "여성복"));
            profileRepository.save(new Profile("seller2", "판매자2", "판매자2입니다.", "남성복"));
            profileRepository.save(new Profile("seller3", "판매자3", "판매자3입니다.", "아동복"));
            profileRepository.save(new Profile("seller4", "판매자4", "판매자4입니다.", "스포츠"));
            profileRepository.save(new Profile("seller5", "판매자5", "판매자5입니다.", "캐주얼"));
        };
    }

}
