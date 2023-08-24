package com.diploma.chessing.service.impl;

import com.diploma.chessing.dto.CustomerDTO;
import com.diploma.chessing.exception.CustomerNotFoundException;
import com.diploma.chessing.model.Customer;
import com.diploma.chessing.model.Owner;
import com.diploma.chessing.repository.CustomerRepository;
import com.diploma.chessing.repository.OwnerRepository;
import com.diploma.chessing.service.utils.CustomerMapper;
import com.diploma.chessing.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {
    private final CustomerMapper customerMapper;
    private final CustomerRepository customerRepository;
    private final OwnerRepository ownerRepository;

    @Autowired
    public CustomerServiceImpl(CustomerMapper customerMapper, CustomerRepository customerRepository,
                               OwnerRepository ownerRepository) {
        this.customerMapper = customerMapper;
        this.customerRepository = customerRepository;
        this.ownerRepository = ownerRepository;
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public CustomerDTO addCustomer(CustomerDTO customerDTO) {
        Customer customer = customerMapper.toEntity(customerDTO);
        customerRepository.save(customer);
        return customerMapper.toDTO(customer);
    }

    @Override
    public CustomerDTO updateCustomer(Long id, CustomerDTO customerDTO) throws CustomerNotFoundException {
        Optional<Customer> customerToBeUpdatedOpt = Optional.of(customerRepository.getById(id));

        if (!customerToBeUpdatedOpt.isPresent()) {
            throw new CustomerNotFoundException(String.format("Customer with identification number %s could not be found.", id));
        }

        Customer customerUpdate = customerMapper.toEntity(customerDTO);
        customerUpdate.setId(customerToBeUpdatedOpt.get().getId());

        Customer customerToBeUpdated = customerToBeUpdatedOpt.get();
        customerToBeUpdated.setName(customerUpdate.getName());
        customerToBeUpdated.setEmail(customerUpdate.getEmail());
        customerToBeUpdated.setPass(customerUpdate.getPass());
        customerToBeUpdated.setRating(customerUpdate.getRating());
        customerToBeUpdated.setRegularCustomer(customerUpdate.isRegularCustomer());
        customerRepository.save(customerToBeUpdated);

        return customerMapper.toDTO(customerToBeUpdated);
    }

    @Override
    public CustomerDTO addOwnerToCustomer(Long id, Long ownerId) {
        Optional<Customer> customer = customerRepository.findCustomerById(id);
        Optional<Owner> owner = ownerRepository.findById(id);
        if(!customer.isPresent() || !owner.isPresent()) {

        }
        customer.get().setOwner(owner.get());
        customerRepository.save(customer.get());
        return customerMapper.toDTO(customer.get());
    }

    @Override
    public CustomerDTO deleteCustomer(Long id) {
        Optional<Customer> customerToBeDeleted = customerRepository.findCustomerById(id);
        customerRepository.deleteById(id);
        return customerMapper.toDTO(customerToBeDeleted.get());
    }
}
