package com.lixo.pos.repository;

import com.lixo.pos.model.Tax;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaxRepository extends JpaRepository<Tax, String> {
}
