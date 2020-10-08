package com.eigenbaumarkt.spring5mvc.restapplication.services;

import com.eigenbaumarkt.spring5mvc.restapplication.api.v1.mapper.CategoryMapper;
import com.eigenbaumarkt.spring5mvc.restapplication.api.v1.model.CategoryDTO;
import com.eigenbaumarkt.spring5mvc.restapplication.domain.Category;
import com.eigenbaumarkt.spring5mvc.restapplication.repositories.CategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

class CategoryServiceTest {

    public static final Long ID = 2L;
    public static final String NAME = "Jimmy";
    CategoryService categoryService;

    @Mock
    CategoryRepository categoryRepository;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        // set up the Mapper:
        categoryService = new CategoryServiceImpl(CategoryMapper.INSTANCE, categoryRepository);
    }

    @Test
    public void getAllCategories() throws Exception {

        // given
        List<Category> categories = Arrays.asList(new Category(), new Category(), new Category());
        when(categoryRepository.findAll()).thenReturn(categories);

        // when
        List<CategoryDTO> categoryDTOS = categoryService.getAllCategories();

        // then
        assertEquals(3, categoryDTOS.size());

    }

    @Test
    public void getCategoriesByName() throws Exception {

        // given
        Category category = new Category();
        category.setId(ID);
        category.setName(NAME);

        when(categoryRepository.findByName(anyString())).thenReturn(category);

        // when
        CategoryDTO categoryDTO = categoryService.getCategoryByName(NAME);

        // then
        assertEquals(ID, categoryDTO.getId());
        assertEquals(NAME, categoryDTO.getName());

    }

}
