package com.lixo.pos.controller;

import com.lixo.pos.model.Restaurant;
import com.lixo.pos.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/companies/{companyId}/restaurants/")
@RequiredArgsConstructor
public class RestaurantController {

    private final RestaurantService restaurantService;

    @GetMapping
    public List<Restaurant> getAllRestaurants(@PathVariable Long companyId) {

        return restaurantService.getAllRestaurants(companyId);
    }

    @GetMapping("/{id}")
    public Restaurant getRestaurantById(@PathVariable Long companyId, @PathVariable Long id) {

        return restaurantService.getRestaurantById(companyId, id);
    }

    @PostMapping
    public ResponseEntity<Restaurant> addRestaurant(@PathVariable Long companyId, @RequestBody Restaurant restaurant) {
        Restaurant newRestaurant = restaurantService.createRestaurant(companyId, restaurant);
        return ResponseEntity.created(URI.create("/api/restaurants/" + newRestaurant.getId()))
                .body(newRestaurant);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Restaurant> updateRestaurant(@PathVariable Long companyId, @PathVariable Long id, @RequestBody Restaurant restaurant) {
        Restaurant updatedRestaurant = restaurantService.updateRestaurant(companyId, id, restaurant);
        return ResponseEntity.ok(updatedRestaurant);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRestaurant(@PathVariable Long companyId, @PathVariable Long id) {
        restaurantService.deleteRestaurant(companyId, id);
        return ResponseEntity.noContent().build();
    }
}
