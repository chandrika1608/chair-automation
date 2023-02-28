package com.lixo.pos.repository;

import com.lixo.pos.model.MenuItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RestaurantMenuRepository extends JpaRepository<MenuItem, String> {

    List<MenuItem> findAllByRestaurantId(String restaurantId);

    Optional<MenuItem> findByRestaurantIdAndId(String restaurantId, String id);


}
