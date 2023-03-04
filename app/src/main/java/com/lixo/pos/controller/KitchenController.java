package com.lixo.pos.controller;

import com.lixo.pos.model.Kitchen;
import com.lixo.pos.model.Restaurant;
import com.lixo.pos.service.KitchenService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/restaurants/{restaurantId}/kitchens/")
@RequiredArgsConstructor
public class KitchenController {

    private final KitchenService kitchenService;

    @GetMapping
    public List<Kitchen> getAllKitchen(@PathVariable Long restaurantId) {
        return kitchenService.getAllKitchens(restaurantId);
    }
    @GetMapping("/{id}")
    public Kitchen getKitchen(@PathVariable Long restaurantId,@PathVariable Long id) {
        return kitchenService.getKitchenById(restaurantId,id);
    }
    @PostMapping
    public ResponseEntity<Kitchen> addKitchen(@PathVariable Long restaurantId,@RequestBody Kitchen kitchen)
    {
        Kitchen newKitchen = kitchenService.createKitchen(restaurantId,kitchen);
        return ResponseEntity.created(URI.create("/api/kitchen/" + newKitchen.getId()))
                .body(newKitchen);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteKitchen(@PathVariable Long restaurantId,@PathVariable Long id) {
        kitchenService.deleteKitchen(restaurantId,id);
        return ResponseEntity.noContent().build();
    }
    @PutMapping("/{id}")
    public ResponseEntity<Kitchen> updateKitchen(@PathVariable Long restaurantId,@PathVariable Long id,@RequestBody Kitchen kitchen){

        Kitchen updatedKitchen= kitchenService.updateKitchen(restaurantId,id, kitchen);
        return ResponseEntity.ok(updatedKitchen);
    }

}



