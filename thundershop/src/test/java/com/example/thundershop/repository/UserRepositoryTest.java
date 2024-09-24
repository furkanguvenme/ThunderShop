package com.example.thundershop.repository;

import com.example.thundershop.entity.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserRepositoryTest {

    private UserRepository userRepository;

    @Autowired
    public UserRepositoryTest(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @BeforeEach
    void setUp() {
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
        User foundUser = userRepository.findUserByEmail("fg@test.com").get();

        userRepository.delete(foundUser);
    }

    @DisplayName("Is it possible to find users via e-mail")
    @Test
    void findUserByEmail() {
        User foundUser = userRepository.findUserByEmail("fg@test.com").get();
        assertNotNull(foundUser);
    }
}