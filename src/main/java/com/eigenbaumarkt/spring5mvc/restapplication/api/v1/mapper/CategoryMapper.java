package com.eigenbaumarkt.spring5mvc.restapplication.api.v1.mapper;

import com.eigenbaumarkt.spring5mvc.restapplication.api.v1.model.CategoryDTO;
import com.eigenbaumarkt.spring5mvc.restapplication.domain.Category;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CategoryMapper {

    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

    // Annotation(s) only needed if Domain-Object and DTO have different naming of variables
    // @Mapping(source = "VariableNameInSourceDomainObject", target = "VariableNameInTargetDTO")
    // @Mapping(source = "one", target = "other")
    // [...]
    CategoryDTO categoryToCategoryDTO(Category category);

}
