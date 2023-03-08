package com.lixo.pos.repository;

import com.lixo.pos.model.Combo;
import com.lixo.pos.model.MenuItemCombo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MenuItemComboRepository extends JpaRepository<MenuItemCombo, Long> {
    List<MenuItemCombo> findAllByRestaurantId(Long restaurantId);

    Optional<MenuItemCombo> findByRestaurantIdAndId(Long restaurantId, Long id);


}
