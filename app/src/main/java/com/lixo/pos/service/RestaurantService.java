package com.lixo.pos.service;

import com.lixo.pos.exception.ResourceNotFoundException;
import com.lixo.pos.model.Company;
import com.lixo.pos.model.Restaurant;
import com.lixo.pos.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;

    public List<Restaurant> getAllRestaurants(Long companyId) {

        return restaurantRepository.findAllByCompanyId(companyId);
    }

    public Restaurant getRestaurantById(Long companyId, Long id) {
        Optional<Restaurant> restaurant = restaurantRepository.findByCompanyIdAndId(companyId, id);
        if (restaurant.isPresent()) {
            return restaurant.get();
        } else {
            throw new ResourceNotFoundException("Restaurant not found with id " + id);
        }
    }

    public Restaurant createRestaurant(Long companyId, Restaurant restaurant) {
        restaurant.setCompany(new Company(companyId));
        return restaurantRepository.save(restaurant);
    }

    public Restaurant updateRestaurant(Long companyId, Long id, Restaurant restaurant) {
        Optional<Restaurant> existingRestaurant = restaurantRepository.findByCompanyIdAndId(companyId, id);
        if (existingRestaurant.isPresent()) {
            Restaurant updatedRestaurant = existingRestaurant.get();
            updatedRestaurant.setName(restaurant.getName());
            updatedRestaurant.setAddress(restaurant.getAddress());
            return restaurantRepository.save(updatedRestaurant);
        } else {
            throw new ResourceNotFoundException("Restaurant not found with id " + id);
        }
    }

    public void deleteRestaurant(Long companyId, Long id) {
        Optional<Restaurant> restaurant = restaurantRepository.findByCompanyIdAndId(companyId, id);
        if (restaurant.isPresent()) {
            restaurantRepository.delete(restaurant.get());
        } else {
            throw new ResourceNotFoundException("Restaurant not found with id " + id);
        }
    }
}
