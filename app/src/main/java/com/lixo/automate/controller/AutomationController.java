package com.lixo.automate.controller;

import com.lixo.automate.model.Automate;
import com.lixo.automate.service.AutomationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/companies")
@RequiredArgsConstructor
public class AutomationController {

    private final AutomationService companyService;

    @GetMapping
    public List<Automate> getAllCompanies() {
        return companyService.getAllCompanies();
    }

    @GetMapping("/{id}")
    public Automate getCompany(@PathVariable Long id) {
        return companyService.getCompanyById(id);
    }

    @PostMapping
    public ResponseEntity<Automate> addCompany(@RequestBody Automate company) {
        Automate newCompany = companyService.createCompany(company);
        return ResponseEntity.created(URI.create("/api/company/" + newCompany.getId()))
                .body(newCompany);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCompany(@PathVariable Long id) {
        companyService.deleteCompany(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Automate> updateCompany(@PathVariable Long id, @RequestBody Automate company) {
        Automate updatedCompany = companyService.updateCompany(id, company);
        return ResponseEntity.ok(updatedCompany);
    }
}
