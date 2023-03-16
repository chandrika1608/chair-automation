package com.lixo.pos.repository;

import com.lixo.pos.model.Combo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RestaurantComboRepository extends JpaRepository<Combo, Long> {
    Optional<Combo> findComboByRestaurantIdAndId(Long restaurantId, Long id);

}
