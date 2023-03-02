package com.lixo.pos.repository;

import com.lixo.pos.model.MenuItem;
import com.lixo.pos.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RestaurantRepository extends JpaRepository <Restaurant, Long> {
    List<Restaurant> findAllByCompanyId(Long companyId);

    Optional<Restaurant> findByCompanyIdAndId(Long companyId, Long id);

}
