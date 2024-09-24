package com.example.thundershop.services;

import com.example.thundershop.entity.Address;
import com.example.thundershop.entity.Product;
import com.example.thundershop.entity.User;
import com.example.thundershop.exceptions.ThunderException;
import com.example.thundershop.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements UserDetailsService {

    private UserRepository userRepository;
    private ProductService productService;

    public UserService(UserRepository userRepository, ProductService productService) {
        this.userRepository = userRepository;
        this.productService = productService;
    }

    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findUserByEmail(username)
                .orElseThrow(() -> {
                    throw new ThunderException("User credentials are not valid", HttpStatus.BAD_REQUEST);
                });
    }

    public List<User> findAll(){
        return userRepository.findAll();
    }

    public User findUserById(long id){
        if(userRepository.findById(id).isPresent()){
            return userRepository.findById(id).get();
        }
        throw new ThunderException("No user with this id was found.", HttpStatus.BAD_REQUEST);
    }

    public Product addFavourites(long userId, long productId){
        if(userRepository.findById(userId).isPresent()){
            User user = userRepository.findById(userId).get();
            Product product = productService.findProductById(productId);
            user.getFavourites().add(product);
            userRepository.save(user);
            return product;
        }
        throw  new ThunderException("There is not user which have " + userId, HttpStatus.BAD_REQUEST);
    }

    public Product addCartList(long userId, long productId){
        if(userRepository.findById(userId).isPresent()){
            User user = userRepository.findById(userId).get();
            Product product = productService.findProductById(productId);
            user.getCartList().add(product);
            userRepository.save(user);
            return product;
        }
        throw  new ThunderException("There is not user which have " + userId, HttpStatus.BAD_REQUEST);
    }

    public List<Product> getFavourites(long id){
        if(userRepository.findById(id).isPresent()){
            return userRepository.findById(id).get().getFavourites();
        }
        throw new ThunderException("There is not user which have " + id, HttpStatus.BAD_REQUEST);
    }

    public List<Product> getCarts(long id){
        if(userRepository.findById(id).isPresent()){
            return userRepository.findById(id).get().getCartList();
        }
        throw new ThunderException("There is not user which have " + id, HttpStatus.BAD_REQUEST);
    }

    public List<Address> getAddresses(long userId){
        if (userRepository.findById(userId).isPresent()){
            return userRepository.findById(userId).get().getAddresses();
        }
        throw new ThunderException("There is not user which have " + userId, HttpStatus.BAD_REQUEST);
    }
 }
