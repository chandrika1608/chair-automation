package com.lixo.pos.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import javax.print.DocFlavor;
import java.math.BigDecimal;
import java.util.Set;

@Entity
@Setter
@Getter
public class Combo {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    
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
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;

    @OneToMany(mappedBy = "combo")
    private Set<MenuItemCombo> menuItems;
    
    // ... getters and setters
}
