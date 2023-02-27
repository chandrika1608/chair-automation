package com.lixo.pos.repository;

import com.lixo.pos.model.Kitchen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestaurantKitchenRepository extends JpaRepository<Kitchen, Long>
{
}
