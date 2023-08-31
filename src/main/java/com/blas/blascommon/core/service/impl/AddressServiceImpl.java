package com.blas.blascommon.core.service.impl;

import static com.blas.blascommon.constants.ResponseMessage.ADDRESS_ID_NOT_FOUND;
import static com.blas.blascommon.constants.ResponseMessage.USER_ID_NOT_FOUND;
import static com.blas.blascommon.utils.IdUtils.genUUID;

import com.blas.blascommon.core.dao.AddressDao;
import com.blas.blascommon.core.dao.AuthUserDao;
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
    authUserDao.findById(userId).orElseThrow(() -> new NotFoundException(USER_ID_NOT_FOUND));
    return addressDao.getAllActiveAddressByUser(userId);
  }

  @Override
  public Address getAddressByAddressId(String addressId) {
    return addressDao.findById(addressId)
        .orElseThrow(() -> new NotFoundException(ADDRESS_ID_NOT_FOUND));
  }

  @Override
  public Address getDefaultAddressByUser(String userId) {
    authUserDao.findById(userId).orElseThrow(() -> new NotFoundException(USER_ID_NOT_FOUND));
    return addressDao.getDefaultAddressByUser(userId);
  }

  @Override
  public Address createAddress(Address address) {
    address.setAddressId(genUUID());
    return addressDao.save(address);
  }

  @Override
  public void updateAddress(Address address) {
    addressDao.findById(address.getAddressId())
        .orElseThrow(() -> new NotFoundException(ADDRESS_ID_NOT_FOUND));
    addressDao.save(address);
  }
}
