package com.lixo.pos.service;

import com.lixo.pos.model.Company;
import com.lixo.pos.repository.CompanyRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    @Transactional
    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    @Transactional
    public Company getCompanyById(Long id) {
        return companyRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Item not found with id: " + id));
    }

    @Transactional
    public Company createCompany(Company newCompany) {
        return companyRepository.save(newCompany);
    }

    @Transactional
    public Company updateCompany(Long id, Company updatedCompany) {
        Company company = companyRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Item not found with id: " + id));
        company.setName(updatedCompany.getName());
        company.setCountry(updatedCompany.getCountry());
        company.setState(updatedCompany.getState());
        company.setCity(updatedCompany.getCity());
        company.setCompanyAddress(updatedCompany.getCompanyAddress());
        return companyRepository.save(company);
    }

    @Transactional
    public void deleteCompany(Long id) {
        companyRepository.deleteById(id);
    }

}
