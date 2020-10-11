package com.eigenbaumarkt.spring5mvc.restapplication.controllers.v1;

import com.eigenbaumarkt.spring5mvc.restapplication.api.v1.model.CategoryDTO;
import com.eigenbaumarkt.spring5mvc.restapplication.api.v1.model.CategoryListDTO;
import com.eigenbaumarkt.spring5mvc.restapplication.services.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

// Convenience-Annotation since Spring framework 4 instead of
// @Controller
@RestController
@RequestMapping(CategoryController.BASE_URL)
public class CategoryController {

    private final CategoryService categoryService;
    public static final String BASE_URL = "/api/v1/categories";

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    /*
    @GetMapping
    public ResponseEntity<CategoryListDTO> getAllCategories() {

        return new ResponseEntity<CategoryListDTO>(
                new CategoryListDTO(categoryService.getAllCategories()), HttpStatus.OK
        );
    }
    */

    // Alternative in @RestControllre annotated class:

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public CategoryListDTO getAllCategories() {

        return new CategoryListDTO(categoryService.getAllCategories());
    }

    @GetMapping("/{name}")
    @ResponseStatus(HttpStatus.OK)
    public CategoryDTO getCategoryByName(@PathVariable String name) {

        return categoryService.getCategoryByName(name);
    }

}
