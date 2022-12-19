package com.education.serivce.address.impl;

import com.education.entity.Address;
import com.education.repository.address.AddressRepository;
import com.education.serivce.address.AddressService;
import lombok.AllArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Service в "edo-repository", служит для связи контроллера и репозитория
 */
@Service
@AllArgsConstructor
public class AddressServiceImpl implements AddressService {

    /**
     * Поле "addressRepository" нужно для вызова Repository-слоя (edo-repository),
     * который нужен для связи с БД
     */
    private final AddressRepository addressRepository;

    /**
     * Метод сохранения нового адреса в БД
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void save(Address address) {
        addressRepository.save(address);
    }

    /**
     * Метод удаления адреса из БД
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delete(Address address) {
        addressRepository.delete(address);
    }

    /**
     * Метод, который возвращает адрес по Id
     */
    @Transactional(readOnly = true, rollbackFor = Exception.class)
    @Override
    public Address findById(@Param("id") Long id) {
        return addressRepository.findById(id).orElse(null);
    }

    /**
     * Метод, который возвращает адреса по их Id
     */
    @Transactional(readOnly = true, rollbackFor = Exception.class)
    @Override
    public List<Address> findAllById(@Param("ids") Iterable<Long> ids) {
        return addressRepository.findAllById(ids);
    }

    /**
     * Метод, который возвращает все адреса
     */
    @Transactional(readOnly = true, rollbackFor = Exception.class)
    @Override
    public List<Address> findAll() {
        return addressRepository.findAll();
    }
}
