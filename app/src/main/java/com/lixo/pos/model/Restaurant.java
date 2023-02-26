package com.lixo.pos.model;

import jakarta.persistence.*;
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
    
    private String name;
    
    private String address;
    
    private String phoneNumber;
    
    private String website;
    private String phone;
    
    // ... other properties
    
    @OneToOne(mappedBy = "restaurant")
    private Menu menu;

    public Restaurant(Long id) {
        this.id=id;
    }
    // ... getters and setters
}