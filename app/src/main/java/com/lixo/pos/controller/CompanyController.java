package com.lixo.pos.controller;

import com.lixo.pos.exception.CompanyNotFoundException;
import com.lixo.pos.model.Company;
import com.lixo.pos.model.Restaurant;
import com.lixo.pos.service.CompanyService;
import com.lixo.pos.service.RestaurantService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.OptimisticLockException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/company")
public class CompanyController {
    @Autowired
    private CompanyService companyService;

    @GetMapping
    public List<Company> getAllCompany() throws CompanyNotFoundException {

        try {
            return companyService.getAllCompany();

        } catch (IllegalStateException | IllegalArgumentException e) {
            throw new CompanyNotFoundException("Error retrieving company", e);

        }
    }

    @GetMapping("/{id}")
    public Company getCompanyById(@PathVariable String id) throws CompanyNotFoundException {

        try {
            Company company = companyService.getCompanyById( id);

            if (company == null) {
                throw new CompanyNotFoundException("Restaurant with ID " + id + " not found");
            }

            return company;

        } catch (IllegalStateException | IllegalArgumentException e) {
            throw new CompanyNotFoundException("Error retrieving restaurant with ID " + id, e);

        }

    }

    @PostMapping
    public ResponseEntity<Company> addCompany(@RequestBody @Valid Company company) throws CompanyNotFoundException
    {
        try {
            //return new ResponseEntity<>(createdCompany, HttpStatus.CREATED);

            Company newCompany = companyService.createCompany(company);
            return ResponseEntity.created(URI.create("/api/company/" + newCompany.getId()))
                    .body(newCompany);
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleValidationExceptions(MethodArgumentNotValidException ex){
        List<String> errorMessages = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> error.getDefaultMessage())
                .collect(Collectors.toList());
        return new ResponseEntity<>(errorMessages.toString(), HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Company> updateCompany(@PathVariable String id,@Valid @RequestBody Company company) throws CompanyNotFoundException, OptimisticLockException {

        try {

            Company updatedCompany = companyService.updateCompany(id, company);

            if (company == null) {
                throw new EntityNotFoundException("Company with ID " + updatedCompany.getId() + " not found");
            }
            return ResponseEntity.ok(updatedCompany);

        } catch (IllegalStateException | IllegalArgumentException  e) {
            throw new CompanyNotFoundException("Error updating restaurant with ID " + id, e);

        } catch (OptimisticLockException e) {
            throw new CompanyNotFoundException("Concurrent update detected on restaurant with ID " + id, e);

        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCompany(@PathVariable String id) throws CompanyNotFoundException {

        try {

            Company company =companyService.getCompanyById(id);

            if (company == null) {
                throw new CompanyNotFoundException("Company with ID " + id + " not found");
            }
            companyService.deleteCompany(id);
        }
        catch (IllegalStateException | IllegalArgumentException e) {

            throw new CompanyNotFoundException("Error deleting company with ID " + id, e);

        }
        return ResponseEntity.noContent().build();
    }

}
