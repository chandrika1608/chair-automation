package com.lixo.pos.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Set;

@Entity
@Setter
@Getter
public class ComboOption {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String name;
    
    private BigDecimal price;
    
    // ... other properties
    
    @ManyToOne
    @JoinColumn(name = "menu_id")
    private Menu menu;
    
    @OneToMany(mappedBy = "comboOption")
    private Set<MenuItemOption> options;
    
    // ... getters and setters
}
