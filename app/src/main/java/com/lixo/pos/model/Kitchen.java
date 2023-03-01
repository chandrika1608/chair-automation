package com.lixo.pos.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
@Entity
@Table(name = "kitchen")
public class Kitchen extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)

    private String id;
    @Column(name = "kitchen_name")
    @NotEmpty(message = "Kitchen name is mandatory")
    private String name;

    @Column(name = "restaurant_id")
    @NotEmpty(message = "Kitchen name is mandatory")
    private String restaurantId;

    @Column(name = "status")
    @NotEmpty(message = "Status is mandatory")
    private String status;
    @Column(name = "location")
    @NotEmpty(message = "Location is mandatory")
    private String location;
}
