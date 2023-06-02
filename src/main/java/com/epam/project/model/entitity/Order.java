package com.epam.project.model.entitity;


import com.epam.project.model.enums.Status;

import java.time.LocalDate;
import java.util.List;


public class Order {
    private Long id;
    private LocalDate shipDate;
    private LocalDate createdAt;
    private Status status;
    private boolean complete;
    private List<Product> items;
}