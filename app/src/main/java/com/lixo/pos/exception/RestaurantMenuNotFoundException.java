package com.lixo.pos.exception;

public class RestaurantMenuNotFoundException extends RuntimeException {

    public RestaurantMenuNotFoundException(Long restaurantId, Long menuItemId) {
        super("Restaurant menu item not found with id " + menuItemId + " for restaurant id " + restaurantId);
    }

}
