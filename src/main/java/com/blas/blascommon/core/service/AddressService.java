package com.blas.blascommon.core.service;

import com.blas.blascommon.core.model.Address;
import java.util.List;

public interface AddressService {

  public List<Address> getAllActiveAddressByUser(String userId);

  public Address getAddressByAddressId(String addressId);

  public Address getDefaultAddressByUser(String userId);

  public Address createAddress(Address address);

  public void updateAddress(Address address);
}