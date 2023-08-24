package com.diploma.chessing.service.impl;

import com.diploma.chessing.dto.OwnerDTO;
import com.diploma.chessing.model.Owner;
import com.diploma.chessing.repository.OwnerRepository;
import com.diploma.chessing.service.utils.OwnerMapper;
import com.diploma.chessing.service.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OwnerServiceImpl implements OwnerService {
    private final OwnerMapper ownerMapper;
    private final OwnerRepository ownerRepository;



    @Autowired(required = false)
    public OwnerServiceImpl(OwnerMapper ownerMapper, OwnerRepository ownerRepository) {
        this.ownerMapper = ownerMapper;
        this.ownerRepository = ownerRepository;
    }

    @Override
    public List<Owner> getAllOwners() {
        return ownerRepository.findAll();
    }

    @Override
    public OwnerDTO addOwner(OwnerDTO ownerDTO) {
        Owner owner = ownerMapper.toEntity(ownerDTO);
        ownerRepository.save(owner);
        return ownerMapper.toDTO(owner);
    }

    @Override
    public OwnerDTO deleteOwner(Long id) {
        Optional<Owner> ownerToBeDeleted = ownerRepository.findOwnerById(id);
        ownerRepository.deleteById(id);
        return ownerMapper.toDTO(ownerToBeDeleted.get());
    }

    @Override
    public OwnerDTO getOwner(Long id) {
        Optional<Owner> ownerById = ownerRepository.findById(id);
        return ownerMapper.toDTO(ownerById.get());
    }
}
