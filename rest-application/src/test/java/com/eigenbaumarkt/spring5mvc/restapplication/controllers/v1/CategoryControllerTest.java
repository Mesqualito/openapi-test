package com.eigenbaumarkt.spring5mvc.restapplication.controllers.v1;

import com.eigenbaumarkt.spring5mvc.restapplication.api.v1.model.CategoryDTO;
import com.eigenbaumarkt.spring5mvc.restapplication.exceptions.ResourceNotFoundException;
import com.eigenbaumarkt.spring5mvc.restapplication.exceptions.RestResponseEntityExceptionHandler;
import com.eigenbaumarkt.spring5mvc.restapplication.services.CategoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class CategoryControllerTest {

    public static final String NAME1 = "Marina";
    public static final String NAME2 = "Ivan";
    public static final Long ID1 = 1L;
    public static final Long ID2 = 1L;


    @Mock
    CategoryService categoryService;

    @InjectMocks
    CategoryController categoryController;

    MockMvc mockMvc;

    @BeforeEach
    public void setUp() throws Exception {

        MockitoAnnotations.initMocks(this);

        // next line is obsolet during "@InjectMocks" above
        // categoryController = new CategoryController(categoryService);

        mockMvc = MockMvcBuilders.standaloneSetup(categoryController)
                .setControllerAdvice(new RestResponseEntityExceptionHandler())
                .build();

    }

    @Test
    // "When the code under test in a unit test throws an exception, the test itself fails.
    // Therefore, there is no need to surround the tested code with a try-catch structure to detect failure.
    // Instead, you can simply move the exception type to the method signature."
    // from: https://rules.sonarsource.com/java/tag/error-handling/RSPEC-3658
    public void testListCategories() throws Exception {

        CategoryDTO category1 = new CategoryDTO();
        category1.setId(ID1);
        category1.setName(NAME1);

        CategoryDTO category2 = new CategoryDTO();
        category2.setId(ID2);
        category2.setName(NAME2);

        List<CategoryDTO> categoryDTOs = Arrays.asList(category1, category2);

        when(categoryService.getAllCategories()).thenReturn(categoryDTOs);

        mockMvc.perform(get(CategoryController.BASE_URL + "/")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.categories", hasSize(2)));
    }

    @Test
    public void testGetByNameCategories() throws Exception {

        CategoryDTO category1 = new CategoryDTO();
        category1.setId(ID1);
        category1.setName(NAME1);

        when(categoryService.getCategoryByName(anyString())).thenReturn(category1);

        mockMvc.perform(get(CategoryController.BASE_URL + "/" + NAME1)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", equalTo(NAME1)));
    }

    @Test
    public void testGetCustomExceptionByNameNotFound() throws Exception {

        when(categoryService.getCategoryByName(anyString())).thenThrow(ResourceNotFoundException.class);

        mockMvc.perform(get(CategoryController.BASE_URL + "/foo")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}
