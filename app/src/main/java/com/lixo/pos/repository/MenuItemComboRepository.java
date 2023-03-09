package com.lixo.pos.repository;

import com.lixo.pos.model.MenuItemCombo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MenuItemComboRepository extends JpaRepository<MenuItemCombo, Long> {


    Optional<MenuItemCombo> findAllByComboId(Long comboId);
    List<MenuItemCombo> findByComboRestaurantId(Long restaurantId);
    Optional<MenuItemCombo> findByComboRestaurantIdAndComboId(Long restaurantId, Long comboId);

}




