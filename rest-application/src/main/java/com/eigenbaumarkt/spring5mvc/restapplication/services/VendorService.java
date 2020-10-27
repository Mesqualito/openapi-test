package com.eigenbaumarkt.spring5mvc.restapplication.services;

import com.eigenbaumarkt.spring5mvc.restapplication.api.v1.model.VendorDTO;
import com.eigenbaumarkt.spring5mvc.restapplication.api.v1.model.VendorListDTO;

public interface VendorService {

    VendorListDTO getAllVendors();

    VendorDTO getVendorById(Long id);

    VendorDTO getVendorByName(String name);

    VendorDTO createNewVendorByDTO(VendorDTO vendorDTO);

    VendorDTO saveVendorByDTO(Long id, VendorDTO vendorDTO);

    VendorDTO patchVendorByDTO(Long id, VendorDTO vendorDTO);

    void deleteVendorById(long id);

}
