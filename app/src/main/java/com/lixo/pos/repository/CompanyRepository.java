package com.lixo.pos.repository;

import com.lixo.pos.model.Combo;
import com.lixo.pos.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CompanyRepository extends JpaRepository<Company,Long> {

}
