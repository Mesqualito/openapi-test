package com.eigenbaumarkt.spring5mvc.restapplication.services;

import com.eigenbaumarkt.spring5mvc.restapplication.api.v1.model.CustomerDTO;

import java.util.List;

public interface CustomerService {

    List<CustomerDTO> getAllCustomers();

    CustomerDTO getCustomerById(Long id);

    // CustomerDTO getCustomerByFirstNameAndLastName(String firstName, String lastName);

    CustomerDTO createNewCustomer(CustomerDTO customerDTO);

    CustomerDTO saveCustomerByDTO(Long id, CustomerDTO customerDTO);

    CustomerDTO patchCustomer(Long id, CustomerDTO customerDTO);

}
