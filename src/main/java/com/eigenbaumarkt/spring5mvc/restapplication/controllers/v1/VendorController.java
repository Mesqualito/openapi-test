package com.eigenbaumarkt.spring5mvc.restapplication.controllers.v1;

import com.eigenbaumarkt.spring5mvc.restapplication.api.v1.model.VendorDTO;
import com.eigenbaumarkt.spring5mvc.restapplication.api.v1.model.VendorListDTO;
import com.eigenbaumarkt.spring5mvc.restapplication.services.VendorService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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
    public VendorListDTO getVendorList() {
        return vendorService.getAllVendors();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public VendorDTO getVendorById(@PathVariable Long id) {
        return vendorService.getVendorById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public VendorDTO createNewCustomer(@RequestBody VendorDTO vendorDTO) {
        return vendorService.createNewVendorByDTO(vendorDTO);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public VendorDTO updateVendor(@PathVariable Long id, @RequestBody VendorDTO vendorDTO) {
        return vendorService.saveVendorByDTO(id, vendorDTO);
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public VendorDTO patchVendor(@PathVariable Long id, @RequestBody VendorDTO vendorDTO) {
        return vendorService.patchVendorByDTO(id, vendorDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteVendor(@PathVariable Long id) {
        vendorService.deleteVendorById(id);
    }

}
