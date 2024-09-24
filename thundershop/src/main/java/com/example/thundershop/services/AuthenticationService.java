package com.example.thundershop.services;

import com.example.thundershop.entity.Role;
import com.example.thundershop.entity.User;
import com.example.thundershop.exceptions.ThunderException;
import com.example.thundershop.repository.RoleRepository;
import com.example.thundershop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class AuthenticationService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public AuthenticationService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User register(User user){

        if(roleRepository.findByAuthority("USER").isPresent()){
            String encodedPassword = passwordEncoder.encode(user.getPassword());
            Role userRole = roleRepository.findByAuthority("USER").get();
            Set<Role> roles = new HashSet<>();
            roles.add(userRole);
            user.setPassword(encodedPassword);
            user.setAuthorities(roles);
            return userRepository.save(user);
        }

        throw new ThunderException("Data must be complete, non-null, and of the correct data type.", HttpStatus.BAD_REQUEST);
    }
}
