package com.epam.project.mapper;

import com.epam.project.model.dto.CourierDto;
import com.epam.project.model.entity.Courier;
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
class CourierMapperTest {

    @Test
    @DisplayName("Test should map to dto")
    void toDto() {
        //given
        Courier courier = Courier.builder()
                .id(1L)
                .firstName("Manac")
                .lastName("Khan")
                .phoneNumber("0999888777")
                .idNumber("3333")
                .build();
        CourierMapper courierMapper = Mappers.getMapper(CourierMapper.class);
        //when
        CourierDto courierDto = courierMapper.toDto(courier);
        //then
        assertNotNull(courierMapper);
        assertNotNull(courierDto);
        assertEquals(courier.getId(), courierDto.getId());
        assertEquals(courier.getFirstName(), courierDto.getFirstName());
        assertEquals(courier.getLastName(), courierDto.getLastName());
        assertEquals(courier.getPhoneNumber(), courierDto.getPhoneNumber());
        assertEquals(courier.getIdNumber(), courierDto.getIdNumber());
    }

    @Test
    @DisplayName("Test should map to entity")
    void toEntity() {
        //given
        CourierDto courierDto = CourierDto.builder()
                .id(1L)
                .firstName("Almanbet")
                .lastName("Khan")
                .phoneNumber("0999888777")
                .idNumber("3333")
                .build();
        CourierMapper courierMapper = Mappers.getMapper(CourierMapper.class);
        //when
        Courier courier = courierMapper.toEntity(courierDto);
        //then
        assertNotNull(courierMapper);
        assertNotNull(courier);
        assertEquals(courier.getId(), courierDto.getId());
        assertEquals(courier.getFirstName(), courierDto.getFirstName());
        assertEquals(courier.getLastName(), courierDto.getLastName());
        assertEquals(courier.getPhoneNumber(), courierDto.getPhoneNumber());
        assertEquals(courier.getIdNumber(), courierDto.getIdNumber());
    }

    @Test
    @DisplayName("Test should map to dtos")
    void toDtos() {
        //given
        Courier courier = Courier.builder()
                .id(1L)
                .firstName("Sveta")
                .lastName("Volodina")
                .phoneNumber("0999888777")
                .idNumber("3333")
                .build();
        List<Courier> couriers = Arrays.asList(courier);
        CourierMapper mapper = Mappers.getMapper(CourierMapper.class);
        //when
        List<CourierDto> courierDtos = mapper.toDtos(couriers);
        //then
        assertNotNull(couriers);
        assertNotNull(mapper);
        assertNotNull(courierDtos);
        assertEquals(courier.getId(), courierDtos.get(0).getId());
        assertEquals(courier.getFirstName(), courierDtos.get(0).getFirstName());
        assertEquals(courier.getLastName(), courierDtos.get(0).getLastName());
        assertEquals(courier.getPhoneNumber(), courierDtos.get(0).getPhoneNumber());
        assertEquals(courier.getIdNumber(), courierDtos.get(0).getIdNumber());
    }

    @Test
    @DisplayName("Test should map to entities")
    void toEntities() {
        //given
        CourierDto courierDto = CourierDto.builder()
                .id(1L)
                .firstName("Svetlana")
                .lastName("Volodina")
                .phoneNumber("0999888777")
                .idNumber("3333")
                .build();
        List<CourierDto> courierDtos = Arrays.asList(courierDto);
        CourierMapper mapper = Mappers.getMapper(CourierMapper.class);
        //when
        List<Courier> couriers = mapper.toEntities(courierDtos);
        //then
        assertNotNull(courierDtos);
        assertNotNull(mapper);
        assertNotNull(couriers);
        assertEquals(courierDto.getId(), couriers.get(0).getId());
        assertEquals(courierDto.getFirstName(), couriers.get(0).getFirstName());
        assertEquals(courierDto.getLastName(), couriers.get(0).getLastName());
        assertEquals(courierDto.getPhoneNumber(), couriers.get(0).getPhoneNumber());
        assertEquals(courierDto.getIdNumber(), couriers.get(0).getIdNumber());
    }
}