package com.lixo.pos.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Set;

@Entity
@Setter
@Getter
public class MenuItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String name;
    
    private String description;
    
    private BigDecimal price;
    
    // ... other properties

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "menu_id")
    private Menu menu;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "restaurants_id")
    private Restaurant restaurant;

    @JsonIgnore
    @OneToMany(mappedBy = "menuItem")
    private Set<MenuItemCombo> menuItemCombo;

    // ... getters and setters
}