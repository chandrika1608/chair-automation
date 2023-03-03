package com.lixo.pos.controller;

import com.lixo.pos.model.Company;
import com.lixo.pos.model.Company;
import com.lixo.pos.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/companies")
public class CompanyController {
    @Autowired
    private CompanyService companyService;

    @GetMapping(value = "/getcompanies")
    public List<Company> getAllCompanies() {
        return companyService.getAllCompanies();
    }
    @GetMapping("/getcompany/{id}")
    public Company getCompany(@PathVariable Long id) {
        return companyService.getCompanyById(id);
    }
    @PostMapping
    public ResponseEntity<Company> addCompany(@RequestBody Company newCompany) {
       Company company =  companyService.createCompany(newCompany);
        return ResponseEntity.created(URI.create("/api/company/" + newCompany.getId()))
                .body(newCompany);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCompany(@PathVariable Long id) {
        companyService.deleteCompany(id);
        return ResponseEntity.noContent().build();
    }
    @PutMapping("/{id}")
    public ResponseEntity<Company> updateCompany(@PathVariable Long id, @RequestBody Company company) {
        Company updatedCompany = companyService.updateCompany(id,company);
        return ResponseEntity.ok(updatedCompany);
    }
}
