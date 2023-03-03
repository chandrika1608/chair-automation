package com.lixo.pos.controller;

import com.lixo.pos.model.Kitchen;
import com.lixo.pos.service.KitchenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Kitchen")
public class KitchenController {
    @Autowired
    private KitchenService kitchenService;

    @GetMapping(value = "/getkitchen")
    public List<Kitchen> getAllKitchen() {
        return kitchenService.getAllKitchen();
    }
    @GetMapping("retreive/{id}")
    public Kitchen getKitchen(@PathVariable String id) {
        return kitchenService.getKitchenById(id);
    }
    @PostMapping("/save")
    public Kitchen createKitchen(@RequestBody Kitchen newKitchen) {
        return kitchenService.createKitchen(newKitchen);
    }
    @DeleteMapping("delete/{id}")
    public void deleteKitchen(@PathVariable String id){
        kitchenService.deleteKitchen(id);
    }
    @PutMapping("update/{id}")
    public Kitchen updateKitchen(@RequestBody Kitchen kitchen,@PathVariable String id){
        return kitchenService.updateKitchen(id,kitchen);
    }

}

