package com.diploma.chessing.service.utils;

import com.diploma.chessing.dto.OwnerDTO;
import com.diploma.chessing.model.Owner;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class OwnerMapperImpl implements OwnerMapper {

    @Override
    public OwnerDTO toDTO(Owner owner) {
        return new OwnerDTO(
                owner.getName(),
                owner.getEmail(),
                owner.getPass(),
                owner.getJob(),
                owner.getCity()
        );
    }

    @Override
    public Owner toEntity(OwnerDTO ownerDTO) {
        Owner owner = new Owner();
        owner.setName(ownerDTO.getName());
        owner.setEmail(ownerDTO.getEmail());
        owner.setPass(ownerDTO.getPass());
        owner.setJob(ownerDTO.getJob());
        owner.setCity(ownerDTO.getCity());
        return owner;
    }

    @Override
    public List<OwnerDTO> toListDTO(List<Owner> owners) {
        return convertToList(owners.stream());
    }

    @Override
    public List<OwnerDTO> toListDTO(Page<Owner> owners) {
        return convertToList(owners.stream());
    }

    private List<OwnerDTO> convertToList (Stream<Owner> stream){
        return stream.map(this::toDTO).collect(Collectors.toList());
    }
}
