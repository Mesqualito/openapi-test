package com.eigenbaumarkt.spring5mvc.restapplication.services;

import com.eigenbaumarkt.spring5mvc.model.CustomerDTO;
import com.eigenbaumarkt.spring5mvc.restapplication.api.v1.mapper.CustomerMapper;
import com.eigenbaumarkt.spring5mvc.restapplication.controllers.v1.CustomerController;
import com.eigenbaumarkt.spring5mvc.restapplication.domain.Customer;
import com.eigenbaumarkt.spring5mvc.restapplication.exceptions.ResourceNotFoundException;
import com.eigenbaumarkt.spring5mvc.restapplication.repositories.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerMapper customerMapper;
    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerMapper customerMapper, CustomerRepository customerRepository) {
        this.customerMapper = customerMapper;
        this.customerRepository = customerRepository;
    }

    @Override
    public List<CustomerDTO> getAllCustomers() {
        return customerRepository
                .findAll()
                .stream()
                .map(this::mapCustomerUrl)
                .collect(Collectors.toList());
    }

    @Override
    public CustomerDTO getCustomerById(Long id) {
        return customerRepository.findById(id)
                .map(this::mapCustomerUrl)
                .orElseThrow(ResourceNotFoundException::new);
    }

    /*
    @Override
    public CustomerDTO getCustomerByFirstNameAndLastName(String firstName, String lastName) {
        return customerRepository.findByFirstNameAndLastName(firstName, lastName)
                .map(
        );
    }
    */

    @Override
    public CustomerDTO createNewCustomerByDTO(CustomerDTO customerDTO) {

        return saveAndReturnDTO(customerMapper.customerDTOToCustomer(customerDTO));

    }

    @Override
    public CustomerDTO saveCustomerByDTO(Long id, CustomerDTO customerDTO) {

        Customer customer = customerMapper.customerDTOToCustomer(customerDTO);
        customer.setId(id);

        return saveAndReturnDTO(customer);
    }

    @Override
    public CustomerDTO patchCustomerByDTO(Long id, CustomerDTO customerDTO) {
        return customerRepository.findById(id).map(customer -> {

            if(customerDTO.getFirstname() != null) {
                customer.setFirstName(customerDTO.getFirstname());
            }
            if(customerDTO.getLastname() != null) {
                customer.setLastName(customerDTO.getLastname());
            }

            return saveAndReturnDTO(customer);

        }).orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public void deleteCustomerById(long id) {
        // TODO: implement error handling if id is not found
        // alternative: return "Ok" in any case (id found & deleted / id not found)
        customerRepository.deleteById(id);
    }

    private CustomerDTO saveAndReturnDTO(Customer customer) {

        Customer savedCustomer = customerRepository.save(customer);
        CustomerDTO returnDTO = customerMapper.customerToCustomerDTO(savedCustomer);
        returnDTO.setCustomerUrl(getCustomerUrl(savedCustomer.getId()));

        return returnDTO;

    }

    private String getCustomerUrl(long id) {
        return CustomerController.BASE_URL + "/" + id;
    }

    private CustomerDTO mapCustomerUrl(Customer customer) {
        CustomerDTO customerDTO = customerMapper.customerToCustomerDTO(customer);
        customerDTO.setCustomerUrl(getCustomerUrl(customer.getId()));
        return customerDTO;
    }
}
