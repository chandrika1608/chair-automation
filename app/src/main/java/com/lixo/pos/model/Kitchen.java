package com.lixo.pos.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
@Entity
@Table(name = "kitchen")
public class Kitchen extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty(message = "Kitchen name is mandatory")
    private String name;

    @NotEmpty(message = "Kitchen name is mandatory")
    private String restaurantId;

    @NotEmpty(message = "Status is mandatory")
    private String status;
    @NotEmpty(message = "Location is mandatory")
    private String location;

    @OneToOne
    @JoinColumn(name = "menu_item_id")
    private MenuItem menuItem;

}
