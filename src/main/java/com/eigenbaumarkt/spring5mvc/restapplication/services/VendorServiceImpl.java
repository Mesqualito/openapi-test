package com.eigenbaumarkt.spring5mvc.restapplication.services;

import com.eigenbaumarkt.spring5mvc.restapplication.api.v1.mapper.VendorMapper;
import com.eigenbaumarkt.spring5mvc.restapplication.api.v1.model.VendorDTO;
import com.eigenbaumarkt.spring5mvc.restapplication.api.v1.model.VendorListDTO;
import com.eigenbaumarkt.spring5mvc.restapplication.controllers.v1.VendorController;
import com.eigenbaumarkt.spring5mvc.restapplication.domain.Vendor;
import com.eigenbaumarkt.spring5mvc.restapplication.exceptions.ResourceNotFoundException;
import com.eigenbaumarkt.spring5mvc.restapplication.repositories.VendorRepository;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class VendorServiceImpl implements VendorService {

    private final VendorMapper vendorMapper;
    private final VendorRepository vendorRepository;

    public VendorServiceImpl(VendorMapper vendorMapper, VendorRepository vendorRepository) {
        this.vendorMapper = vendorMapper;
        this.vendorRepository = vendorRepository;
    }

    @Override
    public VendorListDTO getAllVendors() {
        return new VendorListDTO(
                vendorRepository
                .findAll()
                .stream()
                .map(this::mapVendorUrl)
                .collect(Collectors.toList())
        );
    }

    @Override
    public VendorDTO getVendorById(Long id) {
        return vendorRepository.findById(id)
                .map(this::mapVendorUrl)
                .orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public VendorDTO getVendorByName(String name) {
        return vendorRepository.findByName(name)
                .map(this::mapVendorUrl)
                .orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public VendorDTO createNewVendorByDTO(VendorDTO vendorDTO) {
        return saveAndReturnDTO(vendorMapper.vendorDTOToVendor(vendorDTO));
    }

    @Override
    public VendorDTO saveVendorByDTO(Long id, VendorDTO vendorDTO) {

        Vendor vendor = vendorMapper.vendorDTOToVendor(vendorDTO);
        vendor.setId(id);

        return saveAndReturnDTO(vendor);
    }

    @Override
    public VendorDTO patchVendorByDTO(Long id, VendorDTO vendorDTO) {
        return vendorRepository.findById(id).map(vendor -> {
            if(vendorDTO.getName() != null) {
                vendor.setName(vendorDTO.getName());
            }
            // TODO: additional properties to test and set if given by DTO
            return saveAndReturnDTO(vendor);
        }).orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public void deleteVendorById(long id) {
        vendorRepository.deleteById(id);
    }

    private VendorDTO saveAndReturnDTO(Vendor vendor) {

        Vendor savedVendor = vendorRepository.save(vendor);
        VendorDTO returnDTO = vendorMapper.vendorToVendorDTO(savedVendor);
        returnDTO.setVendorUrl(getVendorUrl(savedVendor.getId()));

        return returnDTO;
    }

    private String getVendorUrl(long id) {
        return VendorController.BASE_URL + "/" + id;
    }

    private VendorDTO mapVendorUrl(Vendor vendor) {
        VendorDTO vendorDTO = vendorMapper.vendorToVendorDTO(vendor);
        vendorDTO.setVendorUrl(getVendorUrl(vendor.getId()));
        return vendorDTO;
    }
}
