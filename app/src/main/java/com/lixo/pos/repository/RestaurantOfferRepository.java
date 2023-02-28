package com.lixo.pos.repository;

import com.lixo.pos.model.Offer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestaurantOfferRepository extends JpaRepository<Offer, String> {
}
