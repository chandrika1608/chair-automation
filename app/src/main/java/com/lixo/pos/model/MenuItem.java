package com.lixo.pos.model;

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
    
    @ManyToOne
    @JoinColumn(name = "menu_id")
    private Menu menu;
    
    @OneToMany(mappedBy = "menuItem")
    private Set<MenuItemOption> options;
    
    // ... getters and setters
}