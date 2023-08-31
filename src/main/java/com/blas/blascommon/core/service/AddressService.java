package com.blas.blascommon.core.service;

import com.blas.blascommon.core.model.Address;
import java.util.List;

public interface AddressService {

  List<Address> getAllActiveAddressByUser(String userId);

  Address getAddressByAddressId(String addressId);

  Address getDefaultAddressByUser(String userId);

  Address createAddress(Address address);

  void updateAddress(Address address);
}
