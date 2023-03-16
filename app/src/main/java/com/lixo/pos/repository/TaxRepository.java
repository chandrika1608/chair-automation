package com.lixo.pos.repository;

import com.lixo.pos.model.Tax;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface TaxRepository extends JpaRepository<Tax, Long> {
    List<Tax> findAllByMenuItemId(Long menuItemId);

    Optional<Tax> findByMenuItemIdAndId(Long menuItemId, Long id);
}
