package com.eigenbaumarkt.spring5mvc.restapplication.services;

import com.eigenbaumarkt.spring5mvc.restapplication.api.v1.model.CustomerDTO;

import java.util.List;

public interface CustomerService {

    List<CustomerDTO> getAllCustomers();

    CustomerDTO getCustomerById(Long id);

    // CustomerDTO getCustomerByFirstNameAndLastName(String firstName, String lastName);

    CustomerDTO createNewCustomerByDTO(CustomerDTO customerDTO);

    CustomerDTO saveCustomerByDTO(Long id, CustomerDTO customerDTO);

    CustomerDTO patchCustomerByDTO(Long id, CustomerDTO customerDTO);

    void deleteCustomerById(long id);

}
