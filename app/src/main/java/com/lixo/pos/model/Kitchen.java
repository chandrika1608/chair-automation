package com.lixo.pos.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "kitchen")
public class Kitchen extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "kitchen_id")
    private Long id;
    @Column(name = "kitchen_name")
    private String name;

    @Column(name = "restaurant_id")
    private Long restaurant;

    @Column(name = "status")
    private String status;
    @Column(name = "location")
    private String location;
}
