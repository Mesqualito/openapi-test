package com.eigenbaumarkt.spring5mvc.restapplication.services;

import com.eigenbaumarkt.spring5mvc.restapplication.api.v1.mapper.VendorMapper;
import com.eigenbaumarkt.spring5mvc.restapplication.api.v1.model.VendorDTO;
import com.eigenbaumarkt.spring5mvc.restapplication.api.v1.model.VendorListDTO;
import com.eigenbaumarkt.spring5mvc.restapplication.domain.Vendor;
import com.eigenbaumarkt.spring5mvc.restapplication.exceptions.ResourceNotFoundException;
import com.eigenbaumarkt.spring5mvc.restapplication.repositories.VendorRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;


public class VendorServiceImplTest {

    public static final String NAME_1 = "My Vendor";
    public static final long ID_1 = 1L;
    public static final String NAME_2 = "My Vendor";
    public static final long ID_2 = 1L;

    @Mock
    VendorRepository vendorRepository;

    VendorService vendorService;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        vendorService = new VendorServiceImpl(VendorMapper.INSTANCE, vendorRepository);
    }

    @Test
    public void getVendorById() throws Exception {
        // given
        Vendor vendor = getVendor1();

        // Mockito Behaviour-Driven-Development Syntax since mockito 1.10.0
        given(vendorRepository.findById(anyLong())).willReturn(Optional.of(vendor));

        // when
        VendorDTO vendorDTO = vendorService.getVendorById(1L);

        // then
        then(vendorRepository).should(times(1)).findById(anyLong());

        // JUnit 5 with static (external) org.hamcrest.MatcherAssert.assertThat() & org.hamcrest.Matchers.equalTo()
        assertThat(vendorDTO.getName(), is(equalTo(NAME_1)));
    }

    @Test
    public void getVendorByIdNotFound() throws Exception {

        // @Test(expected = ResourceNotFoundException.class) in JUnit4 has moved to a lambda inside the Junit5-Test
        // to give more fine-grained control for exception assertion logic because it can be used around specific parts
        // of the code within the Test-method
        Assertions.assertThrows(ResourceNotFoundException.class, () -> {

            // given
            given(vendorRepository.findById(anyLong())).willReturn(Optional.empty());

            // when
            VendorDTO vendorDTO = vendorService.getVendorById(1L);

            // then
            then(vendorRepository).should(times(1)).findById(anyLong());
        });
    }

    @Test
    public void getAllVendors() throws Exception {
        // given
        List<Vendor> vendors = Arrays.asList(getVendor1(), getVendor2());
        given(vendorRepository.findAll()).willReturn(vendors);

        // when
        VendorListDTO vendorListDTO = vendorService.getAllVendors();

        // then
        then(vendorRepository).should(times(1)).findAll();
        assertThat(vendorListDTO.getVendorDTOs().size(), is(equalTo(2)));
    }

    @Test
    public void createNewVendor() throws Exception {
        // given
        VendorDTO vendorDTO = new VendorDTO();
        vendorDTO.setName(NAME_1);

        Vendor vendor = getVendor1();

        given(vendorRepository.save(any(Vendor.class))).willReturn(vendor);

        // when
        VendorDTO savedVendorDTO = vendorService.createNewVendorByDTO(vendorDTO);

        // then
        // 'should' defaults to times = 1
        then(vendorRepository).should().save(any(Vendor.class));
        assertThat(savedVendorDTO.getVendorUrl(), containsString("1"));
    }

    @Test
    public void saveVendorByDTO() throws Exception {

        // given
        VendorDTO vendorDTO = new VendorDTO();
        vendorDTO.setName(NAME_1);

        Vendor vendor = getVendor1();

        given(vendorRepository.save(any(Vendor.class))).willReturn(vendor);

        // when
        VendorDTO savedVendorDTO = vendorService.saveVendorByDTO(ID_1, vendorDTO);

        // then
        // 'should' defaults to times = 1
        then(vendorRepository).should().save(any(Vendor.class));
        assertThat(savedVendorDTO.getVendorUrl(), containsString("1"));
    }

    @Test
    public void patchVendor() throws Exception {
        // given
        VendorDTO vendorDTO = new VendorDTO();
        vendorDTO.setName(NAME_1);

        Vendor vendor = getVendor1();

        given(vendorRepository.findById(anyLong())).willReturn(Optional.of(vendor));
        given(vendorRepository.save(any(Vendor.class))).willReturn(vendor);

        // when
        VendorDTO savedVendorDTO = vendorService.patchVendorByDTO(ID_1, vendorDTO);

        // then
        // 'should' defaults to times = 1
        then(vendorRepository).should().save(any(Vendor.class));
        then(vendorRepository).should(times(1)).findById(anyLong());
        assertThat(savedVendorDTO.getVendorUrl(), containsString("1"));
    }

    @Test
    public void deleteVendorById() throws Exception {

        // when
        vendorService.deleteVendorById(1L);

        // then
        then(vendorRepository).should().deleteById(anyLong());
    }

    private Vendor getVendor1() {
        Vendor vendor = new Vendor();
        vendor.setName(NAME_1);
        vendor.setId(ID_1);
        return vendor;
    }

    private Vendor getVendor2() {
        Vendor vendor = new Vendor();
        vendor.setName(NAME_2);
        vendor.setId(ID_2);
        return vendor;
    }
}
