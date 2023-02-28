package com.lixo.pos.controller;

import com.lixo.pos.exception.RestaurantNotFoundException;
import com.lixo.pos.model.Restaurant;
import com.lixo.pos.service.RestaurantService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.OptimisticLockException;
import jakarta.persistence.PersistenceException;
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
@RequestMapping("/api/restaurants")
public class RestaurantController {
    @Autowired
    private RestaurantService restaurantService;

    @GetMapping
    public List<Restaurant> getAllRestaurants() throws RestaurantNotFoundException {

        try {
            return restaurantService.getAllRestaurants();

        } catch (IllegalStateException | IllegalArgumentException e) {
            throw new RestaurantNotFoundException("Error retrieving restaurants", e);

        }
    }

    @GetMapping("/{id}")
    public Restaurant getRestaurantById(@PathVariable String id) throws RestaurantNotFoundException {

        try {
            Restaurant restaurant = restaurantService.getRestaurantById(id);

            if (restaurant == null) {
                throw new RestaurantNotFoundException("Restaurant with ID " + id + " not found");
            }

            return restaurant;

        } catch (IllegalStateException | IllegalArgumentException e) {
            throw new RestaurantNotFoundException("Error retrieving restaurant with ID " + id, e);

        }

    }

    @PostMapping
    public ResponseEntity<Restaurant> addRestaurant(@RequestBody @Valid Restaurant restaurant) throws RestaurantNotFoundException
    {
        try {
            Restaurant newRestaurant = restaurantService.createRestaurant(restaurant);
            return ResponseEntity.created(URI.create("/api/restaurants/" + newRestaurant.getId()))
                    .body(newRestaurant);
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
    public ResponseEntity<Restaurant> updateRestaurant(@PathVariable String id,@Valid @RequestBody Restaurant restaurant) throws RestaurantNotFoundException, OptimisticLockException {

        try {

            Restaurant updatedRestaurant = restaurantService.updateRestaurant(id, restaurant);

            if (restaurant == null) {
                throw new EntityNotFoundException("Restaurant with ID " + updatedRestaurant.getId() + " not found");
            }
            return ResponseEntity.ok(updatedRestaurant);

        } catch (IllegalStateException | IllegalArgumentException  e) {
            throw new RestaurantNotFoundException("Error updating restaurant with ID " + id, e);

        } catch (OptimisticLockException e) {
            throw new RestaurantNotFoundException("Concurrent update detected on restaurant with ID " + id, e);

        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRestaurant(@PathVariable String id) throws RestaurantNotFoundException {

        try {

            Restaurant restaurant =restaurantService.getRestaurantById(id);

            if (restaurant == null) {
                throw new RestaurantNotFoundException("Restaurant with ID " + id + " not found");
            }
            restaurantService.deleteRestaurant(id);
        }
        catch (IllegalStateException | IllegalArgumentException e) {

            throw new RestaurantNotFoundException("Error deleting restaurant with ID " + id, e);

        }
        return ResponseEntity.noContent().build();
    }

}
