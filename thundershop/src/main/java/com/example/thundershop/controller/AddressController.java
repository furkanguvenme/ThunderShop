package com.example.thundershop.controller;

import com.example.thundershop.dto.AddressDto;
import com.example.thundershop.entity.Address;
import com.example.thundershop.services.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/address")
public class AddressController {

    private AddressService addressService;

    @Autowired
    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @GetMapping("/{id}")
    public AddressDto findById(@PathVariable long id){
        Address address = addressService.getById(id);
        return new AddressDto(address.getCity(), address.getDistrict(), address.getStreet(), address.getBuildingNo(), address.getApartmentNo());
    }

    @PostMapping("/{userId}")
    public AddressDto saveAddress(@PathVariable long userId, @RequestBody Address address){
        Address address1 = addressService.saveAddress(userId, address);
        return new AddressDto(address1.getCity(), address1.getDistrict(), address1.getStreet(), address1.getBuildingNo(), address1.getApartmentNo());
    }

    @PutMapping("/{id}")
    public AddressDto update(@PathVariable long id, @RequestBody Address address){
        Address address1 = addressService.updateAddress(id, address);
        return new AddressDto(address1.getCity(), address1.getDistrict(), address1.getStreet(), address1.getBuildingNo(), address1.getApartmentNo());
    }

    @DeleteMapping("/{id}")
    public AddressDto delete(@PathVariable long id){
        Address address = addressService.deleteAddress(id);
        return new AddressDto(address.getCity(), address.getDistrict(), address.getStreet(), address.getBuildingNo(), address.getApartmentNo());
    }

}
