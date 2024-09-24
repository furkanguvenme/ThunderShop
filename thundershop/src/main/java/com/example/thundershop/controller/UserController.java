package com.example.thundershop.controller;

import com.example.thundershop.dto.AddressDto;
import com.example.thundershop.dto.ProductDto;
import com.example.thundershop.dto.UserDto;
import com.example.thundershop.entity.Product;
import com.example.thundershop.entity.User;
import com.example.thundershop.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/user")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/find/{id}")
    public UserDto findUserById(@PathVariable long id){
        User user = userService.findUserById(id);
        return new UserDto(user.getFirstName(),
                user.getLastName(),
                user.getPhone(),
                user.getEmail(),
                user.getPassword());
    }

    @GetMapping("/favourites/{userId}")
    public List<ProductDto> getFavourites(@PathVariable long userId){
        return userService.getFavourites(userId).stream().map(product ->
                new ProductDto(product.getId(),
                        product.getTeamName(),
                        product.getSize(),
                        product.getPrice(),
                        product.getStock(),
                        product.getImages())).collect(Collectors.toList());
    }

    @GetMapping("/carts/{userId}")
    public List<ProductDto> getCarts(@PathVariable long userId){
        return userService.getCarts(userId).stream().map(product ->
                new ProductDto(product.getId(),
                        product.getTeamName(),
                        product.getSize(),
                        product.getPrice(),
                        product.getStock(),
                        product.getImages())).collect(Collectors.toList());
    }

    @GetMapping("/address/{id}")
    public List<AddressDto> getAddresses(@PathVariable long id){
        return userService.getAddresses(id).stream().map(address ->
                new AddressDto(address.getCity(),
                        address.getDistrict(),
                        address.getStreet(),
                        address.getBuildingNo(),
                        address.getApartmentNo())).collect(Collectors.toList());
    }

    @PostMapping("/favourites/{userId}/{productId}")
    public ProductDto addFavourites(@PathVariable(value = "userId") long userId, @PathVariable(value = "productId") long productId){
        Product product = userService.addFavourites(userId, productId);
        return new ProductDto(product.getId(),
                product.getTeamName(),
                product.getSize(),
                product.getPrice(),
                product.getStock(),
                product.getImages());
    }

    @PostMapping("/carts/{userId}/{productId}")
    public ProductDto addCartList(@PathVariable(value = "userId") long userId, @PathVariable(value = "productId") long productId){
        Product product = userService.addCartList(userId, productId);
        return new ProductDto(product.getId(),
                product.getTeamName(),
                product.getSize(),
                product.getPrice(),
                product.getStock(),
                product.getImages());
    }
}
