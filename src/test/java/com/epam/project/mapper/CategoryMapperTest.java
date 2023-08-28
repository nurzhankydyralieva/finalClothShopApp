package com.epam.project.mapper;

import com.epam.project.model.dto.CategoryDto;
import com.epam.project.model.entity.Category;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
class CategoryMapperTest {

    @Test
    void toDto() {
        Category category = Category.builder()
                .id(1L)
                .name("fruits")
                .build();
        CategoryMapper mapper = Mappers.getMapper(CategoryMapper.class);
        assertNotNull(mapper);
        CategoryDto categoryDto = mapper.toDto(category);
        assertNotNull(categoryDto);
        assertEquals(categoryDto.getId(), category.getId());
        assertEquals(categoryDto.getName(), category.getName());
    }

    @Test
    void toEntity() {
        CategoryDto categoryDto = CategoryDto.builder()
                .id(2L)
                .name("vegetables")
                .build();
        CategoryMapper mapper = Mappers.getMapper(CategoryMapper.class);
        assertNotNull(mapper);
        Category category = mapper.toEntity(categoryDto);
        assertNotNull(category);
        assertEquals(category.getId(), categoryDto.getId());
        assertEquals(category.getName(), categoryDto.getName());
    }

    @Test
    void toDtos() {
        Category category = Category.builder()
                .id(1L)
                .name("dresses")
                .build();
        List<Category> categories = Arrays.asList(category);
        assertNotNull(categories);

        CategoryMapper categoryMapper = Mappers.getMapper(CategoryMapper.class);
        assertNotNull(categoryMapper);
        List<CategoryDto> categoryDtos = categoryMapper.toDtos(categories);
        assertNotNull(categoryDtos);
        assertEquals(categoryDtos.get(0).getId(), category.getId());
        assertEquals(categoryDtos.get(0).getName(), category.getName());
    }

    @Test
    void toEntities() {
        CategoryDto categoryDto = CategoryDto.builder()
                .id(1L)
                .name("dresses")
                .build();
        List<CategoryDto> categoryDtos = Arrays.asList(categoryDto);
        assertNotNull(categoryDtos);

        CategoryMapper categoryMapper = Mappers.getMapper(CategoryMapper.class);
        assertNotNull(categoryMapper);
        List<Category> category = categoryMapper.toEntities(categoryDtos);
        assertNotNull(category);
        assertEquals(category.get(0).getId(), categoryDto.getId());
        assertEquals(category.get(0).getName(), categoryDto.getName());
    }
}