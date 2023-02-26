package com.lixo.pos.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Setter
@Getter
public class MenuItemCombo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String name;
    
    private BigDecimal price;
    @ManyToOne
    @JoinColumn(name = "combo_id")
    private Combo combo;
    
    // ... other properties
    
    @ManyToOne
    @JoinColumn(name = "menu_item_id")
    private MenuItem menuItem;
    
    // ... getters and setters
}