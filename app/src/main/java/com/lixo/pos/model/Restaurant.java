package com.lixo.pos.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@Setter
@Getter
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String name;
    
    private String address;
    
    private String phoneNumber;
    
    private String website;
    
    // ... other properties
    
    @OneToMany(mappedBy = "restaurant")
    private Set<Menu> menus;
    
    // ... getters and setters
}