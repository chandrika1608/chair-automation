package com.lixo.automate.service;

import com.lixo.automate.exception.ResourceNotFoundException;
import com.lixo.automate.model.Automate;
import com.lixo.automate.repository.AutomationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AutomationService {


    private final AutomationRepository companyRepository;


    public List<Automate> getAllCompanies() {
        return companyRepository.findAll();
    }

    public Automate getCompanyById(Long id) {
        return companyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Item not found with id: " + id));
    }

    public Automate createCompany(Automate newCompany) {
        return companyRepository.save(newCompany);
    }


    public Automate updateCompany(Long id, Automate company) {
        Optional<Automate> existingCompany = companyRepository.findById(id);
        if (existingCompany.isPresent()) {
            Automate updatedCompany = existingCompany.get();
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
        Optional<Automate> company = companyRepository.findById(id);
        if (company.isPresent()) {
            companyRepository.delete(company.get());
        } else {
            throw new ResourceNotFoundException("company not found with id " + id);
        }
    }

}
