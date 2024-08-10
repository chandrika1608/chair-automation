package com.lixo.automate.repository;

import com.lixo.automate.model.Automate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AutomationRepository extends JpaRepository<Automate, Long> {
}
