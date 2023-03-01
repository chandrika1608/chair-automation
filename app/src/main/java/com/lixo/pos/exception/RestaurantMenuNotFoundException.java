package com.lixo.pos.exception;

public class RestaurantMenuNotFoundException extends RuntimeException {

    public RestaurantMenuNotFoundException(String restaurantId, String menuItemId) {
        super("Restaurant menu item not found with id " + menuItemId + " for restaurant id " + restaurantId);
    }
    public RestaurantMenuNotFoundException(String message) {
        super(message);
    }

    public RestaurantMenuNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }


}
