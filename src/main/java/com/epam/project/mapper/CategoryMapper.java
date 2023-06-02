package com.epam.project.mapper;


import com.epam.project.model.dto.CategoryDto;
import com.epam.project.model.entitity.Category;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    CategoryDto toDto(Category category);

    Category toEntity(CategoryDto categoryDto);

    List<CategoryDto> toDtos(List<Category> categories);

    List<Category> toEntities(List<CategoryDto> categoryDtos);
}
