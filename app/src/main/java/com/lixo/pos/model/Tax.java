package com.lixo.pos.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "tax")
public class Tax extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "tax_id")
    private String id;
    @Column(name = "tax_name")
    private String name;
    @Column(name = "tax_percentage")
    private float taxPercentage;
    @Column(name = "status")
    private String status;

}
