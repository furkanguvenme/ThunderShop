package com.example.thundershop.repository;

import com.example.thundershop.entity.Address;
import com.example.thundershop.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AddressRepository extends JpaRepository<Address, Long> {

    @Query("SELECT u FROM User u WHERE u.id = :id")
    User findUserById(long id);

}
