package com.diploma.chessing.service.utils;

import com.diploma.chessing.dto.OwnerDTO;
import com.diploma.chessing.model.Owner;
import org.springframework.data.domain.Page;

import java.util.List;

public interface OwnerMapper {
    OwnerDTO toDTO(Owner owner);

    Owner toEntity(OwnerDTO owner);

    List<OwnerDTO> toListDTO(List<Owner> owners);

    List<OwnerDTO> toListDTO(Page<Owner> owners);
}
