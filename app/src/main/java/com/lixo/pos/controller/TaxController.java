package com.lixo.pos.controller;


import com.lixo.pos.exception.TaxNotFoundException;
import com.lixo.pos.model.Restaurant;
import com.lixo.pos.model.Tax;
import com.lixo.pos.service.RestaurantService;
import com.lixo.pos.service.TaxService;
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
@RequestMapping("/api/tax")
public class TaxController {
    @Autowired
    private TaxService taxService;

    @GetMapping
    public List<Tax> getAllTax() throws TaxNotFoundException {

        try {
            return taxService.getAllTax();

        } catch (IllegalStateException | IllegalArgumentException e) {
            throw new TaxNotFoundException("Error retrieving tax", e);

        }
    }

    @GetMapping("/{id}")
    public Tax getTaxById(@PathVariable String id) throws TaxNotFoundException {

        try {
            Tax tax = taxService.getTaxById(id);

            if (tax == null) {
                throw new TaxNotFoundException("Tax with ID " + id + " not found");
            }

            return tax;

        } catch (IllegalStateException | IllegalArgumentException e) {
            throw new TaxNotFoundException("Error retrieving tax with ID " + id, e);

        }

    }

    @PostMapping
    public ResponseEntity<Tax> addTax(@RequestBody @Valid Tax tax) throws TaxNotFoundException
    {
        try {
            //return new ResponseEntity<>(createdTax, HttpStatus.CREATED);

            Tax newTax = taxService.createTax(tax);
            return ResponseEntity.created(URI.create("/api/tax/" + newTax.getId()))
                    .body(newTax);
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
    public ResponseEntity<Tax> updateTax(@PathVariable String id,@Valid @RequestBody Tax tax) throws TaxNotFoundException, OptimisticLockException {

        try {

            Tax updatedTax = taxService.updateTax(id, tax);

            if (tax == null) {
                throw new EntityNotFoundException("Tax with ID " + updatedTax.getId() + " not found");
            }
            return ResponseEntity.ok(updatedTax);

        } catch (IllegalStateException | IllegalArgumentException  e) {
            throw new TaxNotFoundException("Error updating tax with ID " + id, e);

        } catch (OptimisticLockException e) {
            throw new TaxNotFoundException("Concurrent update detected on tax with ID " + id, e);

        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTax(@PathVariable String id) throws TaxNotFoundException {

        try {

            Tax tax =taxService.getTaxById(id);

            if (tax == null) {
                throw new TaxNotFoundException("Tax with ID " + id + " not found");
            }
            taxService.deleteTax(id);
        }
        catch (IllegalStateException | IllegalArgumentException e) {

            throw new TaxNotFoundException("Error deleting restaurant with ID " + id, e);

        }
        return ResponseEntity.noContent().build();
    }

}
