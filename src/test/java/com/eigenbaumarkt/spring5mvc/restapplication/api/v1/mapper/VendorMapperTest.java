package com.eigenbaumarkt.spring5mvc.restapplication.api.v1.mapper;

import com.eigenbaumarkt.spring5mvc.restapplication.api.v1.model.VendorDTO;
import com.eigenbaumarkt.spring5mvc.restapplication.domain.Vendor;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class VendorMapperTest {

    public static final String NAME = "someName";

    VendorMapper vendorMapper = VendorMapper.INSTANCE;

    @Test
    public void convertVendorToVendorDTO() throws Exception {

        // given
        Vendor vendor = new Vendor();
        vendor.setName(NAME);

        // when
        VendorDTO vendorDTO = vendorMapper.vendorToVendorDTO(vendor);

        // then
        assertEquals(vendor.getName(), vendorDTO.getName());

    }

    @Test
    public void convertVendorDTOToVendor() throws Exception {

        // given
        VendorDTO vendorDTO = new VendorDTO();
        vendorDTO.setName(NAME);

        // when
        Vendor vendor = vendorMapper.vendorDTOToVendor(vendorDTO);

        // then
        assertEquals(vendorDTO.getName(), vendor.getName());

    }



}
