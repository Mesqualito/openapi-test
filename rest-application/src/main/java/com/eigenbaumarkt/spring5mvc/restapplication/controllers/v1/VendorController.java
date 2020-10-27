package com.eigenbaumarkt.spring5mvc.restapplication.controllers.v1;

import com.eigenbaumarkt.spring5mvc.restapplication.api.v1.model.VendorDTO;
import com.eigenbaumarkt.spring5mvc.restapplication.api.v1.model.VendorListDTO;
import com.eigenbaumarkt.spring5mvc.restapplication.services.VendorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Api(description = "This is the old way to add a description to an API endpoint / Controller Bean, " +
        "but there is too much unclear at the moment with Swagger2 turning to OpenAPI (Swagger 3)," +
        "integrating in Spring 5.")
@RestController
@RequestMapping(VendorController.BASE_URL)
public class VendorController {

    private final VendorService vendorService;
    public static final String BASE_URL = "/api/v1/vendors";

    public VendorController(VendorService vendorService) {
        this.vendorService = vendorService;
    }

    // @RestController & @ResponseStatus(HttpStatus.XXX) =>
    // the returned data will get rendered as an object based on the content type of the request
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "This method will get all Vendors through Data Transfer Objects as a List from the Service " +
            "and return a VendorListDTO-Object.", notes = "These are some notes, added to the Operation in the API and " +
            "listed in smaller letters to the UI tab.")
    public VendorListDTO getVendorList() {
        return vendorService.getAllVendors();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "The GET-Operation with 'id' will list the vendor with the given id.")
    public VendorDTO getVendorById(@PathVariable Long id) {
        return vendorService.getVendorById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "The POST-Operation will return a 'HttpStatus.CREATED' and create a new vendor.")
    public VendorDTO createNewCustomer(@RequestBody VendorDTO vendorDTO) {
        return vendorService.createNewVendorByDTO(vendorDTO);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "The PUT-Operation with 'id' will update the vendor with the given id.")
    public VendorDTO updateVendor(@PathVariable Long id, @RequestBody VendorDTO vendorDTO) {
        return vendorService.saveVendorByDTO(id, vendorDTO);
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "The PATCH-Operation with 'id' will update only new values from the input of the vendor-object " +
            "with the given id.")
    public VendorDTO patchVendor(@PathVariable Long id, @RequestBody VendorDTO vendorDTO) {
        return vendorService.patchVendorByDTO(id, vendorDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "The DELETE-Operation with 'id' will delete the vendor with the given id and return back " +
            "a 'HttpStatus.OK'.")
    public void deleteVendor(@PathVariable Long id) {
        vendorService.deleteVendorById(id);
    }

}
