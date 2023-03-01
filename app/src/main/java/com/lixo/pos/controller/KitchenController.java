package com.lixo.pos.controller;

import com.lixo.pos.exception.KitchenNotFoundException;
import com.lixo.pos.model.Kitchen;
import com.lixo.pos.model.Restaurant;
import com.lixo.pos.service.KitchenService;
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
@RequestMapping("/api/kitchen")
public class KitchenController {
    @Autowired
    private KitchenService kitchenService;

    @GetMapping
    public List<Kitchen> getAllKitchen() throws KitchenNotFoundException {

        try {
            return kitchenService.getAllKitchen();

        } catch (IllegalStateException | IllegalArgumentException e) {
            throw new KitchenNotFoundException("Error retrieving kitchen", e);

        }
    }

    @GetMapping("/{id}")
    public Kitchen getKitchenById(@PathVariable String id) throws KitchenNotFoundException {

        try {
            Kitchen kitchen = kitchenService.getKitchenById(id);

            if (kitchen == null) {
                throw new KitchenNotFoundException("Kitchen with ID " + id + " not found");
            }

            return kitchen;

        } catch (IllegalStateException | IllegalArgumentException e) {
            throw new KitchenNotFoundException("Error retrieving kitchen with ID " + id, e);

        }

    }

    @PostMapping
    public ResponseEntity<Kitchen> addKitchen(@RequestBody @Valid Kitchen kitchen) throws KitchenNotFoundException
    {
        try {
            //return new ResponseEntity<>(createdKitchen, HttpStatus.CREATED);

            Kitchen newKitchen = kitchenService.createKitchen(kitchen);
            return ResponseEntity.created(URI.create("/api/kitchen/" + newKitchen.getId()))
                    .body(newKitchen);
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
    public ResponseEntity<Kitchen> updateKitchen(@PathVariable String id,@Valid @RequestBody Kitchen kitchen) throws KitchenNotFoundException, OptimisticLockException {

        try {

            Kitchen updatedKitchen = kitchenService.updateKitchen(id, kitchen);

            if (kitchen == null) {
                throw new EntityNotFoundException("Kitchen with ID " + updatedKitchen.getId() + " not found");
            }
            return ResponseEntity.ok(updatedKitchen);

        } catch (IllegalStateException | IllegalArgumentException  e) {
            throw new KitchenNotFoundException("Error updating kitchen with ID " + id, e);

        } catch (OptimisticLockException e) {
            throw new KitchenNotFoundException("Concurrent update detected on kitchen with ID " + id, e);

        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteKitchen(@PathVariable String id) throws KitchenNotFoundException {

        try {

            Kitchen kitchen =kitchenService.getKitchenById(id);

            if (kitchen == null) {
                throw new KitchenNotFoundException("Kitchen with ID " + id + " not found");
            }
            kitchenService.deleteKitchen(id);
        }
        catch (IllegalStateException | IllegalArgumentException e) {

            throw new KitchenNotFoundException("Error deleting kitchen with ID " + id, e);

        }
        return ResponseEntity.noContent().build();
    }

}
