package com.lixo.pos.service;

import com.lixo.pos.exception.ResourceNotFoundException;
import com.lixo.pos.model.Company;
import com.lixo.pos.repository.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CompanyService {


    private final CompanyRepository companyRepository;


    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    public Company getCompanyById(Long id) {
        return companyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Item not found with id: " + id));
    }

    public Company createCompany(Company newCompany) {
        return companyRepository.save(newCompany);
    }


    public Company updateCompany(Long id, Company company) {
        Optional<Company> existingCompany = companyRepository.findById(id);
        if (existingCompany.isPresent()) {
            Company updatedCompany = existingCompany.get();
            updatedCompany.setName(company.getName());
            updatedCompany.setCountry(company.getCountry());
            updatedCompany.setState(company.getState());
            updatedCompany.setCity(company.getCity());
            updatedCompany.setCompanyAddress(company.getCompanyAddress());
            return companyRepository.save(updatedCompany);
        } else {
            throw new ResourceNotFoundException("Company not found with id " + id);
        }
    }

    public void deleteCompany(Long id) {
        Optional<Company> company = companyRepository.findById(id);
        if (company.isPresent()) {
            companyRepository.delete(company.get());
        } else {
            throw new ResourceNotFoundException("company not found with id " + id);
        }
    }

}
