package com.blas.blascommon.core.service.impl;

import static com.blas.blascommon.constants.ResponseMessage.ADDRESS_ID_NOT_FOUND;
import static com.blas.blascommon.constants.ResponseMessage.USER_ID_NOT_FOUND;
import static com.blas.blascommon.utils.IdUtils.genUUID;

import com.blas.blascommon.core.dao.jpa.AddressDao;
import com.blas.blascommon.core.dao.jpa.AuthUserDao;
import com.blas.blascommon.core.model.Address;
import com.blas.blascommon.core.service.AddressService;
import com.blas.blascommon.exceptions.types.NotFoundException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = {Exception.class, Throwable.class})
public class AddressServiceImpl implements AddressService {

  @Lazy
  private final AddressDao addressDao;

  @Lazy
  private final AuthUserDao authUserDao;

  @Override
  public List<Address> getAllActiveAddressByUser(String userId) {
    if (authUserDao.existsById(userId)) {
      return addressDao.getAllActiveAddressByUser(userId);
    }
    throw new NotFoundException(USER_ID_NOT_FOUND);
  }

  @Override
  public Address getAddressByAddressId(String addressId) {
    return addressDao.findById(addressId)
        .orElseThrow(() -> new NotFoundException(ADDRESS_ID_NOT_FOUND));
  }

  @Override
  public Address getDefaultAddressByUser(String userId) {
    if (authUserDao.existsById(userId)) {
      return addressDao.getDefaultAddressByUser(userId);
    }
    throw new NotFoundException(USER_ID_NOT_FOUND);
  }

  @Override
  public Address createAddress(Address address) {
    address.setAddressId(genUUID());
    return addressDao.save(address);
  }

  @Override
  public void updateAddress(Address address) {
    if (addressDao.existsById(address.getAddressId())) {
      addressDao.save(address);
    }
    throw new NotFoundException(ADDRESS_ID_NOT_FOUND);
  }
}
