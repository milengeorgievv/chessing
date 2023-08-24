package com.diploma.chessing.controller;

import com.diploma.chessing.dto.OwnerDTO;
import com.diploma.chessing.model.Owner;
import com.diploma.chessing.service.impl.OwnerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "owners")
public class OwnerController {
    //@Autowired
    private OwnerServiceImpl ownerService;

    @Autowired
    public OwnerController(OwnerServiceImpl ownerService) {
        this.ownerService = ownerService;
    }

    @GetMapping("/owners")
    public ResponseEntity<List<Owner>> getAllOwners() {
        return ResponseEntity.ok(ownerService.getAllOwners());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<OwnerDTO> getOwner(Long id) {
        return ResponseEntity.ok(ownerService.getOwner(id));
    }

    @PostMapping("/createOwner")
    public ResponseEntity<OwnerDTO> addOwner(@RequestBody() OwnerDTO ownerDTO) {
        return new ResponseEntity<>(ownerService.addOwner(ownerDTO), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<OwnerDTO> deleteUser(@PathVariable("id") Long id) {
        return new ResponseEntity<>(ownerService.deleteOwner(id), HttpStatus.OK);
    }
}
