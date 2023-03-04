package com.lixo.pos.repository;

import com.lixo.pos.model.Combo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ComboRepository extends JpaRepository<Combo, Long> {
    List<Combo> findAllByRestaurantId(Long restaurantId);

    Optional<Combo> findByRestaurantIdAndId(Long restaurantId, Long id);

}
