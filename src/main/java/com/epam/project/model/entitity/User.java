package com.epam.project.model.entitity;

import com.epam.project.model.enums.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "user_name")
    private String userName;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;
    @Column(name = "phone")
    private String phone;
    @OneToMany(targetEntity = Order.class, cascade = CascadeType.ALL)
    @JoinTable(name = "users_orders",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "order_id"))
    private List<Order> orders;
    @Enumerated(EnumType.STRING)
    private Role role;
}
