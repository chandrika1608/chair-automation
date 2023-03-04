package com.lixo.pos.repository;

import com.lixo.pos.model.Tax;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TaxRepository extends JpaRepository<Tax, Long> {
    List<Tax> findAllByMenuItemId(Long menuItemId);

    Optional<Tax> findByMenuItemIdAndId(Long menuItemId, Long id);
}
