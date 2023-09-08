package com.epam.project.model.entity;


import com.epam.project.model.enums.Status;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "_order")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "ship_date")
    private LocalDate shipDate;
    @Column(name = "created_at")
    private LocalDate createdAt;
    @Column(name = "updated_at")
    private LocalDate updatedAt;
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private Status status;
    @Column(name = "complete")
    private boolean complete;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id")
    private Product items;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "_user_id")
    private User user;

    @PrePersist
    public void checkOrderStatus() {
        if (status == null) {
            status = Status.APPROVED;
        }
    }

}