package com.diploma.chessing.service.utils;

import com.diploma.chessing.dto.CustomerDTO;
import com.diploma.chessing.model.Customer;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class CustomerMapperImpl implements CustomerMapper {

    @Override
    public CustomerDTO toDTO(Customer customer) {
        return new CustomerDTO(
                customer.getName(),
                customer.getEmail(),
                customer.getPass(),
                customer.getRating(),
                customer.isRegularCustomer()
        );
    }

    @Override
    public Customer toEntity(CustomerDTO customerDTO) {
        Customer customer = new Customer();
        customer.setName(customerDTO.getName());
        customer.setEmail(customerDTO.getEmail());
        customer.setPass(customerDTO.getPass());
        customer.setRating(customerDTO.getRating());
        customer.setRegularCustomer(customerDTO.isRegularCustomer());
        return customer;
    }

    @Override
    public List<CustomerDTO> toListDTO(List<Customer> customers) {
        return convertToList(customers.stream());
    }

    @Override
    public List<CustomerDTO> toListDTO(Page<Customer> customers) {
        return convertToList(customers.stream());
    }

    private List<CustomerDTO> convertToList (Stream<Customer> stream){
        return stream.map(this::toDTO).collect(Collectors.toList());
    }
}
