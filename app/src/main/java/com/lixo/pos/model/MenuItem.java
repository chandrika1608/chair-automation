package com.lixo.pos.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Set;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class MenuItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    private BigDecimal price;
    private String imageURL;
    private MenuType menuType;
    private MealType mealType;

    // ... other properties

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;

    @JsonIgnore
    @OneToMany(mappedBy = "menuItem")
    private Set<MenuItemCombo> menuItemCombo;

    @OneToOne
    @JoinColumn(name = "kitchen_id")
    private Kitchen kitchen;

    @OneToMany(mappedBy = "menuItem")
    private Set<Tax> taxes;

    @OneToOne
    @JoinColumn(name = "food_category_id")
    private FoodCategory foodCategory;

    public MenuItem(Long id) {
        this.id = id;
    }
}