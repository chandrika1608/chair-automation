package com.lixo.pos.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class FoodCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty(message = "Kitchen name is mandatory")
    private String name;
    @JsonIgnore
    @OneToOne(mappedBy = "foodCategory")
    private MenuItem menuItem;
}
