package com.susu.service;

import com.susu.entity.Address;

import java.util.List;

public interface AddressService {
    void addAddress(Address address);

    void update(Address address);

    void delete(int id);

    void deleteByBatch(String[] d);

    List<Address> showAddress(int userId);

    void setDeafult(Address address);

    void setStatus(Address address);
}
