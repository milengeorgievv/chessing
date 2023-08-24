package com.diploma.chessing.controller;


import com.diploma.chessing.dto.CustomerDTO;
import com.diploma.chessing.exception.CustomerNotFoundException;
import com.diploma.chessing.model.Customer;
import com.diploma.chessing.service.impl.CustomerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {
    @Autowired
    private CustomerServiceImpl customerService;

    @GetMapping()
    public ResponseEntity<List<Customer>> getAllCustomers() {
        return ResponseEntity.ok(customerService.getAllCustomers());
    }

    @PostMapping("/add")
    public ResponseEntity<CustomerDTO> addCustomer(@RequestBody() CustomerDTO customer) {
        var newCustomer = customerService.addCustomer(customer);
        return new ResponseEntity<>(newCustomer, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerDTO> updateCompetitor(@PathVariable("id") Long id, @RequestBody() CustomerDTO customerDTO) throws CustomerNotFoundException {
        return new ResponseEntity<>(customerService.updateCustomer(id, customerDTO), HttpStatus.OK);
    }

    @PutMapping("/addOwner/{id}")
    public ResponseEntity<CustomerDTO> addOwnerToCompetitor(@PathVariable("id") Long id, @RequestParam("ownerId") Long ownerId) {
        return new ResponseEntity<>(customerService.addOwnerToCustomer(id, ownerId), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CustomerDTO> deleteCustomer(@PathVariable("id") Long id) {
        return new ResponseEntity<>(customerService.deleteCustomer(id), HttpStatus.OK);
    }

    @GetMapping("/customer")
    public String customer() {
        return "Welcome customer";
    }

    @GetMapping("/admin")
    public String admin() {
        return "Welcome admin";
    }
}
