package com.lixo.pos.repository;

import com.lixo.pos.model.Combo;
import com.lixo.pos.model.Item;
import com.lixo.pos.model.MenuItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RestaurantMenuRepository extends JpaRepository<Item, Long> {

    List<MenuItem> findAllByRestaurantId(Long restaurantId);

    Optional<MenuItem> findByRestaurantIdAndId(Long restaurantId, Long id);

    Optional<Combo> findComboByRestaurantIdAndId(Long restaurantId, Long id);

}
