package com.diploma.chessing.service;

import com.diploma.chessing.dto.CustomerDTO;
import com.diploma.chessing.exception.CustomerNotFoundException;
import com.diploma.chessing.model.Customer;

import java.util.List;

public interface CustomerService {
    List<Customer> getAllCustomers();
    CustomerDTO addCustomer(CustomerDTO customer);

    CustomerDTO updateCustomer(Long id, CustomerDTO customerDTO) throws CustomerNotFoundException;

    CustomerDTO deleteCustomer(Long id);

    CustomerDTO addOwnerToCustomer(Long id, Long ownerId);
}
