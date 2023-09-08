package com.epam.project.mapper;

import com.epam.project.model.dto.CategoryDto;
import com.epam.project.model.entity.Category;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mapstruct.factory.Mappers;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@RunWith(SpringRunner.class)
class CategoryMapperTest {

    @Test
    @DisplayName("Test should map to dto")
    void toDto() {
        //given
        Category category = Category.builder()
                .id(1L)
                .name("fruits")
                .build();
        CategoryMapper mapper = Mappers.getMapper(CategoryMapper.class);
        //when
        CategoryDto categoryDto = mapper.toDto(category);
        //then
        assertNotNull(mapper);
        assertNotNull(categoryDto);
        assertEquals(categoryDto.getId(), category.getId());
        assertEquals(categoryDto.getName(), category.getName());
    }

    @Test
    @DisplayName("Test should map to entity")
    void toEntity() {
        //given
        CategoryDto categoryDto = CategoryDto.builder()
                .id(2L)
                .name("vegetables")
                .build();
        CategoryMapper mapper = Mappers.getMapper(CategoryMapper.class);
        //when
        Category category = mapper.toEntity(categoryDto);
        //then
        assertNotNull(mapper);
        assertNotNull(category);
        assertEquals(category.getId(), categoryDto.getId());
        assertEquals(category.getName(), categoryDto.getName());
    }

    @Test
    @DisplayName("Test should map to dto")
    void toDtos() {
        //given
        Category category = Category.builder()
                .id(1L)
                .name("dresses")
                .build();
        List<Category> categories = Arrays.asList(category);
        CategoryMapper categoryMapper = Mappers.getMapper(CategoryMapper.class);
        //when
        List<CategoryDto> categoryDtos = categoryMapper.toDtos(categories);
        //then
        assertNotNull(categories);
        assertNotNull(categoryMapper);
        assertNotNull(categoryDtos);
        assertEquals(categoryDtos.get(0).getId(), category.getId());
        assertEquals(categoryDtos.get(0).getName(), category.getName());
    }

    @Test
    @DisplayName("Test should map to entities")
    void toEntities() {
        //given
        CategoryDto categoryDto = CategoryDto.builder()
                .id(1L)
                .name("dresses")
                .build();
        List<CategoryDto> categoryDtos = Arrays.asList(categoryDto);
        CategoryMapper categoryMapper = Mappers.getMapper(CategoryMapper.class);
        //when
        List<Category> category = categoryMapper.toEntities(categoryDtos);
        //then
        assertNotNull(categoryDtos);
        assertNotNull(categoryMapper);
        assertNotNull(category);
        assertEquals(category.get(0).getId(), categoryDto.getId());
        assertEquals(category.get(0).getName(), categoryDto.getName());
    }
}