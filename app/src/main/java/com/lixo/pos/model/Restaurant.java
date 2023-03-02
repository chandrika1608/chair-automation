package com.lixo.pos.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty(message = "Restaurant name is mandatory")
    private String name;
    @NotEmpty(message = "Restaurant address is mandatory")
    private String address;
    @NotEmpty(message = "Restaurant phone number is mandatory")
    private String phoneNumber;
    @NotEmpty(message = "Restaurant site name is mandatory")
    private String website;
    
    // ... other properties
    
    @OneToMany(mappedBy = "restaurant")
    private Set<MenuItem> menuItem;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;

    public Restaurant(Long id) {
        this.id=id;
    }
    // ... getters and setters
}