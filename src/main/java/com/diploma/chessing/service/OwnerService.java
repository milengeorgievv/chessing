package com.diploma.chessing.service;

import com.diploma.chessing.dto.OwnerDTO;
import com.diploma.chessing.model.Owner;

import java.util.List;

public interface OwnerService {
    List<Owner> getAllOwners();
    OwnerDTO addOwner(OwnerDTO owner);
    OwnerDTO deleteOwner(Long id);
    OwnerDTO getOwner(Long id);
}
