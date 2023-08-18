package com.epam.project.controller;

import com.epam.project.model.dto.OrderDto;
import com.epam.project.model.dto.UserDto;
import com.epam.project.service.OrderService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/orders")
@SecurityRequirement(name = "clothShopApi")
@PreAuthorize("hasRole('BUYER')")
public class OrderController {
    private final OrderService orderService;

    @GetMapping("/all")
    @PreAuthorize("hasAuthority('buyer:read')")
    public List<OrderDto> findAll() {
        return orderService.findAll();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('buyer:read')")
    public OrderDto findById(@PathVariable Long id) {
        return orderService.findById(id);
    }


    @PostMapping("/save/{id}")
    @PreAuthorize("hasAuthority('buyer:create')")
    public OrderDto save(@PathVariable UUID id, @RequestBody OrderDto orderDto) {
        return orderService.saveOrder(orderDto, id);
    }

    @PutMapping("/update/{id}")
    @PreAuthorize("hasAuthority('buyer:update')")
    public OrderDto update(@PathVariable UUID id, @RequestBody OrderDto orderDto) {
        return orderService.update(orderDto, id);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('buyer:delete')")
    public void deleteById(@PathVariable Long id) {
        orderService.deleteById(id);
    }

}
