package com.lixo.pos.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@Setter
@Getter
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String name;
    
    private String description;
    
    // ... other properties
    
    @OneToOne
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;

    @OneToMany(mappedBy = "menu")
    private Set<MenuItem> items;

    @OneToMany(mappedBy = "menu")
    private Set<Combo> combos;
    
    // ... getters and setters
}