package com.lixo.pos.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Kitchen extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    private Long restaurantId;

    private String status;
    private String location;
    @JsonIgnore
    @OneToOne(mappedBy = "kitchen")
    private MenuItem menuItem;

}
