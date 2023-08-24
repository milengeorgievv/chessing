package com.diploma.chessing.service.utils;

import com.diploma.chessing.dto.CustomerDTO;
import com.diploma.chessing.model.Customer;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CustomerMapper {
    CustomerDTO toDTO(Customer customer);

    Customer toEntity(CustomerDTO customer);

    List<CustomerDTO> toListDTO(List<Customer> customers);

    List<CustomerDTO> toListDTO(Page<Customer> customers);
}
