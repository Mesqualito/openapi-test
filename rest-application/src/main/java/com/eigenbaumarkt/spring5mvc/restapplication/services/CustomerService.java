package com.eigenbaumarkt.spring5mvc.restapplication.services;


import com.eigenbaumarkt.spring5mvc.model.CustomerDTO;

import java.util.List;

public interface CustomerService {

    List<CustomerDTO> getAllCustomers();

    CustomerDTO getCustomerById(Long id);

    // CustomerDTO getCustomerByFirstnameAndLastname(String firstname, String lastname);

    CustomerDTO createNewCustomerByDTO(CustomerDTO customerDTO);

    CustomerDTO saveCustomerByDTO(Long id, CustomerDTO customerDTO);

    CustomerDTO patchCustomerByDTO(Long id, CustomerDTO customerDTO);

    void deleteCustomerById(long id);

}
