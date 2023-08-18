package com.epam.project.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "product_name")
    private String productName;
    @Column(name = "price")
    private Integer price;
    @Column(name = "quantity")
    private Integer quantity;
    @OneToMany(cascade = CascadeType.ALL)
    private Set<Category> categories;
    @OneToMany(cascade = CascadeType.ALL)
    private Set<Order> orders;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "vendor_id")
    private Vendor vendor;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "_user_id")
    private User user;

}
