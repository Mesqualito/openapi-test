package com.eigenbaumarkt.spring5mvc.restapplication.services;

import com.eigenbaumarkt.spring5mvc.model.CustomerDTO;
import com.eigenbaumarkt.spring5mvc.restapplication.api.v1.mapper.CustomerMapper;
import com.eigenbaumarkt.spring5mvc.restapplication.controllers.v1.CustomerController;
import com.eigenbaumarkt.spring5mvc.restapplication.domain.Customer;
import com.eigenbaumarkt.spring5mvc.restapplication.repositories.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

public class CustomerServiceImplTest {

    @Mock
    CustomerRepository customerRepository;

    CustomerMapper customerMapper = CustomerMapper.INSTANCE;

    CustomerServiceImpl customerService;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        // customerService = new CustomerServiceImpl();
        // customerService.setCustomerMapper(customerMapper);
        // customerService.setCustomerRepository(customerRepository);
        customerService = new CustomerServiceImpl(customerMapper, customerRepository);
    }

    @Test
    public void getAllCustomers() throws Exception {
        // given
        Customer customer1 = new Customer();
        customer1.setId(1L);
        customer1.setFirstName("Marina");
        customer1.setLastName("Johanniston");

        Customer customer2 = new Customer();
        customer2.setId(2l);
        customer2.setFirstName("Jochen");
        customer2.setLastName("Strullwald");

        when(customerRepository.findAll()).thenReturn(Arrays.asList(customer1, customer2));

        // when
        List<CustomerDTO> customerDTOs = customerService.getAllCustomers();

        // then
        assertEquals(2, customerDTOs.size());

    }

    @Test
    public void getCustomerById() throws Exception {
        // given
        Customer customer1 = new Customer();
        customer1.setId(1L);
        customer1.setFirstName("Marina");
        customer1.setLastName("Johanniston");

        when(customerRepository.findById(anyLong())).thenReturn(Optional.of(customer1));

        // when
        CustomerDTO customerDTO = customerService.getCustomerById(1L);

        // then
        assertEquals("Marina", customerDTO.getFirstname());
    }

    @Test
    public void createNewCustomer() throws Exception {

        // given
        CustomerDTO customer = new CustomerDTO();
        customer.setFirstname("Jochen");
        customer.setLastname("Puckdoll");

        Customer savedCustomer = new Customer();
        savedCustomer.setFirstName(customer.getFirstname());
        savedCustomer.setLastName(customer.getLastname());
        savedCustomer.setId(1L);

        when(customerRepository.save(any(Customer.class))).thenReturn(savedCustomer);

        // when
        CustomerDTO savedDTO = customerService.createNewCustomerByDTO(customer);

        // then
        assertEquals(customer.getFirstname(), savedDTO.getFirstname());
        assertEquals(customer.getLastname(), savedDTO.getLastname());
        assertEquals(CustomerController.BASE_URL + "/1", savedDTO.getCustomerUrl());
    }

    @Test
    public void saveCustomerByDTO() throws Exception {

        // given
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setFirstname("Marina");
        customerDTO.setLastname("Johanniston");

        Customer savedCustomer = new Customer();
        savedCustomer.setId(1L);
        savedCustomer.setFirstName(customerDTO.getFirstname());
        savedCustomer.setLastName(customerDTO.getLastname());

        when(customerRepository.save(any(Customer.class))).thenReturn(savedCustomer);

        // when
        CustomerDTO savedDTO = customerService.saveCustomerByDTO(savedCustomer.getId(), customerDTO);

        // then
        assertEquals(customerDTO.getFirstname(), savedDTO.getFirstname());
        assertEquals(customerDTO.getLastname(), savedDTO.getLastname());
        assertEquals(CustomerController.BASE_URL + "/1", savedDTO.getCustomerUrl());

    }

    @Test
    public void deleteCustomerById() throws Exception {

        Long id = 1L;

        customerRepository.deleteById(id);

        verify(customerRepository, times(1)).deleteById(anyLong());

    }
}
