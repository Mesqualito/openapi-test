package com.eigenbaumarkt.spring5mvc.restapplication.api.v1.mapper;

import com.eigenbaumarkt.spring5mvc.model.CustomerDTO;
import com.eigenbaumarkt.spring5mvc.restapplication.domain.Customer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CustomerMapperTest {

    public static final String FIRSTNAME = "Marina";
    public static final String LASTNAME = "Mussel";
    CustomerMapper customerMapper = CustomerMapper.INSTANCE;

    @Test
    public void customerToCustomerDTO() throws Exception {

        // given
        Customer customer = new Customer();
        customer.setFirstName(FIRSTNAME);
        customer.setLastName(LASTNAME);

        // when
        CustomerDTO customerDTO = customerMapper.customerToCustomerDTO(customer);

        // then
        assertEquals(FIRSTNAME, customerDTO.getFirstname());
        assertEquals(LASTNAME, customerDTO.getLastname());

    }

}
