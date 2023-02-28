package com.lixo.pos.service;

import com.lixo.pos.exception.RestaurantNotFoundException;
import com.lixo.pos.exception.RestaurantServiceException;
import com.lixo.pos.model.Restaurant;
import com.lixo.pos.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RestaurantService {

    @Autowired
    private RestaurantRepository restaurantRepository;


    public List<Restaurant> getAllRestaurants() {
        try {
            return restaurantRepository.findAll();
        } catch (DataAccessException ex) {
            throw new RestaurantServiceException("Error getting all restaurants: " + ex.getMessage(), ex);
        }
    }

    public Restaurant getRestaurantById(String id) {
        try {
            return restaurantRepository.findById(id)
                    .orElseThrow(() -> new RestaurantNotFoundException("Restaurant not found with ID: " + id));
        } catch (DataAccessException ex) {
            throw new RestaurantServiceException("Error getting restaurant by ID: " + id, ex);
        }
    }

    public Restaurant createRestaurant(Restaurant restaurant) {


        try {
            return restaurantRepository.save(restaurant);
        } catch (DataAccessException ex) {
            throw new RestaurantServiceException("Error saving restaurant: " + ex.getMessage(), ex);
        }

    }

    public Restaurant updateRestaurant(String id, Restaurant restaurant) {

        try {
            Optional<Restaurant> existingRestaurant = restaurantRepository.findById(id);
            if (existingRestaurant.isPresent()) {
                Restaurant updatedRestaurant = existingRestaurant.get();
                updatedRestaurant.setName(restaurant.getName());
                updatedRestaurant.setAddress(restaurant.getAddress());
                updatedRestaurant.setPhone(restaurant.getPhone());
                return restaurantRepository.save(updatedRestaurant);
            } else {
                throw new RestaurantServiceException("Restaurant not found with id " + id);
            }
        } catch (DataAccessException ex) {
            throw new RestaurantServiceException("Error updating restaurant: " + ex.getMessage(), ex);
        }
    }

    public void deleteRestaurant(String id) {
        try {
            Optional<Restaurant> restaurant = restaurantRepository.findById(id);
            if (restaurant.isPresent()) {
                restaurantRepository.delete(restaurant.get());
            } else {
                throw new RestaurantNotFoundException("Restaurant not found with id " + id);
            }
        }
        catch (DataAccessException ex) {
            throw new RestaurantServiceException("Error deleting restaurant with ID: " + id, ex);
        }

    }

}

