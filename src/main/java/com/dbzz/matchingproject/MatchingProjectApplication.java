package com.dbzz.matchingproject;

import com.dbzz.matchingproject.entity.*;
import com.dbzz.matchingproject.enums.UserRoleEnum;
import com.dbzz.matchingproject.repository.*;
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
                                  ProductRepository productRepository,
                                  FormRepository formRepository,
                                  ShippingInfoRepository shippingInfoRepository,
                                  PasswordEncoder passwordEncoder) {

        return (args) -> {
            userRepository.save(new User("customer1", passwordEncoder.encode("1234?5678"), UserRoleEnum.CUSTOMER));
            userRepository.save(new User("customer2", passwordEncoder.encode("1234?5678"), UserRoleEnum.CUSTOMER));
            userRepository.save(new User("customer3", passwordEncoder.encode("1234?5678"), UserRoleEnum.CUSTOMER));
            userRepository.save(new User("customer4", passwordEncoder.encode("1234?5678"), UserRoleEnum.CUSTOMER));
            userRepository.save(new User("customer5", passwordEncoder.encode("1234?5678"), UserRoleEnum.CUSTOMER));
            userRepository.save(new User("seller1", passwordEncoder.encode("1234?5678"), UserRoleEnum.SELLER));
            userRepository.save(new User("seller2", passwordEncoder.encode("1234?5678"), UserRoleEnum.SELLER));
            userRepository.save(new User("seller3", passwordEncoder.encode("1234?5678"), UserRoleEnum.SELLER));
            userRepository.save(new User("seller4", passwordEncoder.encode("1234?5678"), UserRoleEnum.SELLER));
            userRepository.save(new User("seller5", passwordEncoder.encode("1234?5678"), UserRoleEnum.SELLER));
            userRepository.save(new User("admin", passwordEncoder.encode("1234?5678"), UserRoleEnum.ADMIN));

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

            productRepository.save(new Product("seller1", "블라우스", 20000, "블라우스 입니다."));
            productRepository.save(new Product("seller1", "치마", 30000, "치마 입니다."));
            productRepository.save(new Product("seller1", "바지", 30000, "바지 입니다."));
            productRepository.save(new Product("seller1", "자켓", 40000, "자켓 입니다."));
            productRepository.save(new Product("seller1", "레깅스", 20000, "레깅스 입니다."));
            productRepository.save(new Product("seller2", "셔츠", 20000, "셔츠 입니다."));
            productRepository.save(new Product("seller2", "바지", 20000, "바지 입니다."));
            productRepository.save(new Product("seller2", "재킷", 20000, "재킷 입니다."));
            productRepository.save(new Product("seller3", "패딩", 20000, "패딩 입니다."));
            productRepository.save(new Product("seller3", "후드티", 20000, "후드티 입니다."));
            productRepository.save(new Product("seller3", "후드집업", 20000, "후드집업 입니다."));
            productRepository.save(new Product("seller4", "니트", 20000, "니트 입니다."));
            productRepository.save(new Product("seller4", "목도리", 20000, "목도리 입니다."));
            productRepository.save(new Product("seller4", "스웨터", 20000, "스웨터 입니다."));
            productRepository.save(new Product("seller5", "코트", 20000, "코트 입니다."));
            productRepository.save(new Product("seller5", "양말", 20000, "양말 입니다."));
            productRepository.save(new Product("seller5", "남방", 20000, "남방 입니다."));

            formRepository.save(new Form("customer1", "고객1", "악세사리"));
            formRepository.save(new Form("customer2", "고객2", "잡화"));

            shippingInfoRepository.save(new ShippingInfo("customer1", "집", "서울", "010-1234-1234"));
            shippingInfoRepository.save(new ShippingInfo("customer1", "회사", "성남", "010-1234-1234"));
            shippingInfoRepository.save(new ShippingInfo("customer2", "집", "강원", "010-1234-1234"));
            shippingInfoRepository.save(new ShippingInfo("customer2", "회사", "평양", "010-1234-1234"));
            shippingInfoRepository.save(new ShippingInfo("customer3", "집", "인천", "010-1234-1234"));
            shippingInfoRepository.save(new ShippingInfo("customer3", "회사", "일산", "010-1234-1234"));
            shippingInfoRepository.save(new ShippingInfo("customer4", "집", "과천", "010-1234-1234"));
            shippingInfoRepository.save(new ShippingInfo("customer4", "회사", "안양", "010-1234-1234"));
            shippingInfoRepository.save(new ShippingInfo("customer5", "집", "용인", "010-1234-1234"));
            shippingInfoRepository.save(new ShippingInfo("customer5", "회사", "하남", "010-1234-1234"));

        };
    }

}
