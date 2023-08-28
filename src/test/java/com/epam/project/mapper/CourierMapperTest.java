package com.epam.project.mapper;

import com.epam.project.model.dto.CourierDto;
import com.epam.project.model.entity.Courier;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
class CourierMapperTest {

    @Test
    void toDto() {
        Courier courier = Courier.builder()
                .id(1L)
                .firstName("Manac")
                .lastName("Khan")
                .phoneNumber("0999888777")
                .idNumber("3333")
                .build();

        CourierMapper courierMapper = Mappers.getMapper(CourierMapper.class);
        assertNotNull(courierMapper);
        CourierDto courierDto = courierMapper.toDto(courier);
        assertNotNull(courierDto);
        assertEquals(courier.getId(), courierDto.getId());
        assertEquals(courier.getFirstName(), courierDto.getFirstName());
        assertEquals(courier.getLastName(), courierDto.getLastName());
        assertEquals(courier.getPhoneNumber(), courierDto.getPhoneNumber());
        assertEquals(courier.getIdNumber(), courierDto.getIdNumber());
    }

    @Test
    void toEntity() {
        CourierDto courierDto = CourierDto.builder()
                .id(1L)
                .firstName("Almanbet")
                .lastName("Khan")
                .phoneNumber("0999888777")
                .idNumber("3333")
                .build();

        CourierMapper courierMapper = Mappers.getMapper(CourierMapper.class);
        assertNotNull(courierMapper);
        Courier courier = courierMapper.toEntity(courierDto);
        assertNotNull(courier);
        assertEquals(courier.getId(), courierDto.getId());
        assertEquals(courier.getFirstName(), courierDto.getFirstName());
        assertEquals(courier.getLastName(), courierDto.getLastName());
        assertEquals(courier.getPhoneNumber(), courierDto.getPhoneNumber());
        assertEquals(courier.getIdNumber(), courierDto.getIdNumber());
    }

    @Test
    void toDtos() {
        Courier courier = Courier.builder()
                .id(1L)
                .firstName("Sveta")
                .lastName("Volodina")
                .phoneNumber("0999888777")
                .idNumber("3333")
                .build();
        List<Courier> couriers = Arrays.asList(courier);
        assertNotNull(couriers);

        CourierMapper mapper = Mappers.getMapper(CourierMapper.class);
        assertNotNull(mapper);

        List<CourierDto> courierDtos = mapper.toDtos(couriers);
        assertNotNull(courierDtos);
        assertEquals(courier.getId(), courierDtos.get(0).getId());
        assertEquals(courier.getFirstName(), courierDtos.get(0).getFirstName());
        assertEquals(courier.getLastName(), courierDtos.get(0).getLastName());
        assertEquals(courier.getPhoneNumber(), courierDtos.get(0).getPhoneNumber());
        assertEquals(courier.getIdNumber(), courierDtos.get(0).getIdNumber());
    }

    @Test
    void toEntities() {
        CourierDto courierDto = CourierDto.builder()
                .id(1L)
                .firstName("Svetlana")
                .lastName("Volodina")
                .phoneNumber("0999888777")
                .idNumber("3333")
                .build();
        List<CourierDto> courierDtos = Arrays.asList(courierDto);
        assertNotNull(courierDtos);

        CourierMapper mapper = Mappers.getMapper(CourierMapper.class);
        assertNotNull(mapper);

        List<Courier> couriers = mapper.toEntities(courierDtos);
        assertNotNull(couriers);
        assertEquals(courierDto.getId(), couriers.get(0).getId());
        assertEquals(courierDto.getFirstName(), couriers.get(0).getFirstName());
        assertEquals(courierDto.getLastName(), couriers.get(0).getLastName());
        assertEquals(courierDto.getPhoneNumber(), couriers.get(0).getPhoneNumber());
        assertEquals(courierDto.getIdNumber(), couriers.get(0).getIdNumber());
    }
}