package com.lixo.automate.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Automate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty(message = "company name is mandatory")
    private String name;

    private String companyAddress;

    private String city;
    private String state;
    private String country;
    private BigDecimal latitude;
    private BigDecimal longitude;


    public Automate(Long id) {
        this.id = id;
    }
}
