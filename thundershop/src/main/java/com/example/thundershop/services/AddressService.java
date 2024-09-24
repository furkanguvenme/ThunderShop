package com.example.thundershop.services;

import com.example.thundershop.entity.Address;

import java.util.List;

public interface AddressService {

    Address saveAddress(long id, Address address);

    Address getById(long id);

    Address updateAddress(long id, Address address);

    Address deleteAddress(long id);

}
