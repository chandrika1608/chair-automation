package com.lixo.pos.controller;

import com.lixo.pos.model.Kitchen;
import com.lixo.pos.service.RestaurantKitchenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/kitchen")
public class RestaurantKitchenController {
    @Autowired
    private RestaurantKitchenService kitchenService;

    @GetMapping(value = "/getkitchen")
    public List<Kitchen> getAllKitchen() {
        return kitchenService.getAllKitchen();
    }
    @GetMapping("retreive/{id}")
    public Kitchen getKitchen(@PathVariable Long id) {
        return kitchenService.getKitchenById(id);
    }
    @PostMapping("/save")
    public Kitchen createKitchen(@RequestBody Kitchen newKitchen) {
        return kitchenService.createKitchen(newKitchen);
    }
    @DeleteMapping("delete/{id}")
    public void deleteKitchen(@PathVariable Long id){
        kitchenService.deleteKitchen(id);
    }
    @PutMapping("update/{id}")
    public Kitchen updateKitchen(@RequestBody Kitchen kitchen,@PathVariable Long id){
        return kitchenService.updateKitchen(id,kitchen);
    }

}
