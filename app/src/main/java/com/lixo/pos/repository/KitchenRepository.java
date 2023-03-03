package com.lixo.pos.repository;

import com.lixo.pos.model.Kitchen;
import com.lixo.pos.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface KitchenRepository extends JpaRepository<Kitchen, Long>
{
    List<Kitchen> findAllByRestaurantId(Long restaurantId);

    Optional<Kitchen> findByRestaurantIdAndId(Long restaurantId, Long id);
}
