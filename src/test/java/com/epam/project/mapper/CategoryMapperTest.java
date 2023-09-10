package com.epam.project.mapper;

import com.epam.project.model.dto.CategoryDto;
import com.epam.project.model.entity.Category;
import org.junit.jupiter.api.BeforeEach;
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
    private CategoryMapper mapper;
    private Category category;
    private CategoryDto categoryDto;

    @BeforeEach
    public void setUp() {
        mapper = Mappers.getMapper(CategoryMapper.class);
        category = Category.builder().id(1L).name("fruits").build();
        categoryDto = CategoryDto.builder().id(2L).name("vegetables").build();
    }

    @Test
    @DisplayName("Test should map to dto")
    void toDto() {
        CategoryDto categoryDto = mapper.toDto(category);

        assertNotNull(mapper);
        assertNotNull(categoryDto);
        assertEquals(categoryDto.getId(), category.getId());
        assertEquals(categoryDto.getName(), category.getName());
    }

    @Test
    @DisplayName("Test should map to entity")
    void toEntity() {
        Category category = mapper.toEntity(categoryDto);

        assertNotNull(mapper);
        assertNotNull(category);
        assertEquals(category.getId(), categoryDto.getId());
        assertEquals(category.getName(), categoryDto.getName());
    }

    @Test
    @DisplayName("Test should map to dtos")
    void toDtos() {
        List<Category> categories = Arrays.asList(category);

        List<CategoryDto> categoryDtos = mapper.toDtos(categories);

        assertNotNull(categories);
        assertNotNull(mapper);
        assertNotNull(categoryDtos);
        assertEquals(categoryDtos.get(0).getId(), category.getId());
        assertEquals(categoryDtos.get(0).getName(), category.getName());
    }

    @Test
    @DisplayName("Test should map to entities")
    void toEntities() {
        List<CategoryDto> categoryDtos = Arrays.asList(categoryDto);

        List<Category> category = mapper.toEntities(categoryDtos);

        assertNotNull(categoryDtos);
        assertNotNull(mapper);
        assertNotNull(category);
        assertEquals(category.get(0).getId(), categoryDto.getId());
        assertEquals(category.get(0).getName(), categoryDto.getName());
    }
}