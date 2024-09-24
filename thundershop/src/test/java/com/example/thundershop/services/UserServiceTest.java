package com.example.thundershop.services;

import com.example.thundershop.entity.*;
import com.example.thundershop.exceptions.ThunderException;
import com.example.thundershop.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private ProductService productService;

    @BeforeEach
    void setUp() {
        userService = new UserService(userRepository, productService);

        User user = new User();
        user.setFirstName("Furkan");
        user.setLastName("Güven");
        user.setPhone("5441805565");
        user.setEmail("fg@test.com");
        user.setAvatarUrl("https://www.revoyazilim.com/yonetim/upload/blogdetay/46aeebc689991e5768c2147143ccf2.jpg");
        user.setPassword("test1234");
        userRepository.save(user);
    }

    @AfterEach
    void tearDown() {
        if (userRepository.findUserByEmail("fg@test.com").isPresent()){
            User foundUser = userRepository.findUserByEmail("fg@test.com").get();
            userRepository.delete(foundUser);
        }
    }

    @Test
    void loadUserByUsername() {
        if (userRepository.findUserByEmail("fg@test.com").isPresent()){
            User foundUser = userRepository.findUserByEmail("fg@test.com").get();
            verify(userService).loadUserByUsername("fg@test.com");
        }
    }

    @Test
    void notLoadByUserName(){
        String invalidEmail = "invalid@test.com";

        when(userRepository.findUserByEmail(invalidEmail)).thenReturn(Optional.empty());
        assertThatThrownBy(() -> userService.loadUserByUsername(invalidEmail))
                .isInstanceOf(ThunderException.class)
                .hasMessageContaining("User credentials are not valid");
    }

    @Test
    void findUserById() {
    }

    @Test
    void addFavourites() {

    }

    @Test
    void addCartList() {

    }

    @Test
    void getFavourites() {
    }

    @Test
    void getCarts() {
    }

    @Test
    void getAddresses() {
    }
}