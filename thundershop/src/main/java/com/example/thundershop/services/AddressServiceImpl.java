package com.example.thundershop.services;

import com.example.thundershop.entity.Address;
import com.example.thundershop.entity.User;
import com.example.thundershop.exceptions.ThunderException;
import com.example.thundershop.repository.AddressRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class AddressServiceImpl implements AddressService {

    private AddressRepository addressRepository;
    private UserService userService;

    @Autowired
    public AddressServiceImpl(AddressRepository addressRepository, UserService userService) {
        this.addressRepository = addressRepository;
        this.userService = userService;
    }

    @Override
    @Transactional
    public Address getById(long id){
        if(addressRepository.findById(id).isPresent()){
            return addressRepository.findById(id).get();
        }
        throw new ThunderException("This id could not be synchronized with any address", HttpStatus.BAD_REQUEST);
    }

    @Override
    public Address saveAddress(long id, Address address) {
        User user = addressRepository.findUserById(id);
//        User user = userService.findUserById(id);
        user.addNewAddress(address);
        return addressRepository.save(address);
    }

    @Override
    public Address updateAddress(long id, Address address) {
        if(addressRepository.findById(id).isPresent()){
            address.setId(id);
            address.setUsers(addressRepository.findById(id).get().getUsers());
            return addressRepository.save(address);
        }
        throw new ThunderException("There is not address which have " + id, HttpStatus.BAD_REQUEST);
    }

    @Override
    public Address deleteAddress(long id) {
        if(addressRepository.findById(id).isPresent()){
            Address address = addressRepository.findById(id).get();
            addressRepository.deleteById(id);
            return address;
        }
        throw new ThunderException("There is not address which have " + id, HttpStatus.BAD_REQUEST);
    }

}
