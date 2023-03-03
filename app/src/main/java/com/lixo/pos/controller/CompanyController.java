package com.lixo.pos.controller;

import com.lixo.pos.model.Company;
import com.lixo.pos.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/company")
public class CompanyController {
    @Autowired
    private CompanyService companyService;

    @GetMapping(value = "/getcompany")
    public List<Company> getAllCompany() {
        return companyService.getAllCompany();
    }
    @GetMapping("retrieve/{id}")
    public Company getCompany(@PathVariable String id) {
        return companyService.getCompanyById(id);
    }
    @PostMapping("/save")
    public Company createCompany(@RequestBody Company newCompany) {
        return companyService.createCompany(newCompany);
    }
    @DeleteMapping("delete/{id}")
    public void deleteCompany(@PathVariable String id){
        companyService.deleteCompany(id);
    }
    @PutMapping("update/{id}")
    public Company updateCompany(@RequestBody Company company, @PathVariable String id){
        return companyService.updateCompany(id,company);
    }

}
