package com.lixo.pos.controller;

import com.lixo.pos.exception.RestaurantMenuNotFoundException;
import com.lixo.pos.exception.RestaurantNotFoundException;
import com.lixo.pos.model.Combo;
import com.lixo.pos.model.MenuItem;
import com.lixo.pos.model.Restaurant;
import com.lixo.pos.service.RestaurantMenuService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.OptimisticLockException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/restaurants/{restaurantId}/menu")
public class RestaurantMenuController {
    @Autowired
    private RestaurantMenuService restaurantMenuService;

    @GetMapping("/getAllItems")
    public List<MenuItem> getAllMenuItems(@PathVariable String restaurantId) throws RestaurantMenuNotFoundException {

        try {
            return restaurantMenuService.getAllMenuItems(restaurantId);

        } catch (IllegalStateException | IllegalArgumentException e) {
            throw new RestaurantMenuNotFoundException("Error retrieving RestaurantsMenu", e);
        }
    }
    @GetMapping("/{itemId}")
    public MenuItem getMenuItemById(@PathVariable String restaurantId, @PathVariable String itemId)throws RestaurantMenuNotFoundException {
        try {
            MenuItem menuItem = restaurantMenuService.getMenuItemById(itemId,restaurantId);

            if (menuItem == null) {
                throw new RestaurantMenuNotFoundException("MenuItem with ID " + itemId + " not found");
            }

            return menuItem;

        } catch (IllegalStateException | IllegalArgumentException e) {
            throw new RestaurantMenuNotFoundException("Error retrieving MenuItem with ID " + itemId, e);

        }
    }

    @PostMapping("/menu/menuItems")
    public ResponseEntity<MenuItem> addMenuItem(@PathVariable String restaurantId, @RequestBody @Valid MenuItem menuItem) throws RestaurantMenuNotFoundException {

        try {
            MenuItem newMenuItem = restaurantMenuService.addMenuItem(restaurantId, menuItem);
            return ResponseEntity.created(URI.create("/api/restaurants/" + restaurantId + "/menu/" + newMenuItem.getId()))
                    .body(newMenuItem);
        }
        catch(Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @ExceptionHandler(value = {MethodArgumentNotValidException.class, MethodArgumentTypeMismatchException.class})
    public ResponseEntity<String> handleValidationExceptions(MethodArgumentNotValidException ex){
        List<String> errorMessages = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> error.getDefaultMessage())
                .collect(Collectors.toList());
        return new ResponseEntity<>(errorMessages.toString(), HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(value = NumberFormatException.class)
    public ResponseEntity<Object> handleNumberFormatException(NumberFormatException ex) {
        String errorMessage = "Invalid input format for path variable: " + ex.getMessage();
        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/{itemId}")
    public ResponseEntity<MenuItem> updateMenuItem(@PathVariable String restaurantId, @PathVariable String itemId, @RequestBody @Valid MenuItem menuItem)throws RestaurantMenuNotFoundException {
        try {

            MenuItem updatedMenuItem = restaurantMenuService.updateMenuItem(restaurantId, itemId, menuItem);

            if (updatedMenuItem == null) {
                throw new EntityNotFoundException("MenuItem with ID " + updatedMenuItem.getId() + " not found");
            }
            return ResponseEntity.ok(updatedMenuItem);

        } catch (IllegalStateException | IllegalArgumentException  e) {
            throw new RestaurantMenuNotFoundException("Error updating MenuItem with ID " + itemId, e);

        } catch (OptimisticLockException e) {
            throw new RestaurantMenuNotFoundException("Concurrent update detected on MenuItem with ID " + itemId, e);
        }
    }

    @DeleteMapping("/{itemId}")
    public ResponseEntity<Void> deleteMenuItem(@PathVariable String restaurantId, @PathVariable String itemId)throws RestaurantMenuNotFoundException {

        try {

            MenuItem menuItem=restaurantMenuService.getMenuItemById(restaurantId,itemId);

            if (menuItem == null) {
                throw new RestaurantMenuNotFoundException("MenuItem with ID " + itemId + " not found");
            }
            restaurantMenuService.deleteMenuItem(restaurantId, itemId);
        }
        catch (IllegalStateException | IllegalArgumentException e) {

            throw new RestaurantMenuNotFoundException("Error deleting MenuItem with ID " + itemId, e);
        }
        return ResponseEntity.noContent().build();

    }

    @PostMapping("/combos")
    public ResponseEntity<Combo> addCombo(@PathVariable String restaurantId, @RequestBody @Valid Combo combo) throws RestaurantMenuNotFoundException{
        try {
            Combo newCombo = restaurantMenuService.addCombo(restaurantId, combo);
            return ResponseEntity.created(URI.create("/api/restaurants/" + restaurantId + "/menu/combos/" + newCombo.getId()))
                    .body(newCombo);
        }
        catch(Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/combos/{comboId}")
    public ResponseEntity<Combo> updateCombo(@PathVariable String restaurantId, @PathVariable String comboId, @RequestBody Combo combo)throws RestaurantMenuNotFoundException {
        try {

            Combo updatedCombo = restaurantMenuService.updateCombo(restaurantId, comboId, combo);

            if (updatedCombo == null) {
                throw new EntityNotFoundException("Combo with ID " + updatedCombo.getId() + " not found");
            }
            return ResponseEntity.ok(updatedCombo);

        } catch (IllegalStateException | IllegalArgumentException  e) {
            throw new RestaurantMenuNotFoundException("Error updating Combo with ID " + comboId, e);

        } catch (OptimisticLockException e) {
            throw new RestaurantMenuNotFoundException("Concurrent update detected on Combo with ID " + comboId, e);
        }

    }

    @DeleteMapping("/combos/{comboId}")
    public ResponseEntity<Void> deleteCombo(@PathVariable String restaurantId, @PathVariable String comboId)throws RestaurantMenuNotFoundException {

        try {

            Combo newCombo=restaurantMenuService.getComboById(restaurantId,comboId);

            if (newCombo == null) {
                throw new RestaurantMenuNotFoundException("Restaurant with ID " + comboId + " not found");
            }
            restaurantMenuService.deleteMenuItem(restaurantId, comboId);
        }
        catch (IllegalStateException | IllegalArgumentException e) {

            throw new RestaurantMenuNotFoundException("Error deleting restaurant with ID " + comboId, e);

        }
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/{comboId}")
    public Combo getComboById(@PathVariable String restaurantId, @PathVariable String comboId)throws RestaurantMenuNotFoundException {
        try {
            Combo combo = restaurantMenuService.getComboById(restaurantId,comboId);

            if (combo == null) {
                throw new RestaurantMenuNotFoundException("Combo with ID " + comboId + " not found");
            }

            return combo ;

        } catch (IllegalStateException | IllegalArgumentException e) {
            throw new RestaurantMenuNotFoundException("Error retrieving Combo with ID " + comboId, e);

        }
    }

}
