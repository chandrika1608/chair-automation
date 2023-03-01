package com.lixo.pos.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
@Entity
@Table(name = "tax")
public class Tax extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)

    private String id;

    @NotEmpty(message = "Tax name is mandatory")
    private String name;
    @Column(name = "tax_percentage")
    @NotEmpty(message = "Tax percentage name is mandatory")
    private float taxPercentage;
    @Column(name = "status")
    private String status;

}
