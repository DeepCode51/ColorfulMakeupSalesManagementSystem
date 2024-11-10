package com.susu.service.impl;

import com.susu.dao.AddressMapper;
import com.susu.entity.Address;
import com.susu.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressServiceImpl implements AddressService {
    @Autowired
    private AddressMapper addressMapper;

    @Override
    public void addAddress(Address address) {
        addressMapper.insert(address);
    }

    @Override
    public void update(Address address) {
        addressMapper.updateByPrimaryKey(address);
    }

    @Override
    public void delete(int id) {
        addressMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void deleteByBatch(String[] d) {
        addressMapper.deleteByBatch(d);
    }

    @Override
    public List<Address> showAddress(int userId) {
        return addressMapper.showAddress(userId);
    }

    @Override
    public void setDeafult(Address address) {
         addressMapper.setDeafult(address);
    }

    @Override
    public void setStatus(Address address) {
        addressMapper.setStatus(address);
    }

}
