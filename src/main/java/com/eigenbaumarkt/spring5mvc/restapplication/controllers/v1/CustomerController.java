package com.eigenbaumarkt.spring5mvc.restapplication.controllers.v1;

import com.eigenbaumarkt.spring5mvc.restapplication.api.v1.model.CustomerDTO;
import com.eigenbaumarkt.spring5mvc.restapplication.api.v1.model.CustomerListDTO;
import com.eigenbaumarkt.spring5mvc.restapplication.services.CustomerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
// description Param deprecated; see e.g.: https://stackoverflow.com/questions/38074936/api-annotations-description-is-deprecated
@Api(description = "This is the Customer Controller")
@RequestMapping(CustomerController.BASE_URL)
public class CustomerController {

    private final CustomerService customerService;
    public static final String BASE_URL = "/api/v1/customers";

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    @ApiOperation(value = "This will get a list of customers.", notes = "These are some notes about the API that we can customize.")
    @ResponseStatus(HttpStatus.OK)
    public CustomerListDTO getAllCustomers() {

        // Unterschied CustomerListDTO und List<CustomerDTO> zwischen | Controller <=> Service |
        // nicht zwischen | Service <=> Repository | (auch in den Tests!!)
        CustomerListDTO customerListDTO = new CustomerListDTO();
        customerListDTO.setCustomerDTOs(customerService.getAllCustomers());
        return customerListDTO;
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDTO> getCustomerById(@PathVariable Long id){
        return new ResponseEntity<CustomerDTO>(
                customerService.getCustomerById(id), HttpStatus.OK);
    }

    @PostMapping
    // "@RequestBody" tells the Spring MVC to look at the body of the request and parse it
    // and try to create a customerDTO out of that
    public ResponseEntity<CustomerDTO> createNewCustomer(@RequestBody CustomerDTO customerDTO) {

        return new ResponseEntity<CustomerDTO>(customerService.createNewCustomerByDTO(customerDTO), HttpStatus.CREATED);

    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerDTO> updateCustomer(@PathVariable Long id, @RequestBody CustomerDTO customerDTO) {

        return new ResponseEntity<CustomerDTO>(customerService.saveCustomerByDTO(id, customerDTO), HttpStatus.OK);

    }

    @PatchMapping("/{id}")
    public ResponseEntity<CustomerDTO> patchCustomer(@PathVariable Long id, @RequestBody CustomerDTO customerDTO) {

        return new ResponseEntity<CustomerDTO>(customerService.patchCustomerByDTO(id, customerDTO), HttpStatus.OK);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long id) {

        customerService.deleteCustomerById(id);

        return new ResponseEntity<Void>(HttpStatus.OK);

    }

}
