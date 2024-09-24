package com.example.thundershop.controller;

import com.example.thundershop.entity.User;
import com.example.thundershop.services.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Test
    @WithMockUser(username = "fg@test.com", roles = {"USER"})
    void findUserById() throws Exception {
        User user = new User();
        user.setId(1L);
        user.setFirstName("Furkan");
        user.setLastName("Güven");
        user.setPhone("5441805565");
        user.setEmail("fg@test.com");
        user.setAvatarUrl("https://www.revoyazilim.com/yonetim/upload/blogdetay/46aeebc689991e5768c2147143ccf2.jpg");
        user.setPassword(new BCryptPasswordEncoder().encode("test1234"));

        when(userService.findUserById(user.getId())).thenReturn(user);
        mockMvc.perform(MockMvcRequestBuilders.get("/user/find/{id}", user.getId())
                        .accept(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk());
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

    @Test
    void addFavourites() {
    }

    @Test
    void addCartList() {
    }

    public static String jsonToString(Object object){
        try{
            return new ObjectMapper().writeValueAsString(object);
        }catch(Exception ex){
            throw new RuntimeException(ex);
        }
    }
}