package com.lixo.pos.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class Tax extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty(message = "Tax name is mandatory")
    private String name;

    private float taxPercentage;
    private String status;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "menu_item_id")
    private  MenuItem menuItem;

    public Tax(Long id) {
        this.id = id;
    }
}
